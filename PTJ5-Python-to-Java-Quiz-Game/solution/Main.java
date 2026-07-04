import java.util.Scanner;

// Run a short Java syntax quiz ported from a Python console pattern
public class Main {
	private static final int STARTING_SCORE = 0;
	private static final int POINTS_PER_CORRECT_ANSWER = 1;
	private static final int QUESTION_COUNT = 2;
	private static final String CLASS_KEYWORD_QUESTION = "What keyword defines a class in Java? ";
	private static final String STRING_COMPARE_QUESTION = "What method compares string contents in Java? ";
	private static final String CLASS_KEYWORD_ANSWER = "class";
	private static final String STRING_COMPARE_ANSWER = "equals";

	/**
	 * @brief Ask one quiz question and check the answer
	 *
	 * @param scanner Scanner used to read input
	 *
	 * @param prompt Question prompt to show
	 *
	 * @param expectedAnswer Case-insensitive expected answer
	 *
	 * @return Points earned for this question
	 */
	private static int askQuestion(
		Scanner scanner,
		String prompt,
		String expectedAnswer
	) {
		System.out.print(prompt);
		String answer = scanner.nextLine();

		// Award a point only when the trimmed answer matches
		if (answer.trim().equalsIgnoreCase(expectedAnswer)) {
			return POINTS_PER_CORRECT_ANSWER;
		}

		return STARTING_SCORE;
	}

	/**
	 * @brief Run the quiz game
	 *
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int score = STARTING_SCORE;

		score += askQuestion(scanner, CLASS_KEYWORD_QUESTION, CLASS_KEYWORD_ANSWER);
		score += askQuestion(scanner, STRING_COMPARE_QUESTION, STRING_COMPARE_ANSWER);

		System.out.println("Final score: " + score + "/" + QUESTION_COUNT);
	}
}
