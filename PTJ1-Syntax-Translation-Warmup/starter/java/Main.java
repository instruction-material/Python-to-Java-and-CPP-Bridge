import java.util.Arrays;

public class Main {
	static String greeting(String name) {
		// TODO: return "Hello, <name>!"
		return "";
	}

	static int absoluteValue(int value) {
		// TODO: return the non-negative version of value
		return 0;
	}

	static boolean isEven(int value) {
		// TODO: return true when value is even
		return false;
	}

	static String fizzBuzzLabel(int value) {
		// TODO: return Fizz, Buzz, FizzBuzz, or the number as text
		return "";
	}

	public static void main(String[] args) {
		int[] checks = { -7, -2, 0, 3, 5, 15 };
		System.out.println(greeting("Avery"));
		System.out.println(absoluteValue(-7));
		System.out.println(isEven(12));
		System.out.println(Arrays.toString(Arrays.stream(checks).mapToObj(Main::fizzBuzzLabel).toArray()));
	}
}
