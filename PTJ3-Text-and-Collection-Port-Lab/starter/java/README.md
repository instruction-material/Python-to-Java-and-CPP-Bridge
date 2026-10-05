# PTJ3 Text and Collection Port Lab

## Purpose and contract

Replace Python list operations with Java `List<String>`/`ArrayList<String>` or
C++ `vector<string>`. `longWords(words)` returns a NEW collection containing
words of length at least 5, preserving order and duplicates. `longestWord(words)`
returns the FIRST longest word, or empty text for an empty collection. Neither
helper changes its input. Shared fixtures contain ASCII strings; Java's UTF-16
length and C++ byte length are not general Unicode character counts.

Python starting behavior:
```python
def long_words(words):
    return [word for word in words if len(word) >= 5]
def longest_word(words):
    return max(words, key=len, default="")
```
Java's supplied `List.of(...)` cannot be mutated. Build a new `ArrayList` for a
result. C++ `const vector<string>&` borrows the input without copying the vector
and prevents changing it through that reference. `add` and `push_back` append
to the result. Valid indexes run from 0 through size minus one; an empty
collection has no valid element index. A range loop avoids indexing here.

## Checks

Test empty and one-word collections, lengths 4/5/6, duplicates, all-short
words, equal-length ties (`first`, `other` must select `first`) and the supplied
sample. Verify order, retained duplicates and unchanged input. Compare returned
elements; the drivers intentionally use different collection display styles.

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
