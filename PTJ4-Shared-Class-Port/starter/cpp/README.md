# PTJ4 Shared Class Port

## Purpose and contract

Port a small stateful Python account into Java class files or C++ header/source
files. Constructor `(owner, balance)` keeps the owner and a finite, non-negative
starting balance; reject an invalid balance with an argument exception.
`deposit(amount)` accepts finite positive amounts whose sum remains finite;
otherwise throw an argument exception WITHOUT changing state.
`withdraw(amount)` returns true and subtracts a finite positive affordable
amount; otherwise returns false WITHOUT changing state. Zero transactions are
invalid. `summary()` returns `<owner> has $<balance>` with exactly two decimal
places using a decimal point, independent of machine locale.

Python starting behavior:
```python
import math
class BankAccount:
    def __init__(self, owner, balance):
        if not math.isfinite(balance) or balance < 0:
            raise ValueError("invalid starting balance")
        self.owner, self.balance = owner, balance
    def deposit(self, amount):
        if not math.isfinite(amount) or amount <= 0 or not math.isfinite(self.balance + amount):
            raise ValueError("invalid deposit")
        self.balance += amount
    def withdraw(self, amount):
        if not math.isfinite(amount) or amount <= 0 or amount > self.balance:
            return False
        self.balance -= amount
        return True
    def summary(self):
        return f"{self.owner} has ${self.balance:.2f}"
```
This is a state/encapsulation exercise using doubles; production money handling
needs a deliberate exact representation. Keep `balance` private. Java object
variables hold references; assigning one to another does not clone the object.
C++ account variables here hold values; copying one makes a separate account.
The `.h` declares the interface; `.cpp` defines behavior; compile both source
files to avoid missing-definition linker errors. `<utility>` declares `move`.

## Checks

125 + 25 - 40 yields `Avery has $110.00`. Test full withdrawal to zero,
overdraft rejection, negative/zero/non-finite transactions, invalid starting
balances, and deposit overflow. After every rejected transaction, the summary
must be unchanged. Explain object aliasing versus value copying separately.

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
