import java.util.Locale;

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
        if (!Double.isFinite(balance) || balance < 0) {
            throw new IllegalArgumentException("Starting balance must be finite and non-negative");
        }
        this.owner = owner;
        this.balance = balance;
    }

    /**
	 * @brief Add money to the account balance
	 *
	 * @param amount Amount to deposit
	 */
    public void deposit(double amount) {
        if (!Double.isFinite(amount) || amount <= 0 ||
            !Double.isFinite(balance + amount)) {
            throw new IllegalArgumentException("Deposit must be finite, positive and representable");
        }
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
        if (!Double.isFinite(amount) || amount <= 0 || amount > balance) {
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
        return String.format(Locale.ROOT, "%s has $%.2f", owner, balance);
    }
}
