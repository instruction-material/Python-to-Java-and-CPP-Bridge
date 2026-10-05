#include "BankAccount.h"

#include <cmath>
#include <iomanip>
#include <locale>
#include <sstream>
#include <stdexcept>
#include <utility>

/*****************
*   FUNCTIONS   *
*****************/

// Initialize the owner and starting balance
BankAccount::BankAccount(std::string owner, double balance)
    : owner_(std::move(owner)), balance_(balance) {
    if (!std::isfinite(balance) || balance < 0) {
        throw std::invalid_argument("Starting balance must be finite and non-negative");
    }
}

// Add money to the stored balance
void BankAccount::deposit(double amount) {
    if (!std::isfinite(amount) || amount <= 0 ||
        !std::isfinite(balance_ + amount)) {
        throw std::invalid_argument("Deposit must be finite, positive and representable");
    }
    balance_ += amount;
}

// Withdraw money only when the account has enough balance
bool BankAccount::withdraw(double amount) {
    // Reject withdrawals that exceed the current balance
    if (!std::isfinite(amount) || amount <= 0 || amount > balance_) {
        return false;
    }

    balance_ -= amount;
    return true;
}

// Build a readable account summary
std::string BankAccount::summary() const {
    std::ostringstream output;
    output.imbue(std::locale::classic());
    output << owner_ << " has $" << std::fixed << std::setprecision(2) << balance_;
    return output.str();
}
