# PTJ2 Function Port Pack

## Purpose and contract

Keep Python helper behavior while adding parameter and return types.
`clampScore(score)` limits any integer to the inclusive range 0 through 100.
`totalPrice(subtotal, member)` returns 90% of the subtotal for members and the
unchanged subtotal otherwise. Subtotals are finite, non-negative doubles.
`countVowels(text)` counts ASCII a/e/i/o/u, case-insensitively; repetitions count
and y does not. Use ASCII text for shared Java/C++ fixtures: Java characters
and C++ string bytes do not provide identical Unicode processing.

Python starting behavior:
```python
def clamp_score(score):
    return max(0, min(100, score))
def total_price(subtotal, member):
    return subtotal * 0.9 if member else subtotal
def count_vowels(text):
    return sum(letter.lower() in "aeiou" for letter in text)
```
A return type promises the kind of value produced on every reachable path.
`void` is appropriate for a side effect with no returned value, not these
helpers. `bool` in C++ and `boolean` in Java hold true/false values. Floating
point results need a tolerance rather than exact decimal equality.

## Checks

Clamp -1, 0, 50, 100, 101 and the integer extremes. Price 0 and 42.5 with both
membership values (member 42.5 is 38.25). Count empty text, `AEIOUaeiou`,
`rhythm`, `Bridge Course` (5), and punctuation. Prove arguments remain unchanged.

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
