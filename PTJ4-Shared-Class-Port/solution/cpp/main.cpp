#include <iostream>

#include "BankAccount.h"


/*****************
*   CONSTANTS   *
*****************/

const std::string SAMPLE_OWNER = "Avery";
constexpr double STARTING_BALANCE = 125.0;
constexpr double DEPOSIT_AMOUNT = 25.0;
constexpr double WITHDRAW_AMOUNT = 40.0;


/*****************
*   FUNCTIONS   *
*****************/

/**
 * @brief Run the shared class port example
 *
 * @return Process exit code
 */
int main() {
	BankAccount account(SAMPLE_OWNER, STARTING_BALANCE);
	account.deposit(DEPOSIT_AMOUNT);
	account.withdraw(WITHDRAW_AMOUNT);
	std::cout << account.summary() << "\n";
}
