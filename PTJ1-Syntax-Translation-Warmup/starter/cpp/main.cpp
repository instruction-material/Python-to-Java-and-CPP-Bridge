#include <iostream>
#include <string>
#include <vector>

std::string greeting(const std::string& name) {
	// TODO: return "Hello, <name>!"
	return "";
}

int absoluteValue(int value) {
	// TODO: return the non-negative version of value
	return 0;
}

bool isEven(int value) {
	// TODO: return true when value is even
	return false;
}

std::string fizzBuzzLabel(int value) {
	// TODO: return Fizz, Buzz, FizzBuzz, or the number as text
	return "";
}

int main() {
	std::vector<int> checks = {-7, -2, 0, 3, 5, 15};
	std::cout << greeting("Avery") << "\n";
	std::cout << absoluteValue(-7) << "\n";
	std::cout << std::boolalpha << isEven(12) << "\n";
	for (const int value : checks)
		std::cout << fizzBuzzLabel(value) << " ";
	std::cout << "\n";
}
