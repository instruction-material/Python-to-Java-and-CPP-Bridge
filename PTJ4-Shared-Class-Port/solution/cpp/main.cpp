#include <iostream>

#include "BankAccount.h"

int main() {
	BankAccount account("Avery", 125.0);
	account.deposit(25.0);
	account.withdraw(40.0);
	std::cout << account.summary() << "\n";
}
