#include "TaskTracker.h"
#include <stdexcept>

std::string TaskTracker::normalizeTitle(const std::string& title) {
    auto start = title.find_first_not_of(' ');
    if (start == std::string::npos) throw std::invalid_argument("title");
    auto end = title.find_last_not_of(' ');
    auto result = title.substr(start, end - start + 1);
    if (result.size() > 60) throw std::invalid_argument("title");
    for (unsigned char c : result)
        if (c < 32 || c > 126) throw std::invalid_argument("title");
    return result;
}
int TaskTracker::addTask(const std::string& title) {
    auto normalized = normalizeTitle(title);
    if (nextId > 100) throw std::invalid_argument("limit");
    tasks.push_back({nextId, normalized, false});
    return nextId++;
}
bool TaskTracker::completeTask(int id) {
    if (id <= 0) throw std::invalid_argument("id");
    for (auto& task : tasks) {
        if (task.id == id && !task.done) { task.done = true; return true; }
    }
    return false;
}
bool TaskTracker::removeTask(int id) {
    if (id <= 0) throw std::invalid_argument("id");
    for (auto entry = tasks.begin(); entry != tasks.end(); ++entry) {
        if (entry->id == id) { tasks.erase(entry); return true; }
    }
    return false;
}
std::vector<std::string> TaskTracker::listTasks(bool openOnly) const {
    std::vector<std::string> rows;
    for (const auto& task : tasks) {
        if (!openOnly || !task.done)
            rows.push_back(std::to_string(task.id) + " | " + (task.done ? "DONE" : "OPEN") + " | " + task.title);
    }
    return rows;
}
std::string TaskTracker::summary() const {
    int done = 0;
    for (const auto& task : tasks) if (task.done) ++done;
    return "Total: " + std::to_string(tasks.size()) + " | Open: " + std::to_string(tasks.size() - done) + " | Done: " + std::to_string(done);
}
