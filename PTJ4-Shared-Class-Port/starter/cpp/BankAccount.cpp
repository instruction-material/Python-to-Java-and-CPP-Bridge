#include "BankAccount.h"

BankAccount::BankAccount(std::string owner, double balance)
	: owner_(std::move(owner)), balance_(balance) {}

void BankAccount::deposit(double amount) {
	// TODO: add amount to the balance
}

bool BankAccount::withdraw(double amount) {
	// TODO: reject withdrawals larger than the balance
	return false;
}

std::string BankAccount::summary() const {
	// TODO: return a compact summary string
	return "";
}
