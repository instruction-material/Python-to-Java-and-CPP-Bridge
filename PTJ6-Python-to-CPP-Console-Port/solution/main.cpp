#include <iostream>
#include <string>
#include <vector>


/*****************
*   CONSTANTS   *
*****************/

constexpr int ROUND_COUNT = 3;
constexpr int POINTS_PER_MATCH = 1;
constexpr int NO_MATCH_POINTS = 0;
const std::vector<std::string> SECRET_WORDS = {"vector", "compile", "header"};


/*****************
*   FUNCTIONS   *
*****************/

/**
 * @brief Score one guessed word
 *
 * @param guess Word guessed by the player
 *
 * @param secret_words Accepted secret words
 *
 * @return Points earned for the guess
 */
int score_round(const std::string& guess, const std::vector<std::string>& secret_words) {
	// Search for the guess in the accepted word list
	for (const std::string& word : secret_words) {
		// Award a point when the guess matches a secret word
		if (word == guess) {
			return POINTS_PER_MATCH;
		}
	}

	return NO_MATCH_POINTS;
}

/**
 * @brief Run the console guessing game
 *
 * @return Process exit code
 */
int main() {
	std::string guess;
	int score = NO_MATCH_POINTS;

	// Ask for one guess per round
	for (int round = 0; round < ROUND_COUNT; ++round) {
		std::cout << "Guess a bridge word: ";
		std::cin >> guess;
		score += score_round(guess, SECRET_WORDS);
	}

	std::cout << "Score: " << score << "\n";
}
