#include <iostream>
#include <limits>
#include <stdexcept>
#include <string>
#include <vector>

/*****************
*   CONSTANTS   *
*****************/

const std::string SAMPLE_NAME = "Avery";
constexpr int NEGATIVE_SAMPLE_VALUE = -7;
constexpr int EVEN_SAMPLE_VALUE = 12;
constexpr int EVEN_DIVISOR = 2;
constexpr int FIZZ_DIVISOR = 3;
constexpr int BUZZ_DIVISOR = 5;
constexpr int FIZZ_BUZZ_DIVISOR = FIZZ_DIVISOR * BUZZ_DIVISOR;
const std::vector<int> CHECK_VALUES = {-7, -2, 0, 3, 5, 15};

/*****************
*   FUNCTIONS   *
*****************/

/**
 * @brief Build a greeting for one name
 *
 * @param name Name to greet
 *
 * @return Greeting text
 */
std::string greeting(const std::string& name) {
    return "Hello, " + name + "!";
}

/**
 * @brief Return the absolute value of an integer
 *
 * @param value Integer to convert
 *
 * @return Non-negative absolute value
 */
int absoluteValue(int value) {
    if (value == std::numeric_limits<int>::min()) {
        throw std::overflow_error("Absolute value does not fit in int");
    }
    return value < 0 ? -value : value;
}

/**
 * @brief Check whether an integer is even
 *
 * @param value Integer to check
 *
 * @return True when the value is even
 */
bool isEven(int value) {
    return value % EVEN_DIVISOR == 0;
}

/**
 * @brief Return the FizzBuzz label for one integer
 *
 * @param value Integer to label
 *
 * @return FizzBuzz label or the original value as text
 */
std::string fizzBuzzLabel(int value) {
    // Prefer the combined label when both divisibility rules match
    if (value % FIZZ_BUZZ_DIVISOR == 0) {
        return "FizzBuzz";
    }

    // Apply the fizz label for multiples of three
    if (value % FIZZ_DIVISOR == 0) {
        return "Fizz";
    }

    // Apply the buzz label for multiples of five
    if (value % BUZZ_DIVISOR == 0) {
        return "Buzz";
    }

    return std::to_string(value);
}

/**
 * @brief Print the warmup examples
 *
 * @return Process exit code
 */
int main() {
    std::cout << greeting(SAMPLE_NAME) << "\n";
    std::cout << absoluteValue(NEGATIVE_SAMPLE_VALUE) << "\n";
    std::cout << std::boolalpha << isEven(EVEN_SAMPLE_VALUE) << "\n";

    // Print the FizzBuzz label for each sample value
    for (const int value : CHECK_VALUES) {
        std::cout << fizzBuzzLabel(value) << " ";
    }

    std::cout << "\n";
}
