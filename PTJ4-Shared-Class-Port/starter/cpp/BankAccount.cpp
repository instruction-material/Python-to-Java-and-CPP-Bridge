#include "BankAccount.h"

#include <cmath>
#include <iomanip>
#include <locale>
#include <sstream>
#include <stdexcept>
#include <utility>

BankAccount::BankAccount(std::string owner, double balance)
    : owner_(std::move(owner)), balance_(balance) {
    if (!std::isfinite(balance) || balance < 0) {
        throw std::invalid_argument("Starting balance must be finite and non-negative");
    }
}

void BankAccount::deposit(double amount) {
    // TODO: validate a finite positive deposit, then update balance
    throw std::logic_error("Implement deposit");
}

bool BankAccount::withdraw(double amount) {
    // TODO: reject invalid or unaffordable amounts without changing balance
    throw std::logic_error("Implement withdraw");
}

std::string BankAccount::summary() const {
    // TODO: return owner + " has $" + balance formatted to two decimal places
    throw std::logic_error("Implement summary");
}
