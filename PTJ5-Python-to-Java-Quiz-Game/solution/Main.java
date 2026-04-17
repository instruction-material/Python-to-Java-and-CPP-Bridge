import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int score = 0;

		System.out.print("What keyword defines a class in Java? " );
		String first = scanner.nextLine();
		if (first.trim().equalsIgnoreCase("class"))
			score++;

		System.out.print("What method compares string contents in Java? " );
		String second = scanner.nextLine();
		if (second.trim().equalsIgnoreCase("equals"))
			score++;

		System.out.println("Final score: " + score + "/2");
	}
}
