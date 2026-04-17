public class BankAccount {
	private final String owner;
	private double balance;

	public BankAccount(String owner, double balance) {
		this.owner = owner;
		this.balance = balance;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public boolean withdraw(double amount) {
		if (amount > balance)
			return false;
		balance -= amount;
		return true;
	}

	public String summary() {
		return owner + " has $" + balance;
	}
}
