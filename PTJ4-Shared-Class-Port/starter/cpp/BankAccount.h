#pragma once

#include <string>

class BankAccount {
public:
	BankAccount(std::string owner, double balance);
	void deposit(double amount);
	bool withdraw(double amount);
	std::string summary() const;

private:
	std::string owner_;
	double balance_;
};
