#include <cctype>
#include <iostream>
#include <string>

/*****************
*   CONSTANTS   *
*****************/

constexpr int MINIMUM_SCORE = 0;
constexpr int MAXIMUM_SCORE = 100;
constexpr double MEMBER_DISCOUNT_RATE = 0.9;
constexpr int SAMPLE_SCORE = 140;
constexpr double SAMPLE_SUBTOTAL = 42.5;
const std::string SAMPLE_TEXT = "Bridge Course";
const std::string VOWELS = "aeiou";

/*****************
*   FUNCTIONS   *
*****************/

/**
 * @brief Clamp a score into the allowed range
 *
 * @param score Score to clamp
 *
 * @return Score limited to the minimum and maximum bounds
 */
int clamp_score(int score) {
    // Return the lower bound when the score is too small
    if (score < MINIMUM_SCORE) {
        return MINIMUM_SCORE;
    }

    // Return the upper bound when the score is too large
    if (score > MAXIMUM_SCORE) {
        return MAXIMUM_SCORE;
    }

    return score;
}

/**
 * @brief Calculate total price after an optional member discount
 *
 * @param subtotal Price before discounts
 *
 * @param member True when the customer receives the member rate
 *
 * @return Final total price
 */
double total_price(double subtotal, bool member) {
    return member ? subtotal * MEMBER_DISCOUNT_RATE : subtotal;
}

/**
 * @brief Count vowels in a string
 *
 * @param text Text to scan
 *
 * @return Number of vowels found
 */
int count_vowels(const std::string& text) {
    int count = 0;

    // Normalize each character before checking for a vowel
    for (unsigned char letter : text) {
        const char lower = static_cast<char>(std::tolower(letter));

        // Count the character when it appears in the vowel list
        if (VOWELS.find(lower) != std::string::npos) {
            count++;
        }
    }

    return count;
}

/**
 * @brief Print function-port examples
 *
 * @return Process exit code
 */
int main() {
    std::cout << clamp_score(SAMPLE_SCORE) << "\n";
    std::cout << total_price(SAMPLE_SUBTOTAL, true) << "\n";
    std::cout << count_vowels(SAMPLE_TEXT) << "\n";
}
