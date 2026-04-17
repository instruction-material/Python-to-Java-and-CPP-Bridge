import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<String> longWords(List<String> words) {
		List<String> result = new ArrayList<>();
		for (String word : words) {
			if (word.length() >= 5)
				result.add(word);
		}
		return result;
	}

	static String longestWord(List<String> words) {
		String best = "";
		for (String word : words) {
			if (word.length() > best.length())
				best = word;
		}
		return best;
	}

	public static void main(String[] args) {
		List<String> words = List.of("bridge", "code", "typed", "syntax", "port");
		System.out.println(longWords(words));
		System.out.println(longestWord(words));
	}
}
