#include <iostream>
#include <string>
#include <vector>

/*****************
*   CONSTANTS   *
*****************/

constexpr std::size_t MINIMUM_LONG_WORD_LENGTH = 5;
const std::vector<std::string> SAMPLE_WORDS = {"bridge", "code", "typed",
                                               "syntax", "port"};

/*****************
*   FUNCTIONS   *
*****************/

/**
 * @brief Filter words that meet the long-word length threshold
 *
 * @param words Words to filter
 *
 * @return Words that meet the threshold
 */
std::vector<std::string> long_words(const std::vector<std::string>& words) {
    std::vector<std::string> result;

    // Keep only words that meet the long-word threshold
    for (const std::string& word : words) {
        if (word.size() >= MINIMUM_LONG_WORD_LENGTH) {
            result.push_back(word);
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
std::string longest_word(const std::vector<std::string>& words) {
    std::string best;

    // Track the longest word seen so far
    for (const std::string& word : words) {
        if (word.size() > best.size()) {
            best = word;
        }
    }

    return best;
}

/**
 * @brief Print collection-port examples
 *
 * @return Process exit code
 */
int main() {
    const auto result = long_words(SAMPLE_WORDS);

    // Print each filtered word on one line
    for (const auto& word : result) {
        std::cout << word << ' ';
    }

    std::cout << "\n" << longest_word(SAMPLE_WORDS) << "\n";
}
