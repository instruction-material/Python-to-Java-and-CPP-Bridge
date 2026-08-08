// Store a simple bank account with deposit and withdrawal behavior
public class BankAccount {
    private final String owner;
    private double balance;

    /**
	 * @brief Build a bank account for one owner
	 *
	 * @param owner Account owner name
	 *
	 * @param balance Starting balance
	 */
    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    /**
	 * @brief Add money to the account balance
	 *
	 * @param amount Amount to deposit
	 */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
	 * @brief Attempt to withdraw money from the account
	 *
	 * @param amount Amount to withdraw
	 *
	 * @return True when the withdrawal succeeds
	 */
    public boolean withdraw(double amount) {
        // Reject withdrawals that exceed the current balance
        if (amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    /**
	 * @brief Build a readable account summary
	 *
	 * @return Summary string for the account
	 */
    public String summary() {
        return owner + " has $" + balance;
    }
}
