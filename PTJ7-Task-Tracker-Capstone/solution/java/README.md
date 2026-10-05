# Task Tracker Capstone Port

Choose Java **or** C++ and port the supplied Python task tracker. This authored
bridge capstone combines functions, collection traversal, private object state,
validation and a console command loop. It follows the original PTJ projects;
completing both target languages is optional. It is not a GUI, persistence or
production task-management project.

The provided driver and state declarations compile before the six TODO methods
are implemented. An unfinished method raises an implementation reminder. Keep
the target-language reference under `solution/` separate from learner work.
The Python program below is the starting behavior to translate, not a completed
Java or C++ answer.

## Files and build workflow

- Java: import `starter/java`, containing `Main.java`, `TaskTracker.java` and this
  brief. `Main` owns the supplied console loop; `TaskTracker` owns the learner work.
- C++: import `starter/cpp`, containing `main.cpp`, `TaskTracker.cpp`,
  `TaskTracker.h` and this brief. Keep the header and both source files together.
- The site's source workspace supports editing, saving and ZIP download. Extract
  the ZIP before compiling. The Java browser runner is a limited beginner
  preview; use a native JDK for this object-based capstone. C++ uses a native
  compiler. Imported source does not execute automatically.
- Java 21: from the extracted Java directory, run
  `javac -Xlint:all Main.java TaskTracker.java`, then `java Main`.
- C++17 on macOS/Linux: from the extracted C++ directory, run
  `c++ -std=c++17 -Wall -Wextra -pedantic -I. main.cpp TaskTracker.cpp -o tracker`,
  then `./tracker`. Unused-parameter warnings in untouched TODO methods are
  expected; compiler errors still need correction.
- Python 3.9+: `python3 python/task_tracker.py` from this project root. The full
  Python source is included below so the imported brief is self-contained.

## Required public operations

Use the provided camelCase Java/C++ signatures. Python uses snake_case names.
Each tracker starts empty, with the next ID equal to 1. Its collection stores
each task's ID, normalized title and done flag; new tasks start open.

1. `normalizeTitle(title)` is a static helper. Trim only outer ASCII spaces
   (U+0020), preserving case, punctuation, duplicate titles and internal spaces.
   The normalized result must contain 1–60 printable ASCII characters (U+0020
   through U+007E). Reject blank, tab/newline, non-ASCII, overlong and Java-null
   titles with `title`. Return the normalized string without changing a tracker.
2. `addTask(title)` normalizes before changing anything. Append an open task and
   return its ID. Allocate IDs consecutively for **successful** adds; rejected
   adds never consume an ID. Each tracker allows at most 100 successful adds
   over its lifetime. A further valid add rejects with `limit`. Removing a task
   never recycles its ID or restores this lifetime budget. Invalid titles still
   reject with `title` when the budget is exhausted.
3. `completeTask(id)` accepts a positive 32-bit integer. A nonpositive ID rejects
   with `id` and changes nothing. Mark an existing open task done and return
   true. Unknown IDs and already-done tasks return false without a change.
4. `removeTask(id)` uses the same ID domain. Remove an existing task and return
   true, preserving the order of the other tasks. Unknown IDs return false.
5. `listTasks(openOnly)` accepts a Boolean and returns a **fresh** ordered list
   of strings. Use `id | OPEN | title` or `id | DONE | title`, with exactly one
   space on each side of each pipe. True includes only open tasks; false includes
   all tasks. Return an empty list when none match. Editing the returned list
   must not change the tracker.
6. `summary()` returns exactly `Total: N | Open: O | Done: D` for the current
   collection, where N = O + D. Removed tasks do not count. It changes nothing.

Java rejects invalid values with `IllegalArgumentException`; C++ uses
`std::invalid_argument`; Python uses `ValueError`. Their messages are the exact
keys `title`, `id` or `limit` above. Typed Java/C++ API callers supply the declared
argument types. Rejections preserve task titles, flags, order and next ID.
Separate trackers have separate state. A Java/Python alias shares the same
object; an ordinary C++ tracker copy has its own value state.

## Supplied console protocol

The driver is already implemented and may remain unchanged. Commands are
case-sensitive, one per line. LF and CRLF line endings are accepted. `ADD ` takes
the rest of the line as its title. `DONE ` and `REMOVE ` take a canonical positive
decimal ID: no sign, leading zero, extra whitespace or value above 2147483647.

| Command | Output after the opening help line |
| --- | --- |
| `ADD <title>` | `ADDED <id>` |
| `DONE <id>` / `REMOVE <id>` | `CHANGED` or `UNCHANGED` |
| `LIST` / `OPEN` | Each formatted row, or `(empty)` |
| `SUMMARY` | The exact summary string |
| `QUIT` | Stop without processing later lines |

Invalid commands print `ERROR: command`; malformed/nonpositive console IDs print
`ERROR: id`; rejected adds print `ERROR: title` or `ERROR: limit`. Continue after
an error with unchanged state. EOF stops cleanly, including an unterminated last
line. The program does not write files or require external services.

## Checks

For this input (outer spaces on the first title are intentional):

