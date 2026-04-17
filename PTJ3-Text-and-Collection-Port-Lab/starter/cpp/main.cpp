#include <iostream>
#include <string>
#include <vector>

std::vector<std::string> longWords(const std::vector<std::string>& words) {
	// TODO: return only words with length >= 5
	return {};
}

std::string longestWord(const std::vector<std::string>& words) {
	// TODO: return the longest word in the list
	return "";
}

int main() {
	std::vector<std::string> words = {"bridge", "code", "typed", "syntax", "port"};
	auto result = longWords(words);
	for (const auto& word : result)
		std::cout << word << ' ';
	std::cout << "\n" << longestWord(words) << "\n";
}
