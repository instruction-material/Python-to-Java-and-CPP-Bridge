#include "TaskTracker.h"
#include <algorithm>
#include <iostream>
#include <stdexcept>
#include <string>

int parseId(const std::string& raw) {
    if (raw.empty() || raw.front() == '0' || raw.size() > 10 ||
        !std::all_of(raw.begin(), raw.end(), [](char c) { return c >= '0' && c <= '9'; }))
        throw std::invalid_argument("id");
    try {
        auto value = std::stoll(raw);
        if (value > 2147483647LL) throw std::invalid_argument("id");
        return static_cast<int>(value);
    }
    catch (const std::out_of_range&) { throw std::invalid_argument("id"); }
}
int main() {
    TaskTracker tracker;
    std::cout << "Task tracker. Commands: ADD <title>, DONE <id>, REMOVE <id>, LIST, OPEN, SUMMARY, QUIT.\n";
    std::string line;
    while (std::getline(std::cin, line)) {
        if (!line.empty() && line.back() == '\r') line.pop_back();
        try {
            if (line == "QUIT") break;
            if (line.rfind("ADD ", 0) == 0) {
                int id = tracker.addTask(line.substr(4));
                std::cout << "ADDED " << id << '\n';
            }
            else if (line.rfind("DONE ", 0) == 0)
                std::cout << (tracker.completeTask(parseId(line.substr(5))) ? "CHANGED" : "UNCHANGED") << '\n';
            else if (line.rfind("REMOVE ", 0) == 0)
                std::cout << (tracker.removeTask(parseId(line.substr(7))) ? "CHANGED" : "UNCHANGED") << '\n';
            else if (line == "LIST" || line == "OPEN") {
                auto rows = tracker.listTasks(line == "OPEN");
                if (rows.empty()) std::cout << "(empty)\n";
                else for (const auto& row : rows) std::cout << row << '\n';
            }
            else if (line == "SUMMARY") std::cout << tracker.summary() << '\n';
            else throw std::invalid_argument("command");
        }
        catch (const std::exception& error) { std::cout << "ERROR: " << error.what() << '\n'; }
    }
}
