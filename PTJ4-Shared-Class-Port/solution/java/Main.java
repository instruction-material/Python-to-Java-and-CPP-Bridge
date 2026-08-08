// Run the Java version of the shared bank account port
public class Main {
    private static final String SAMPLE_OWNER = "Avery";
    private static final double STARTING_BALANCE = 125.0;
    private static final double DEPOSIT_AMOUNT = 25.0;
    private static final double WITHDRAW_AMOUNT = 40.0;

    /**
	 * @brief Run the shared class port example
	 *
	 * @param args Command-line arguments
	 */
    public static void main(String[] args) {
        BankAccount account = new BankAccount(SAMPLE_OWNER, STARTING_BALANCE);
        account.deposit(DEPOSIT_AMOUNT);
        account.withdraw(WITHDRAW_AMOUNT);
        System.out.println(account.summary());
    }
}
