import java.util.Arrays;

public class Main {
	static String greeting(String name) {
		return "Hello, " + name + "!";
	}

	static int absoluteValue(int value) {
		return value < 0 ? -value : value;
	}

	static boolean isEven(int value) {
		return value % 2 == 0;
	}

	static String fizzBuzzLabel(int value) {
		if (value % 15 == 0)
			return "FizzBuzz";
		if (value % 3 == 0)
			return "Fizz";
		if (value % 5 == 0)
			return "Buzz";
		return Integer.toString(value);
	}

	public static void main(String[] args) {
		int[] checks = { -7, -2, 0, 3, 5, 15 };
		System.out.println(greeting("Avery"));
		System.out.println(absoluteValue(-7));
		System.out.println(isEven(12));
		System.out.println(Arrays.toString(Arrays.stream(checks).mapToObj(Main::fizzBuzzLabel).toArray()));
	}
}
