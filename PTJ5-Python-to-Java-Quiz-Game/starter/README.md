# PTJ5 Python to Java Quiz Game

## Purpose and contract

Choose this exit branch when Java is the next course. Preserve a two-question
Python console quiz while learning `Scanner`, line input and string equality.
`scoreAnswer(answer, expectedAnswer)` returns 1 for a trimmed, case-insensitive
content match, otherwise 0. Inputs are non-null ASCII text. The supplied driver
asks for `class` and `equals`, accumulates points and prints `Final score: N/2`.
EOF cancels remaining questions, prints `Input ended.`, and reports the score
earned so far. An empty line is an incorrect answer; it is not EOF.

Python starting behavior:
```python
def score_answer(answer, expected_answer):
    return int(answer.strip().lower() == expected_answer.lower())
score = 0
for prompt, expected in [("What keyword defines a class in Java? ", "class"),
                         ("What method compares string contents in Java? ", "equals")]:
    try:
        answer = input(prompt)
    except EOFError:
        print("Input ended.")
        break
    score += score_answer(answer, expected)
print(f"Final score: {score}/2")
```
Java `==` compares String references; `equals` compares content and
`equalsIgnoreCase` handles this ASCII case-folding contract. `nextLine` reads
the entire answer. The supplied `hasNextLine` check handles cancellation before
reading, so input exhaustion does not crash the program. Only the scoring
helper is learner work; prompts and input lifecycle are supplied.

## Checks

Run correct/correct, correct/wrong, wrong/wrong, surrounding spaces and mixed
case, blank lines, immediate EOF and EOF after the first answer. Scores must
stay between 0 and 2; cancellation must preserve points already earned.

## Workflow and comparison

Choose Java or C++ for the first pass; the other target is optional. The Python
behavior below is the starting point, so the exercise is a port rather than a
new algorithm. Predict results before editing. Keep function signatures and
driver code, replace one TODO at a time, compile, then test the same fixtures.
Starters compile but deliberately stop at an unfinished callable. Completed
reference code is only in `solution/`; compare it after a working draft.

Java: from the chosen `starter/java/` folder (or `starter/` for PTJ5), run
`javac *.java` then `java Main`. Use JDK 21 or later. A public class named `Main`
belongs in `Main.java`. C++: from `starter/cpp/` (or `starter/` for PTJ6), run
`c++ -std=c++17 -Wall -Wextra -pedantic *.cpp -o bridge` then `./bridge`.
PTJ4 needs both `.cpp` files and the header, not only `main.cpp`.
In the site IDE, confirm the import, select the matching language, and keep the
whole chosen-language folder together. Compile errors precede execution;
runtime errors happen after compilation. Fix the first relevant diagnostic,
then compare behavior rather than compiler-specific wording.

## Guided use and completion evidence

For independent study, read the contract, predict the listed cases, implement,
and record actual versus expected results. In a facilitated session, pause at
the same checkpoints to explain a type, loop, or state change before running.
Record one compiler diagnostic, its cause and correction, all boundary cases,
and one difference from Python. A passing sample alone is not completion.
This material is authored bridge coursework; its PTJ ordering is preserved.
It does not claim to be an original Juni assignment export.
