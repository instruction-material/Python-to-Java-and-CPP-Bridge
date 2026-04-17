import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int score = 0;

		System.out.print("What keyword defines a class in Java? " );
		String first = scanner.nextLine();
		// TODO: increment score when the answer is class

		System.out.print("What method compares string contents in Java? " );
		String second = scanner.nextLine();
		// TODO: increment score when the answer is equals

		System.out.println("Final score: " + score + "/2");
	}
}
