public class BankAccount {
	private final String owner;
	private double balance;

	public BankAccount(String owner, double balance) {
		this.owner = owner;
		this.balance = balance;
	}

	public void deposit(double amount) {
		// TODO: add amount to balance
	}

	public boolean withdraw(double amount) {
		// TODO: reject withdrawals larger than balance
		return false;
	}

	public String summary() {
		// TODO: return a compact summary string
		return "";
	}
}
