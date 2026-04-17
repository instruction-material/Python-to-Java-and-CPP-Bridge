#include <iostream>
#include <string>
#include <vector>

std::string greeting(const std::string& name) {
	return "Hello, " + name + "!";
}

int absoluteValue(int value) {
	return value < 0 ? -value : value;
}

bool isEven(int value) {
	return value % 2 == 0;
}

std::string fizzBuzzLabel(int value) {
	if (value % 15 == 0)
		return "FizzBuzz";
	if (value % 3 == 0)
		return "Fizz";
	if (value % 5 == 0)
		return "Buzz";
	return std::to_string(value);
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
