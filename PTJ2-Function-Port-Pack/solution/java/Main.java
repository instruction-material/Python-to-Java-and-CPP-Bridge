public class Main {
	static int clampScore(int score) {
		if (score < 0)
			return 0;
		if (score > 100)
			return 100;
		return score;
	}

	static double totalPrice(double subtotal, boolean member) {
		return member ? subtotal * 0.9 : subtotal;
	}

	static int countVowels(String text) {
		int count = 0;
		for (char letter : text.toCharArray()) {
			char lower = Character.toLowerCase(letter);
			if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u')
				count++;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(clampScore(140));
		System.out.println(totalPrice(42.5, true));
		System.out.println(countVowels("Bridge Course"));
	}
}
