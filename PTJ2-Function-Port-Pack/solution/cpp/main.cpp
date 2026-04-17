#include <cctype>
#include <iostream>
#include <string>

int clampScore(int score) {
	if (score < 0)
		return 0;
	if (score > 100)
		return 100;
	return score;
}

double totalPrice(double subtotal, bool member) {
	return member ? subtotal * 0.9 : subtotal;
}

int countVowels(const std::string& text) {
	int count = 0;
	for (unsigned char letter : text) {
		char lower = static_cast<char>(std::tolower(letter));
		if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u')
			count++;
	}
	return count;
}

int main() {
	std::cout << clampScore(140) << "\n";
	std::cout << totalPrice(42.5, true) << "\n";
	std::cout << countVowels("Bridge Course") << "\n";
}
