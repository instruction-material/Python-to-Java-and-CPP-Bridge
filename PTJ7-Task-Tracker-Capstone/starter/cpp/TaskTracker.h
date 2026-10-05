#pragma once
#include <string>
#include <vector>

class TaskTracker {
public:
    static std::string normalizeTitle(const std::string& title);
    int addTask(const std::string& title);
    bool completeTask(int id);
    bool removeTask(int id);
    std::vector<std::string> listTasks(bool openOnly) const;
    std::string summary() const;
private:
    struct Task { int id; std::string title; bool done; };
    std::vector<Task> tasks;
    int nextId = 1;
};
