"""Supplied Python behavior to port; target-language answers live separately."""

import sys


class TaskTracker:
    def __init__(self):
        self._tasks = []
        self._next_id = 1

    @staticmethod
    def normalize_title(title):
        if not isinstance(title, str):
            raise ValueError("title")
        title = title.strip(" ")
        if not 1 <= len(title) <= 60 or any(not 32 <= ord(c) <= 126 for c in title):
            raise ValueError("title")
        return title

    def add_task(self, title):
        title = self.normalize_title(title)
        if self._next_id > 100:
            raise ValueError("limit")
        task_id = self._next_id
        self._tasks.append([task_id, title, False])
        self._next_id += 1
        return task_id

    def complete_task(self, task_id):
        if type(task_id) is not int or not 1 <= task_id <= 2147483647:
            raise ValueError("id")
        for task in self._tasks:
            if task[0] == task_id and not task[2]:
                task[2] = True
                return True
        return False

    def remove_task(self, task_id):
        if type(task_id) is not int or not 1 <= task_id <= 2147483647:
            raise ValueError("id")
        for index, task in enumerate(self._tasks):
            if task[0] == task_id:
                del self._tasks[index]
                return True
        return False

    def list_tasks(self, open_only):
        if type(open_only) is not bool:
            raise ValueError("filter")
        return [f"{i} | {'DONE' if done else 'OPEN'} | {title}"
                for i, title, done in self._tasks if not open_only or not done]

    def summary(self):
        done = sum(task[2] for task in self._tasks)
        total = len(self._tasks)
        return f"Total: {total} | Open: {total - done} | Done: {done}"


def parse_id(raw):
    if not raw or raw[0] == "0" or any(c not in "0123456789" for c in raw):
        raise ValueError("id")
    if len(raw) > 10 or int(raw) > 2147483647:
        raise ValueError("id")
    return int(raw)


def main():
    tracker = TaskTracker()
    print("Task tracker. Commands: ADD <title>, DONE <id>, REMOVE <id>, LIST, OPEN, SUMMARY, QUIT.")
    for line in sys.stdin:
        line = line.removesuffix("\n").removesuffix("\r")
        try:
            if line == "QUIT":
                break
            if line.startswith("ADD "):
                print(f"ADDED {tracker.add_task(line[4:])}")
            elif line.startswith("DONE "):
                print("CHANGED" if tracker.complete_task(parse_id(line[5:])) else "UNCHANGED")
            elif line.startswith("REMOVE "):
                print("CHANGED" if tracker.remove_task(parse_id(line[7:])) else "UNCHANGED")
            elif line in ("LIST", "OPEN"):
                rows = tracker.list_tasks(line == "OPEN")
                print("\n".join(rows) if rows else "(empty)")
            elif line == "SUMMARY":
                print(tracker.summary())
            else:
                raise ValueError("command")
        except ValueError as error:
            print(f"ERROR: {error}")


if __name__ == "__main__":
    main()
