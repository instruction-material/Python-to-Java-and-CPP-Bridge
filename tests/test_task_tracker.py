"""Independent state-model, native API and console parity for the capstone."""

from datetime import datetime, timezone
import importlib.util
import json
import os
from pathlib import Path
import random
import shutil
import signal
import subprocess
import sys
import tempfile
import unittest

ROOT = Path(__file__).resolve().parents[1]
PACK = ROOT / "PTJ7-Task-Tracker-Capstone"
BANNER = "Task tracker. Commands: ADD <title>, DONE <id>, REMOVE <id>, LIST, OPEN, SUMMARY, QUIT.\n"


def run(command, cwd, text=""):
    child = subprocess.Popen(command, cwd=cwd, stdin=subprocess.PIPE,
                             stdout=subprocess.PIPE, stderr=subprocess.PIPE,
                             text=True, start_new_session=True)
    common = dict(parentTaskId=os.environ.get("CLASSES_FAMILY_TASK_ID", "bridge-capstone-native"),
                  cwd=str(cwd), command=list(map(str, command)), pid=child.pid,
                  parentPid=os.getpid(), timeoutSeconds=30)
    def record(event, **fields):
        print(json.dumps(dict(event=event, time=datetime.now(timezone.utc).isoformat(),
                              **common, **fields)), flush=True)
    record("start")
    try:
        stdout, stderr = child.communicate(text, timeout=30)
    except BaseException:
        try:
            os.killpg(child.pid, signal.SIGKILL)
        except ProcessLookupError:
            pass
        child.communicate()
        record("child-process-group-cleanup", reason="timeout-or-cancellation")
        raise
    record("end", exitCode=child.returncode)
    if child.returncode:
        try:
            os.killpg(child.pid, signal.SIGKILL)
        except ProcessLookupError:
            pass
        record("child-process-group-cleanup", reason="failure")
    return subprocess.CompletedProcess(command, child.returncode, stdout, stderr)


class TaskTrackerTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.temp = tempfile.TemporaryDirectory(prefix="bridge-capstone-")
        cls.addClassCleanup(cls.temp.cleanup)
        cls.work = Path(cls.temp.name)
        cls.java = os.environ.get("JAVA", "java")
        cls.javac = os.environ.get("JAVAC", "javac")
        cls.cxx = os.environ.get("CXX", "c++")
        cls.folders = {}
        for side in ["starter", "solution"]:
            for lang in ["java", "cpp"]:
                folder = cls.work / f"{side}-{lang}"
                shutil.copytree(PACK / side / lang, folder)
                cls.folders[side, lang] = folder
                command = ([cls.javac, "-Xlint:all", "Main.java", "TaskTracker.java"] if lang == "java"
                           else [cls.cxx, "-std=c++17", "-Wall", "-Wextra", "-pedantic", "-I.",
                                 "main.cpp", "TaskTracker.cpp", "-o", "tracker"])
                result = run(command, folder)
                if result.returncode:
                    raise AssertionError(result.stderr)
        spec = importlib.util.spec_from_file_location("capstone_baseline", PACK / "python/task_tracker.py")
        cls.baseline = importlib.util.module_from_spec(spec)
        spec.loader.exec_module(cls.baseline)

    def command(self, lang):
        folder = self.folders["solution", lang]
        return ([self.java, "-cp", str(folder), "Main"] if lang == "java" else [str(folder / "tracker")])

    def harness(self, lang, side, lines):
        folder = self.folders[side, lang]
        if lang == "java":
            source = ("import java.util.*; public class Checks { public static void main(String[] args) {\n"
                      + "\n".join(lines) + "\nSystem.out.println(\"PASS\"); } }\n")
            (folder / "Checks.java").write_text(source)
            compiled = run([self.javac, "-Xlint:all", "Checks.java"], folder)
            command = [self.java, "-ea", "-cp", str(folder), "Checks"]
        else:
            source = ('#include "TaskTracker.h"\n#include <cassert>\n#include <iostream>\n#include <stdexcept>\n'
                      + 'int main() {\n' + "\n".join(lines) + '\nstd::cout << "PASS\\n"; }\n')
            (folder / "Checks.cpp").write_text(source)
            compiled = run([self.cxx, "-std=c++17", "-Wall", "-Wextra", "-pedantic", "-I.",
                            "Checks.cpp", "TaskTracker.cpp", "-o", "checks"], folder)
            command = [str(folder / "checks")]
        self.assertEqual(compiled.returncode, 0, compiled.stderr)
        result = run(command, folder)
        self.assertEqual(result.returncode, 0, result.stderr)
        self.assertEqual(result.stdout, "PASS\n")

    def test_seeded_state_transitions_against_independent_model(self):
        rng = random.Random(6137)
        tasks, next_id, script = {}, 1, []
        for _ in range(80):
            action = rng.choice(["add", "done", "remove"])
            task_id = rng.randint(1, 25)
            if action == "add":
                title = f"Task {rng.randint(1, 4)}"
                script.append(("add", title, next_id))
                tasks[next_id] = [title, False]
                next_id += 1
            elif action == "done":
                changed = task_id in tasks and not tasks[task_id][1]
                if changed:
                    tasks[task_id][1] = True
                script.append(("done", task_id, changed))
            else:
                changed = task_id in tasks
                tasks.pop(task_id, None)
                script.append(("remove", task_id, changed))
            for flag in [False, True]:
                rows = [f"{i} | {'DONE' if done else 'OPEN'} | {title}"
                        for i, (title, done) in tasks.items() if not flag or not done]
                script.append(("rows", flag, rows))
            done = sum(done for _, done in tasks.values())
            script.append(("summary", None,
                           f"Total: {len(tasks)} | Open: {len(tasks)-done} | Done: {done}"))
        for lang in ["java", "cpp", "python"]:
            with self.subTest(language=lang):
                lines = ["TaskTracker t = new TaskTracker();" if lang == "java" else "TaskTracker t;"]
                baseline = self.baseline.TaskTracker()
                for action, value, expected in script:
                    if lang == "python":
                        result = ({"add": baseline.add_task, "done": baseline.complete_task,
                                   "remove": baseline.remove_task, "rows": baseline.list_tasks,
                                   "summary": lambda _: baseline.summary()}[action])(value)
                        self.assertEqual(result, expected)
                        continue
                    argument = (json.dumps(value) if isinstance(value, str)
                                else str(value).lower())
                    call = {"add": "addTask", "done": "completeTask", "remove": "removeTask",
                            "rows": "listTasks", "summary": "summary"}[action]
                    expression = f"t.{call}({argument if action != 'summary' else ''})"
                    if action == "rows":
                        values = ",".join(json.dumps(s) for s in expected)
                        compare = (f"{expression}.equals(Arrays.asList({values}))" if lang == "java"
                                   else f"{expression} == std::vector<std::string>({{{values}}})")
                    elif action == "summary":
                        compare = (f"{expression}.equals({json.dumps(expected)})" if lang == "java"
                                   else f"{expression} == {json.dumps(expected)}")
                    else:
                        compare = f"{expression} == {str(expected).lower()}"
                    lines.append(f"assert {compare};" if lang == "java" else f"assert({compare});")
                if lang != "python":
                    self.harness(lang, "solution", lines)

    def test_normalization_rejection_and_id_budget_preserve_state(self):
        for lang in ["java", "cpp"]:
            lines = ["TaskTracker t = new TaskTracker();" if lang == "java" else "TaskTracker t;"]
            for title in ["", " ", "\t", "\n", "é", "a" * 61]:
                literal = json.dumps(title, ensure_ascii=False)
                lines.append((f'try {{ t.addTask({literal}); throw new AssertionError(); }} catch (IllegalArgumentException e) {{ assert e.getMessage().equals("title"); }}'
                              if lang == "java" else
                              f'try {{ t.addTask({literal}); assert(false); }} catch (const std::invalid_argument& e) {{ assert(std::string(e.what()) == "title"); }}'))
            for method in ["completeTask", "removeTask"]:
                for task_id in [0, -1, -2147483647]:
                    lines.append((f'try {{ t.{method}({task_id}); throw new AssertionError(); }} catch (IllegalArgumentException e) {{ assert e.getMessage().equals("id"); }}'
                                  if lang == "java" else
                                  f'try {{ t.{method}({task_id}); assert(false); }} catch (const std::invalid_argument& e) {{ assert(std::string(e.what()) == "id"); }}'))
            check = 'TaskTracker.normalizeTitle("  A  B!  ").equals("A  B!")' if lang == "java" else 'TaskTracker::normalizeTitle("  A  B!  ") == "A  B!"'
            lines.append(f"assert {check};" if lang == "java" else f"assert({check});")
            lines.append(('for (int i=1; i<=100; i++) assert t.addTask("Title") == i;' if lang == "java" else
                          'for (int i=1; i<=100; i++) assert(t.addTask("Title") == i);'))
            lines.append("t.removeTask(50);")
            for title, error in [("Title", "limit"), ("", "title")]:
                lines.append((f'try {{ t.addTask("{title}"); throw new AssertionError(); }} catch (IllegalArgumentException e) {{ assert e.getMessage().equals("{error}"); }}'
                              if lang == "java" else
                              f'try {{ t.addTask("{title}"); assert(false); }} catch (const std::invalid_argument& e) {{ assert(std::string(e.what()) == "{error}"); }}'))
            summary = 't.summary().equals("Total: 99 | Open: 99 | Done: 0")' if lang == "java" else 't.summary() == "Total: 99 | Open: 99 | Done: 0"'
            lines.append(f"assert {summary};" if lang == "java" else f"assert({summary});")
            self.harness(lang, "solution", lines)

    def test_fresh_rows_instances_and_language_copy_semantics(self):
        for lang in ["java", "cpp"]:
            lines = (["TaskTracker t = new TaskTracker();", "t.addTask(\"First\");", "t.listTasks(false).clear();",
                      "assert t.listTasks(false).size() == 1;", "TaskTracker separate = new TaskTracker();",
                      "assert separate.listTasks(false).isEmpty();", "TaskTracker alias = t;",
                      "alias.completeTask(1);", "assert t.listTasks(true).isEmpty();"] if lang == "java" else
                     ['TaskTracker t; t.addTask("First"); auto rows = t.listTasks(false); rows.clear();',
                      'assert(t.listTasks(false).size() == 1); TaskTracker separate;',
                      'assert(separate.listTasks(false).empty()); auto copy = t; copy.completeTask(1);',
                      'assert(copy.listTasks(true).empty()); assert(t.listTasks(true).size() == 1);'])
            self.harness(lang, "solution", lines)

    def test_all_six_starter_methods_remain_incomplete(self):
        calls = ['TaskTracker.normalizeTitle("Title")', 't.addTask("Title")', 't.completeTask(1)',
                 't.removeTask(1)', 't.listTasks(false)', 't.summary()']
        for lang in ["java", "cpp"]:
            lines = ["TaskTracker t = new TaskTracker();" if lang == "java" else "TaskTracker t;"]
            for call in calls:
                lines.append((f'try {{ {call}; throw new AssertionError(); }} catch (UnsupportedOperationException e) {{ assert e.getMessage().startsWith("Implement"); }}'
                              if lang == "java" else
                              f'try {{ {call.replace("TaskTracker.", "TaskTracker::")}; assert(false); }} catch (const std::logic_error& e) {{ assert(std::string(e.what()).find("Implement") == 0); }}'))
            self.harness(lang, "starter", lines)

    def test_real_console_protocol_matches_independent_fixtures(self):
        cases = [
            ("", ""),
            ("LIST\nOPEN\nSUMMARY", "(empty)\n(empty)\nTotal: 0 | Open: 0 | Done: 0\n"),
            ("ADD  Read notes  \nADD Read notes\nDONE 1\nDONE 1\nOPEN\nREMOVE 1\nADD Plan check\nLIST\nSUMMARY\nQUIT\nADD Ignored\n",
             "ADDED 1\nADDED 2\nCHANGED\nUNCHANGED\n2 | OPEN | Read notes\nCHANGED\nADDED 3\n2 | OPEN | Read notes\n3 | OPEN | Plan check\nTotal: 2 | Open: 2 | Done: 0\n"),
            ("ADD \nADD \t\nADD é\nDONE 0\nDONE -1\nDONE 01\nDONE +1\nREMOVE 2147483648\nDONE 1 \nunknown\nADD Good\nDONE 99\nREMOVE 99\nSUMMARY\n",
             "ERROR: title\n" * 3 + "ERROR: id\n" * 6 + "ERROR: command\nADDED 1\nUNCHANGED\nUNCHANGED\nTotal: 1 | Open: 1 | Done: 0\n"),
            ("ADD " + "a"*60 + "\nADD " + "b"*61 + "\nSUMMARY\n", "ADDED 1\nERROR: title\nTotal: 1 | Open: 1 | Done: 0\n")
        ]
        for lang in ["java", "cpp", "python"]:
            command = self.command(lang) if lang != "python" else [sys.executable, str(PACK / "python/task_tracker.py")]
            folder = self.folders["solution", lang] if lang != "python" else PACK
            for text, expected in cases:
                for line_ending in ["\n", "\r\n"]:
                    with self.subTest(language=lang, input=text[:30], newline=repr(line_ending)):
                        result = run(command, folder, text.replace("\n", line_ending))
                        self.assertEqual(result.returncode, 0, result.stderr)
                        self.assertEqual(result.stdout, BANNER + expected)

    def test_self_contained_imports_and_reference_separation(self):
        brief = (PACK / "README.md").read_text()
        python = (PACK / "python/task_tracker.py").read_text()
        self.assertIn("```python\n" + python + "```", brief)
        for side in ["starter", "solution"]:
            for lang in ["java", "cpp"]:
                folder = PACK / side / lang
                self.assertEqual((folder / "README.md").read_text(), brief)
                self.assertNotIn("solution", [p.name for p in folder.iterdir()])
        self.assertEqual((PACK / "starter/java/Main.java").read_bytes(),
                         (PACK / "solution/java/Main.java").read_bytes())
        self.assertEqual((PACK / "starter/cpp/main.cpp").read_bytes(),
                         (PACK / "solution/cpp/main.cpp").read_bytes())


if __name__ == "__main__":
    unittest.main()
