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
 * @param secretWords Accepted secret words
 *
 * @return Points earned for the guess
 */
int scoreRound(const std::string& guess,
                const std::vector<std::string>& secretWords) {
    // Search for the guess in the accepted word list
    for (const std::string& word : secretWords) {
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
        if (!(std::cin >> guess)) {
            std::cout << "Input ended after " << round << " of 3 rounds.\n";
            break;
        }
        score += scoreRound(guess, SECRET_WORDS);
    }

    std::cout << "Score: " << score << "\n";
}
