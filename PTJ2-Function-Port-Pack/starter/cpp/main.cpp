#include <stdexcept>
#include <iostream>
#include <string>

int clampScore(int score) {
    // TODO: keep score in the range 0..100
    throw std::logic_error("Implement the documented contract");
}

double totalPrice(double subtotal, bool member) {
    // TODO: apply 10% member discount when member is true
    throw std::logic_error("Implement the documented contract");
}

int countVowels(const std::string& text) {
    // TODO: count lowercase and uppercase vowels
    throw std::logic_error("Implement the documented contract");
}

int main() {
    std::cout << clampScore(140) << "\n";
    std::cout << totalPrice(42.5, true) << "\n";
    std::cout << countVowels("Bridge Course") << "\n";
}
