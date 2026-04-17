#include <iostream>
#include <string>
#include <vector>

int scoreRound(const std::string& guess, const std::vector<std::string>& secretWords) {
	// TODO: return 1 when guess is in secretWords, otherwise 0
	return 0;
}

int main() {
	std::vector<std::string> secretWords = {"vector", "compile", "header"};
	std::string guess;
	int score = 0;

	for (int round = 0; round < 3; ++round) {
		std::cout << "Guess a bridge word: ";
		std::cin >> guess;
		score += scoreRound(guess, secretWords);
	}

	std::cout << "Score: " << score << "\n";
}