```text
SUMMARY
ADD  Read notes
ADD Read notes
DONE 1
DONE 1
OPEN
REMOVE 1
ADD Plan check
LIST
SUMMARY
QUIT
ADD Ignored
```

After the opening help line, the output is:

```text
Total: 0 | Open: 0 | Done: 0
ADDED 1
ADDED 2
CHANGED
UNCHANGED
2 | OPEN | Read notes
CHANGED
ADDED 3
2 | OPEN | Read notes
3 | OPEN | Plan check
Total: 2 | Open: 2 | Done: 0
```

Also check a blank/title with a tab/non-ASCII title, exactly 60 versus 61 title
characters, unknown and nonpositive IDs, an empty/all-done open filter, removal
from the first/middle/last position, independent trackers and returned-list
mutation. Reach the 100-add lifetime boundary, remove an item, and confirm the
next valid add still rejects. Record state before each rejection and after it.
Run the same LF, CRLF and EOF console fixtures in Python and the chosen target.

## Walkthrough and completion

1. Predict the sample outputs before running Python. Draw each state transition
   and identify the next-ID value independently of collection size.
2. Map the Python object/record/list representation into the provided target
   declarations. Explain where the language requires explicit types and files.
3. Implement normalization and one successful add. Verify a rejected add leaves
   the next successful ID unchanged before adding other operations.
4. Implement completion/removal, then filtering and summary. Check one normal
   and one boundary case after each operation, including a fresh-output check.
5. Compile and run the supplied command loop. Diagnose one compiler error and
   one behavior mismatch using the same fixture in both languages.

Completion requires one working target port, the shared output/edge-case record,
unchanged-state evidence, exact build/run commands, and a short explanation of
one type or object-model difference. An instructor can pause at each checkpoint
to ask for a prediction, have the learner explain a trace, and let the learner
correct the mismatch. Compare the separate reference only after a working draft.

Optional extension: implement the second target language and compare Java
reference aliasing with C++ value copying. Persistence, sorting or a GUI require
a separately agreed behavior contract; none is required for this capstone.

## Starting Python behavior

The complete supplied source follows. It is also retained at
`python/task_tracker.py` in this project.

```python
"""Supplied Python behavior to port; target-language answers live separately."""

import sys


class TaskTracker:
    def __init__(self):
        self._tasks = []
        self._next_id = 1

    @staticmethod
    def normalize_title(title):
        if not isinstance(title, str):
            raise ValueError("title")
        title = title.strip(" ")
        if not 1 <= len(title) <= 60 or any(not 32 <= ord(c) <= 126 for c in title):
            raise ValueError("title")
        return title

    def add_task(self, title):
        title = self.normalize_title(title)
        if self._next_id > 100:
            raise ValueError("limit")
        task_id = self._next_id
        self._tasks.append([task_id, title, False])
        self._next_id += 1
        return task_id

    def complete_task(self, task_id):
        if type(task_id) is not int or not 1 <= task_id <= 2147483647:
            raise ValueError("id")
        for task in self._tasks:
            if task[0] == task_id and not task[2]:
                task[2] = True
                return True
        return False

    def remove_task(self, task_id):
        if type(task_id) is not int or not 1 <= task_id <= 2147483647:
            raise ValueError("id")
        for index, task in enumerate(self._tasks):
            if task[0] == task_id:
                del self._tasks[index]
                return True
        return False

    def list_tasks(self, open_only):
        if type(open_only) is not bool:
            raise ValueError("filter")
        return [f"{i} | {'DONE' if done else 'OPEN'} | {title}"
                for i, title, done in self._tasks if not open_only or not done]

    def summary(self):
        done = sum(task[2] for task in self._tasks)
        total = len(self._tasks)
        return f"Total: {total} | Open: {total - done} | Done: {done}"


def parse_id(raw):
    if not raw or raw[0] == "0" or any(c not in "0123456789" for c in raw):
        raise ValueError("id")
    if len(raw) > 10 or int(raw) > 2147483647:
        raise ValueError("id")
    return int(raw)


def main():
    tracker = TaskTracker()
    print("Task tracker. Commands: ADD <title>, DONE <id>, REMOVE <id>, LIST, OPEN, SUMMARY, QUIT.")
    for line in sys.stdin:
        line = line.removesuffix("\n").removesuffix("\r")
        try:
            if line == "QUIT":
                break
            if line.startswith("ADD "):
                print(f"ADDED {tracker.add_task(line[4:])}")
            elif line.startswith("DONE "):
                print("CHANGED" if tracker.complete_task(parse_id(line[5:])) else "UNCHANGED")
            elif line.startswith("REMOVE "):
                print("CHANGED" if tracker.remove_task(parse_id(line[7:])) else "UNCHANGED")
            elif line in ("LIST", "OPEN"):
                rows = tracker.list_tasks(line == "OPEN")
                print("\n".join(rows) if rows else "(empty)")
            elif line == "SUMMARY":
                print(tracker.summary())
            else:
                raise ValueError("command")
        except ValueError as error:
            print(f"ERROR: {error}")


if __name__ == "__main__":
    main()
```
