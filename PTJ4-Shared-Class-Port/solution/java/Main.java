public class Main {
	public static void main(String[] args) {
		BankAccount account = new BankAccount("Avery", 125.0);
		account.deposit(25.0);
		account.withdraw(40.0);
		System.out.println(account.summary());
	}
}
