# PTJ1 Syntax Translation Warmup

## Purpose and contract

Translate Python conditionals and helper functions into one typed target.
`greeting(name)` returns `Hello, <name>!`, including an empty name.
`absoluteValue(value)` returns the non-negative magnitude. The minimum signed
integer has no positive `int` counterpart: reject it with an arithmetic/overflow
exception. `isEven(value)` is true for all multiples of 2, including zero and
negative even values. `fizzBuzzLabel(value)` returns `FizzBuzz` when divisible
by both 3 and 5, `Fizz` for 3 only, `Buzz` for 5 only, otherwise decimal text.
The combined branch must win. Java and C++ use the same helper names.

Python starting behavior:
```python
def greeting(name):
    return f"Hello, {name}!"
def absolute_value(value):
    if value == -2147483648:
        raise OverflowError("magnitude does not fit in a 32-bit int")
    return abs(value)
def is_even(value):
    return value % 2 == 0
def fizz_buzz_label(value):
    if value % 15 == 0:
        return "FizzBuzz"
    if value % 3 == 0:
        return "Fizz"
    if value % 5 == 0:
        return "Buzz"
    return str(value)
```
Python integers can grow; Java `int` is 32-bit. C++ `int` width is
implementation-dependent; use the compiler's minimum value for its rejection
check. Ordinary fixtures here fit both. Both typed ports must guard negation
before applying it to the minimum value.

## Checks

Test names `Avery` and empty text; magnitudes -7, 0, 12 and minimum-int
rejection; parity -2, -1, 0, 3; labels -15, -7, 0, 3, 5, 15 and 16.
Java's bracketed collection display and C++'s space-separated display may differ;
compare individual returned values, not incidental formatting.

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
