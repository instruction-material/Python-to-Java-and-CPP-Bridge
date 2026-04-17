#include "BankAccount.h"

#include <utility>

BankAccount::BankAccount(std::string owner, double balance)
	: owner_(std::move(owner)), balance_(balance) {}

void BankAccount::deposit(double amount) {
	balance_ += amount;
}

bool BankAccount::withdraw(double amount) {
	if (amount > balance_)
		return false;
	balance_ -= amount;
	return true;
}

std::string BankAccount::summary() const {
	return owner_ + " has $" + std::to_string(balance_);
}
