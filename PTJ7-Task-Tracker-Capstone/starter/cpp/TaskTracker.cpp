#include "TaskTracker.h"
#include <stdexcept>

std::string TaskTracker::normalizeTitle(const std::string& title) {
    // TODO: validate and normalize the title using the README contract.
    throw std::logic_error("Implement normalizeTitle");
}
int TaskTracker::addTask(const std::string& title) {
    // TODO: validate before allocating an ID or changing the collection.
    throw std::logic_error("Implement addTask");
}
bool TaskTracker::completeTask(int id) {
    // TODO: change only an existing open task; report whether it changed.
    throw std::logic_error("Implement completeTask");
}
bool TaskTracker::removeTask(int id) {
    // TODO: remove one matching task while preserving the remaining order.
    throw std::logic_error("Implement removeTask");
}
std::vector<std::string> TaskTracker::listTasks(bool openOnly) const {
    // TODO: produce fresh formatted rows with the required filter and order.
    throw std::logic_error("Implement listTasks");
}
std::string TaskTracker::summary() const {
    // TODO: compute the exact summary from the current collection.
    throw std::logic_error("Implement summary");
}
