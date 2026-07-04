#include "BankAccount.h"

#include <utility>


/*****************
*   FUNCTIONS   *
*****************/

// Initialize the owner and starting balance
BankAccount::BankAccount(std::string owner, double balance)
	: owner_(std::move(owner)), balance_(balance) {}

// Add money to the stored balance
void BankAccount::deposit(double amount) {
	balance_ += amount;
}

// Withdraw money only when the account has enough balance
bool BankAccount::withdraw(double amount) {
	// Reject withdrawals that exceed the current balance
	if (amount > balance_) {
		return false;
	}

	balance_ -= amount;
	return true;
}

// Build a readable account summary
std::string BankAccount::summary() const {
	return owner_ + " has $" + std::to_string(balance_);
}
