#include <iostream>
#include <string>

int clampScore(int score) {
	// TODO: keep score in the range 0..100
	return 0;
}

double totalPrice(double subtotal, bool member) {
	// TODO: apply 10% member discount when member is true
	return 0.0;
}

int countVowels(const std::string& text) {
	// TODO: count lowercase and uppercase vowels
	return 0;
}

int main() {
	std::cout << clampScore(140) << "\n";
	std::cout << totalPrice(42.5, true) << "\n";
	std::cout << countVowels("Bridge Course") << "\n";
}
