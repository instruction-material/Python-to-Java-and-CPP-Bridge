import java.util.Arrays;

public class Main {
    static String greeting(String name) {
        // TODO: return "Hello, <name>!"
        throw new UnsupportedOperationException("Implement the documented contract");
    }

    static int absoluteValue(int value) {
        // TODO: return the non-negative version of value
        throw new UnsupportedOperationException("Implement the documented contract");
    }

    static boolean isEven(int value) {
        // TODO: return true when value is even
        throw new UnsupportedOperationException("Implement the documented contract");
    }

    static String fizzBuzzLabel(int value) {
        // TODO: return Fizz, Buzz, FizzBuzz, or the number as text
        throw new UnsupportedOperationException("Implement the documented contract");
    }

    public static void main(String[] args) {
        int[] checks = {-7, -2, 0, 3, 5, 15};
        System.out.println(greeting("Avery"));
        System.out.println(absoluteValue(-7));
        System.out.println(isEven(12));
        System.out.println(Arrays.toString(
            Arrays.stream(checks).mapToObj(Main::fizzBuzzLabel).toArray()));
    }
}
