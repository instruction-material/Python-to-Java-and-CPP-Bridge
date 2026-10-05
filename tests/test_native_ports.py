"""Compile real packs and check behavior through their published learner APIs.

No packages, network, repository build outputs, or GUI dependencies are needed.
Generated harnesses live only in an automatically cleaned temporary directory.
"""

import os
from pathlib import Path
import re
import shutil
import subprocess
import tempfile
import unittest

ROOT = Path(__file__).resolve().parents[1]


def run(command, *, cwd, input_text=""):
    result = subprocess.run(command, cwd=cwd, input=input_text, text=True,
                            stdout=subprocess.PIPE, stderr=subprocess.PIPE,
                            timeout=30, check=False)
    return result


JAVA_CASES = {
    1: """
        ok(Main.greeting("Avery").equals("Hello, Avery!"));
        ok(Main.greeting("").equals("Hello, !"));
        for (int n : new int[]{-7, 0, 12}) ok(Main.absoluteValue(n) == (n == -7 ? 7 : n));
        fails(ArithmeticException.class, () -> Main.absoluteValue(Integer.MIN_VALUE));
        ok(Main.isEven(-2)); ok(Main.isEven(0)); ok(!Main.isEven(-1)); ok(!Main.isEven(3));
        int[] values = {-15, -7, 0, 3, 5, 15, 16};
        String[] labels = {"FizzBuzz", "-7", "FizzBuzz", "Fizz", "Buzz", "FizzBuzz", "16"};
        for (int i=0; i<values.length; i++) ok(Main.fizzBuzzLabel(values[i]).equals(labels[i]));
    """,
    2: """
        int[] values = {Integer.MIN_VALUE, -1, 0, 50, 100, 101, Integer.MAX_VALUE};
        int[] expected = {0, 0, 0, 50, 100, 100, 100};
        for (int i=0; i<values.length; i++) ok(Main.clampScore(values[i]) == expected[i]);
        ok(Math.abs(Main.totalPrice(42.5, true) - 38.25) < 1e-10);
        ok(Main.totalPrice(42.5, false) == 42.5); ok(Main.totalPrice(0, true) == 0);
        ok(Main.countVowels("") == 0); ok(Main.countVowels("AEIOUaeiou") == 10);
        ok(Main.countVowels("rhythm") == 0); ok(Main.countVowels("Bridge Course") == 5);
        ok(Main.countVowels("!? a") == 1);
    """,
    3: """
        var input = new java.util.ArrayList<>(java.util.List.of("tiny", "first", "other", "longer", "first"));
        var original = new java.util.ArrayList<>(input);
        var result = Main.longWords(input);
        ok(result.equals(java.util.List.of("first", "other", "longer", "first")));
        ok(input.equals(original)); ok(result != input);
        result.clear(); ok(input.equals(original));
        ok(Main.longWords(java.util.List.of()).isEmpty());
        ok(Main.longWords(java.util.List.of("tiny")).isEmpty());
        ok(Main.longWords(java.util.List.of("first")).equals(java.util.List.of("first")));
        ok(Main.longestWord(java.util.List.of()).equals(""));
        ok(Main.longestWord(java.util.List.of("first", "other")).equals("first"));
        ok(Main.longestWord(input).equals("longer")); ok(input.equals(original));
    """,
    4: """
        BankAccount a = new BankAccount("Avery", 125);
        a.deposit(25); ok(a.withdraw(40)); ok(a.summary().equals("Avery has $110.00"));
        for (double n : new double[]{-1, 0, Double.NaN, Double.POSITIVE_INFINITY}) {
            fails(IllegalArgumentException.class, () -> a.deposit(n));
            ok(!a.withdraw(n)); ok(a.summary().equals("Avery has $110.00"));
        }
        ok(!a.withdraw(111)); ok(a.summary().equals("Avery has $110.00"));
        ok(a.withdraw(110)); ok(a.summary().equals("Avery has $0.00"));
        for (double n : new double[]{-1, Double.NaN, Double.POSITIVE_INFINITY})
            fails(IllegalArgumentException.class, () -> new BankAccount("Avery", n));
        BankAccount large = new BankAccount("Avery", Double.MAX_VALUE);
        String before = large.summary();
        fails(IllegalArgumentException.class, () -> large.deposit(Double.MAX_VALUE));
        ok(large.summary().equals(before));
        java.util.Locale.setDefault(java.util.Locale.GERMANY);
        ok(a.summary().equals("Avery has $0.00"));
        BankAccount alias = a; alias.deposit(1); ok(a.summary().equals("Avery has $1.00"));
    """,
    5: """
        ok(Main.scoreAnswer(" class ", "class") == 1);
        ok(Main.scoreAnswer("EqUaLs", "equals") == 1);
        ok(Main.scoreAnswer("==", "equals") == 0);
        ok(Main.scoreAnswer("", "class") == 0);
        ok(Main.scoreAnswer(new String("class"), new String("class")) == 1);
    """,
}

