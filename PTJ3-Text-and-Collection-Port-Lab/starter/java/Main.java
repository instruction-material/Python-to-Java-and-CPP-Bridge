import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<String> longWords(List<String> words) {
		// TODO: return a list containing only words with length >= 5
		return new ArrayList<>();
	}

	static String longestWord(List<String> words) {
		// TODO: return the longest word in the list
		return "";
	}

	public static void main(String[] args) {
		List<String> words = List.of("bridge", "code", "typed", "syntax", "port");
		System.out.println(longWords(words));
		System.out.println(longestWord(words));
	}
}
