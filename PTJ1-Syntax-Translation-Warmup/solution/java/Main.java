import java.util.Arrays;

// Demonstrate direct syntax translation from Python-style examples to Java
public class Main {
	private static final String SAMPLE_NAME = "Avery";
	private static final int NEGATIVE_SAMPLE_VALUE = -7;
	private static final int EVEN_SAMPLE_VALUE = 12;
	private static final int EVEN_DIVISOR = 2;
	private static final int FIZZ_DIVISOR = 3;
	private static final int BUZZ_DIVISOR = 5;
	private static final int FIZZ_BUZZ_DIVISOR = FIZZ_DIVISOR * BUZZ_DIVISOR;
	private static final int[] CHECK_VALUES = { -7, -2, 0, 3, 5, 15 };

	/**
	 * @brief Build a greeting for one name
	 *
	 * @param name Name to greet
	 *
	 * @return Greeting text
	 */
	static String greeting(String name) {
		return "Hello, " + name + "!";
	}

	/**
	 * @brief Return the absolute value of an integer
	 *
	 * @param value Integer to convert
	 *
	 * @return Non-negative absolute value
	 */
	static int absoluteValue(int value) {
		return value < 0 ? -value : value;
	}

	/**
	 * @brief Check whether an integer is even
	 *
	 * @param value Integer to check
	 *
	 * @return True when the value is even
	 */
	static boolean isEven(int value) {
		return value % EVEN_DIVISOR == 0;
	}

	/**
	 * @brief Return the FizzBuzz label for one integer
	 *
	 * @param value Integer to label
	 *
	 * @return FizzBuzz label or the original value as text
	 */
	static String fizzBuzzLabel(int value) {
		// Prefer the combined label when both divisibility rules match
		if (value % FIZZ_BUZZ_DIVISOR == 0) {
			return "FizzBuzz";
		}

		// Apply the fizz label for multiples of three
		if (value % FIZZ_DIVISOR == 0) {
			return "Fizz";
		}

		// Apply the buzz label for multiples of five
		if (value % BUZZ_DIVISOR == 0) {
			return "Buzz";
		}

		return Integer.toString(value);
	}

	/**
	 * @brief Print the warmup examples
	 *
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		System.out.println(greeting(SAMPLE_NAME));
		System.out.println(absoluteValue(NEGATIVE_SAMPLE_VALUE));
		System.out.println(isEven(EVEN_SAMPLE_VALUE));
		System.out.println(Arrays.toString(Arrays.stream(CHECK_VALUES).mapToObj(Main::fizzBuzzLabel).toArray()));
	}
}
