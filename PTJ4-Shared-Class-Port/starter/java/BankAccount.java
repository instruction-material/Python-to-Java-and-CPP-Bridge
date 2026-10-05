import java.util.Locale;

public class BankAccount {
    private final String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        if (!Double.isFinite(balance) || balance < 0) {
            throw new IllegalArgumentException("Starting balance must be finite and non-negative");
        }
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        // TODO: validate a finite positive deposit, then update balance
        throw new UnsupportedOperationException("Implement deposit");
    }

    public boolean withdraw(double amount) {
        // TODO: reject invalid or unaffordable amounts without changing balance
        throw new UnsupportedOperationException("Implement withdraw");
    }

    public String summary() {
        // TODO: return owner + " has $" + balance formatted to two decimal places
        throw new UnsupportedOperationException("Implement summary");
    }
}
