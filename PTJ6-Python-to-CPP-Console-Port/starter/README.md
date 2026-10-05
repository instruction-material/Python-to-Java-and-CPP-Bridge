# PTJ6 Python to C++ Console Port

## Purpose and contract

Choose this exit branch when C++ is the next course. `scoreRound(guess,
secretWords)` returns 1 for exact membership, otherwise 0; do not mutate the
vector. Matching is case-sensitive. The supplied driver accepts at most three
whitespace-delimited guesses against `vector`, `compile`, `header`. Repeating a
matching word counts once per accepted round. EOF cancels remaining rounds:
report `Input ended after N of 3 rounds.` and the score already earned. Failed
input must never replay the previous guess.

Python starting behavior for the helper:
```python
def score_round(guess, secret_words):
    return int(guess in secret_words)
```
Python `input()` reads a full line; `cin >> guess` reads a whitespace-delimited
token and skips blank whitespace. Thus `vector compile` supplies TWO C++ rounds
and blank lines supply none. To port a line-based interface instead, use
`getline` consistently and document that changed input contract. This pack
deliberately teaches token input. `const string&` and `const vector<string>&`
borrow inputs for reading; no manual allocation or raw pointers are needed.
Includes declare library types/functions, and compilation must precede running.

## Checks

Test membership/non-membership, empty and duplicate secret lists, wrong case,
three correct tokens, all incorrect tokens, repeated matches, blank whitespace,
immediate EOF and EOF after ONE matching token. The last case must score 1,
not 3. Print `Score: N` and keep the final score in the range 0 through 3.

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
