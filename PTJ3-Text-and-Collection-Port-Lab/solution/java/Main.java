import java.util.ArrayList;
import java.util.List;

// Demonstrate text and collection ports from Python into Java
public class Main {
	private static final int MINIMUM_LONG_WORD_LENGTH = 5;
	private static final List<String> SAMPLE_WORDS = List.of("bridge", "code", "typed", "syntax", "port");

	/**
	 * @brief Filter words that meet the long-word length threshold
	 *
	 * @param words Words to filter
	 *
	 * @return Words that meet the threshold
	 */
	static List<String> longWords(List<String> words) {
		List<String> result = new ArrayList<>();

		// Keep only words that meet the long-word threshold
		for (String word : words) {
			if (word.length() >= MINIMUM_LONG_WORD_LENGTH) {
				result.add(word);
			}
		}

		return result;
	}

	/**
	 * @brief Find the longest word in a list
	 *
	 * @param words Words to search
	 *
	 * @return Longest word found or an empty string
	 */
	static String longestWord(List<String> words) {
		String best = "";

		// Track the longest word seen so far
		for (String word : words) {
			if (word.length() > best.length()) {
				best = word;
			}
		}

		return best;
	}

	/**
	 * @brief Print collection-port examples
	 *
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		System.out.println(longWords(SAMPLE_WORDS));
		System.out.println(longestWord(SAMPLE_WORDS));
	}
}
