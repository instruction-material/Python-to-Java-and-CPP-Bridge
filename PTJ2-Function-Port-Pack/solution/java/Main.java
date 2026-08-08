// Demonstrate function ports from Python into Java
public class Main {
    private static final int MINIMUM_SCORE = 0;
    private static final int MAXIMUM_SCORE = 100;
    private static final double MEMBER_DISCOUNT_RATE = 0.9;
    private static final int SAMPLE_SCORE = 140;
    private static final double SAMPLE_SUBTOTAL = 42.5;
    private static final String SAMPLE_TEXT = "Bridge Course";
    private static final String VOWELS = "aeiou";

    /**
	 * @brief Clamp a score into the allowed range
	 *
	 * @param score Score to clamp
	 *
	 * @return Score limited to the minimum and maximum bounds
	 */
    static int clampScore(int score) {
        // Return the lower bound when the score is too small
        if (score < MINIMUM_SCORE) {
            return MINIMUM_SCORE;
        }

        // Return the upper bound when the score is too large
        if (score > MAXIMUM_SCORE) {
            return MAXIMUM_SCORE;
        }

        return score;
    }

    /**
	 * @brief Calculate total price after an optional member discount
	 *
	 * @param subtotal Price before discounts
	 *
	 * @param member True when the customer receives the member rate
	 *
	 * @return Final total price
	 */
    static double totalPrice(double subtotal, boolean member) {
        return member ? subtotal * MEMBER_DISCOUNT_RATE : subtotal;
    }

    /**
	 * @brief Count vowels in a string
	 *
	 * @param text Text to scan
	 *
	 * @return Number of vowels found
	 */
    static int countVowels(String text) {
        int count = 0;

        // Normalize each character before checking for a vowel
        for (char letter : text.toCharArray()) {
            char lower = Character.toLowerCase(letter);

            // Count the character when it appears in the vowel list
            if (VOWELS.indexOf(lower) >= 0) {
                count++;
            }
        }

        return count;
    }

    /**
	 * @brief Print function-port examples
	 *
	 * @param args Command-line arguments
	 */
    public static void main(String[] args) {
        System.out.println(clampScore(SAMPLE_SCORE));
        System.out.println(totalPrice(SAMPLE_SUBTOTAL, true));
        System.out.println(countVowels(SAMPLE_TEXT));
    }
}
