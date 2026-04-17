#include <iostream>
#include <string>
#include <vector>

std::vector<std::string> longWords(const std::vector<std::string>& words) {
	std::vector<std::string> result;
	for (const std::string& word : words) {
		if (word.size() >= 5)
			result.push_back(word);
	}
	return result;
}

std::string longestWord(const std::vector<std::string>& words) {
	std::string best;
	for (const std::string& word : words) {
		if (word.size() > best.size())
			best = word;
	}
	return best;
}

int main() {
	std::vector<std::string> words = {"bridge", "code", "typed", "syntax", "port"};
	auto result = longWords(words);
	for (const auto& word : result)
		std::cout << word << ' ';
	std::cout << "\n" << longestWord(words) << "\n";
}
