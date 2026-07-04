
/****************
*   SOLUTION   *
****************/

#pragma once

#include <string>


/*************
*   TYPES   *
*************/

// Store a simple bank account with deposit and withdrawal behavior
class BankAccount {
public:
	/**
	 * @brief Build a bank account for one owner
	 *
	 * @param owner Account owner name
	 *
	 * @param balance Starting balance
	 */
	BankAccount(std::string owner, double balance);

	/**
	 * @brief Add money to the account balance
	 *
	 * @param amount Amount to deposit
	 */
	void deposit(double amount);

	/**
	 * @brief Attempt to withdraw money from the account
	 *
	 * @param amount Amount to withdraw
	 *
	 * @return True when the withdrawal succeeds
	 */
	bool withdraw(double amount);

	/**
	 * @brief Build a readable account summary
	 *
	 * @return Summary string for the account
	 */
	std::string summary() const;

private:
	std::string owner_;
	double balance_;
};