JAVA_INCOMPLETE = {
    1: ['Main.greeting("Avery")', 'Main.absoluteValue(-7)', 'Main.isEven(2)', 'Main.fizzBuzzLabel(15)'],
    2: ['Main.clampScore(50)', 'Main.totalPrice(42.5, true)', 'Main.countVowels("Avery")'],
    3: ['Main.longWords(java.util.List.of())', 'Main.longestWord(java.util.List.of())'],
    4: ['new BankAccount("Avery", 10).deposit(1)', 'new BankAccount("Avery", 10).withdraw(1)', 'new BankAccount("Avery", 10).summary()'],
    5: ['Main.scoreAnswer("class", "class")'],
}

CPP_CASES = {
    1: r"""
        assert(greeting("Avery") == "Hello, Avery!"); assert(greeting("") == "Hello, !");
        assert(absoluteValue(-7) == 7); assert(absoluteValue(0) == 0); assert(absoluteValue(12) == 12);
        fails<std::overflow_error>([]{ absoluteValue(std::numeric_limits<int>::min()); });
        assert(isEven(-2)); assert(isEven(0)); assert(!isEven(-1)); assert(!isEven(3));
        const std::vector<int> values{-15, -7, 0, 3, 5, 15, 16};
        const std::vector<std::string> labels{"FizzBuzz", "-7", "FizzBuzz", "Fizz", "Buzz", "FizzBuzz", "16"};
        for (std::size_t i=0; i<values.size(); i++) assert(fizzBuzzLabel(values[i]) == labels[i]);
    """,
    2: r"""
        const std::vector<int> values{std::numeric_limits<int>::min(), -1, 0, 50, 100, 101, std::numeric_limits<int>::max()};
        const std::vector<int> expected{0, 0, 0, 50, 100, 100, 100};
        for (std::size_t i=0; i<values.size(); i++) assert(clampScore(values[i]) == expected[i]);
        assert(std::abs(totalPrice(42.5, true) - 38.25) < 1e-10);
        assert(totalPrice(42.5, false) == 42.5); assert(totalPrice(0, true) == 0);
        assert(countVowels("") == 0); assert(countVowels("AEIOUaeiou") == 10);
        assert(countVowels("rhythm") == 0); assert(countVowels("Bridge Course") == 5);
        assert(countVowels("!? a") == 1);
    """,
    3: r"""
        std::vector<std::string> input{"tiny", "first", "other", "longer", "first"};
        const auto original = input; auto result = longWords(input);
        const std::vector<std::string> expected{"first", "other", "longer", "first"};
        assert(result == expected); assert(input == original);
        result.clear(); assert(input == original);
        assert(longWords({}).empty()); assert(longWords({"tiny"}).empty());
        assert(longWords({"first"}) == std::vector<std::string>{"first"});
        assert(longestWord({}) == ""); assert(longestWord({"first", "other"}) == "first");
        assert(longestWord(input) == "longer"); assert(input == original);
    """,
    4: r"""
        BankAccount a("Avery", 125); a.deposit(25); assert(a.withdraw(40));
        assert(a.summary() == "Avery has $110.00");
        for (double n : {-1.0, 0.0, std::numeric_limits<double>::quiet_NaN(), std::numeric_limits<double>::infinity()}) {
            fails<std::invalid_argument>([&]{ a.deposit(n); }); assert(!a.withdraw(n));
            assert(a.summary() == "Avery has $110.00");
        }
        assert(!a.withdraw(111)); assert(a.summary() == "Avery has $110.00");
        assert(a.withdraw(110)); assert(a.summary() == "Avery has $0.00");
        for (double n : {-1.0, std::numeric_limits<double>::quiet_NaN(), std::numeric_limits<double>::infinity()})
            fails<std::invalid_argument>([&]{ BankAccount bad("Avery", n); });
        BankAccount large("Avery", std::numeric_limits<double>::max());
        const auto before = large.summary();
        fails<std::invalid_argument>([&]{ large.deposit(std::numeric_limits<double>::max()); });
        assert(large.summary() == before);
        auto copy = a; copy.deposit(1); assert(a.summary() == "Avery has $0.00");
        assert(copy.summary() == "Avery has $1.00");
    """,
    6: r"""
        const std::vector<std::string> words{"vector", "compile", "header"};
        assert(scoreRound("vector", words) == 1); assert(scoreRound("other", words) == 0);
        assert(scoreRound("VECTOR", words) == 0); assert(scoreRound("vector", {}) == 0);
        assert(scoreRound("vector", {"vector", "vector"}) == 1);
        assert(words == std::vector<std::string>({"vector", "compile", "header"}));
    """,
}
CPP_INCOMPLETE = {
    1: ['greeting("Avery")', 'absoluteValue(-7)', 'isEven(2)', 'fizzBuzzLabel(15)'],
    2: ['clampScore(50)', 'totalPrice(42.5, true)', 'countVowels("Avery")'],
    3: ['longWords({})', 'longestWord({})'],
    4: ['BankAccount("Avery", 10).deposit(1)', 'BankAccount("Avery", 10).withdraw(1)', 'BankAccount("Avery", 10).summary()'],
    6: ['scoreRound("vector", {"vector"})'],
}


class NativePortTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.temporary = tempfile.TemporaryDirectory(prefix="bridge-native-")
        cls.addClassCleanup(cls.temporary.cleanup)
        cls.work = Path(cls.temporary.name)
        cls.programs = {}
        cls.javac = os.environ.get("JAVAC", "javac")
        cls.java = os.environ.get("JAVA", "java")
        cls.cpp = os.environ.get("CXX", "c++")
        for stage in range(1, 7):
            folder = next(ROOT.glob(f"PTJ{stage}-*"))
            for language in (["java", "cpp"] if stage <= 4 else ["java" if stage == 5 else "cpp"]):
                for side in ["starter", "solution"]:
                    source = folder/side/(language if stage <= 4 else "")
                    destination = cls.work/f"{stage}-{language}-{side}"
                    shutil.copytree(source, destination)
                    key = stage, language, side
                    cls.programs[key] = destination
                    if language == "java":
                        command = [cls.javac, "-Xlint:all", *[p.name for p in destination.glob("*.java")]]
                    else:
                        command = [cls.cpp, "-std=c++17", "-Wall", "-Wextra", "-pedantic", *[p.name for p in destination.glob("*.cpp")], "-o", "bridge"]
                    result = run(command, cwd=destination)
                    if result.returncode:
                        raise AssertionError(f"Original compile failed {key}: {result.stderr}")

    def test_java_learner_api_contracts(self):
        for stage in JAVA_CASES:
            for side in ["starter", "solution"]:
                with self.subTest(stage=stage, side=side):
                    folder = self.programs[stage, "java", side]
                    cases = JAVA_CASES[stage] if side == "solution" else "\n".join(
                        f"fails(UnsupportedOperationException.class, () -> {call});" for call in JAVA_INCOMPLETE[stage])
                    harness = """public class Checks {
                        static void ok(boolean value) { if (!value) throw new AssertionError("Contract mismatch"); }
                        static void fails(Class<?> type, Runnable callback) {
                            try { callback.run(); } catch (Exception error) {
                                if (type.isInstance(error)) return;
                                throw new AssertionError("Wrong exception", error);
                            } throw new AssertionError("Expected exception");
                        }
                        public static void main(String[] args) {
                    """+cases+'\nSystem.out.println("PASS"); } }'
                    (folder/"Checks.java").write_text(harness)
                    compiled = run([self.javac, "-Xlint:all", "Checks.java"], cwd=folder)
                    self.assertEqual(compiled.returncode, 0, compiled.stderr)
                    result = run([self.java, "-ea", "-cp", str(folder), "Checks"], cwd=folder)
                    self.assertEqual(result.returncode, 0, result.stderr)
                    self.assertEqual(result.stdout, "PASS\n")

    def test_cpp_learner_api_contracts(self):
        for stage in CPP_CASES:
            for side in ["starter", "solution"]:
                with self.subTest(stage=stage, side=side):
                    folder = self.programs[stage, "cpp", side]
                    # Compile originals separately above. This harness excludes only
                    # their demo driver so the published helpers can be exercised.
                    body = re.sub(r'int main\(\)\s*\{.*', '', (folder/"main.cpp").read_text(), flags=re.S)
                    cases = CPP_CASES[stage] if side == "solution" else "\n".join(
                        f"fails<std::logic_error>([]{{ {call}; }});" for call in CPP_INCOMPLETE[stage])
                    harness = """#include <cassert>
                        #include <cmath>
                        #include <limits>
                        #include <stdexcept>
                        #include <iostream>
                        #include <string>
                        #include <vector>
                        template<class E, class F> void fails(F callback) {
                            try { callback(); } catch (const E&) { return; }
                            throw std::runtime_error("Expected exception");
                        }
                    """+body+'\nint main() {\n'+cases+'\nstd::cout << "PASS\\n"; }'
                    (folder/"Checks.cpp").write_text(harness)
                    command = [self.cpp, "-std=c++17", "-Wall", "-Wextra", "-pedantic", "Checks.cpp"]
                    if stage == 4: command.append("BankAccount.cpp")
                    compiled = run([*command, "-o", "checks"], cwd=folder)
                    self.assertEqual(compiled.returncode, 0, compiled.stderr)
                    result = run([str(folder/"checks")], cwd=folder)
                    self.assertEqual(result.returncode, 0, result.stderr)
                    self.assertEqual(result.stdout, "PASS\n")

    def test_java_quiz_inputs_and_cancellation(self):
        folder = self.programs[5, "java", "solution"]
        for input_text, score, ended in [("class\nequals\n", 2, False), (" CLASS \nEqUaLs\n", 2, False),
                ("class\nwrong\n", 1, False), ("wrong\nwrong\n", 0, False), ("\n\n", 0, False),
                ("", 0, True), ("class\n", 1, True)]:
            with self.subTest(input=input_text):
                result = run([self.java, "-cp", str(folder), "Main"], cwd=folder, input_text=input_text)
                self.assertEqual(result.returncode, 0, result.stderr)
                self.assertTrue(result.stdout.endswith(f"Final score: {score}/2\n"))
                self.assertEqual("Input ended." in result.stdout, ended)

    def test_cpp_token_inputs_and_cancellation(self):
        folder = self.programs[6, "cpp", "solution"]
        for input_text, score, ended in [("vector compile header\n", 3, None), ("wrong WRONG nope\n", 0, None),
                ("vector vector vector\n", 3, None), ("\n  vector\n compile header\n", 3, None),
                ("", 0, 0), ("vector\n", 1, 1), ("vector compile\n", 2, 2)]:
            with self.subTest(input=input_text):
                result = run([str(folder/"bridge")], cwd=folder, input_text=input_text)
                self.assertEqual(result.returncode, 0, result.stderr)
                self.assertTrue(result.stdout.endswith(f"Score: {score}\n"))
                if ended is None: self.assertNotIn("Input ended", result.stdout)
                else: self.assertIn(f"Input ended after {ended} of 3 rounds.", result.stdout)

    def test_original_driver_demonstrations(self):
        outputs = {
            1: {"java": "Hello, Avery!\n7\ntrue\n[-7, -2, FizzBuzz, Fizz, Buzz, FizzBuzz]\n",
                "cpp": "Hello, Avery!\n7\ntrue\n-7 -2 FizzBuzz Fizz Buzz FizzBuzz \n"},
            2: {"java": "100\n38.25\n5\n", "cpp": "100\n38.25\n5\n"},
            3: {"java": "[bridge, typed, syntax]\nbridge\n", "cpp": "bridge typed syntax \nbridge\n"},
            4: {"java": "Avery has $110.00\n", "cpp": "Avery has $110.00\n"},
        }
        for stage in range(1, 5):
            for language in ["java", "cpp"]:
                folder = self.programs[stage, language, "solution"]
                command = [self.java, "-cp", str(folder), "Main"] if language == "java" else [str(folder/"bridge")]
                result = run(command, cwd=folder)
                self.assertEqual(result.returncode, 0, result.stderr)
                self.assertEqual(result.stdout, outputs[stage][language])

    def test_starter_drivers_do_not_claim_a_completed_solution(self):
        for (stage, language, side), folder in self.programs.items():
            if side != "starter": continue
            with self.subTest(stage=stage, language=language):
                command = [self.java, "-cp", str(folder), "Main"] if language == "java" else [str(folder/"bridge")]
                result = run(command, cwd=folder, input_text="class\nequals\n" if stage == 5 else "vector\n")
                self.assertNotEqual(result.returncode, 0)
                self.assertIn("Implement", result.stderr)

    def test_imported_briefs_and_reference_separation(self):
        for stage in range(1, 7):
            folder = next(ROOT.glob(f"PTJ{stage}-*"))
            for language in (["java", "cpp"] if stage <= 4 else ["java" if stage == 5 else "cpp"]):
                starter = folder/"starter"/(language if stage <= 4 else "")
                self.assertEqual((starter/"README.md").read_bytes(), (folder/"README.md").read_bytes())
                self.assertNotIn("solution", [p.name for p in starter.iterdir()])
                self.assertIn("## Checks", (starter/"README.md").read_text())
                for source in (folder/"solution").rglob("*"):
                    if source.suffix in {".java", ".cpp", ".h"}:
                        self.assertNotIn("TODO", source.read_text(), str(source))


if __name__ == "__main__":
    unittest.main()
