import java.util.ArrayList;

public class TaskTracker {
    private static class Task {
        final int id;
        final String title;
        boolean done;
        Task(int id, String title) { this.id = id; this.title = title; }
    }
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public static String normalizeTitle(String title) {
        if (title == null) throw new IllegalArgumentException("title");
        int start = 0, end = title.length();
        while (start < end && title.charAt(start) == ' ') start++;
        while (end > start && title.charAt(end - 1) == ' ') end--;
        String result = title.substring(start, end);
        if (result.isEmpty() || result.length() > 60)
            throw new IllegalArgumentException("title");
        for (int i = 0; i < result.length(); i++)
            if (result.charAt(i) < 32 || result.charAt(i) > 126)
                throw new IllegalArgumentException("title");
        return result;
    }

    public int addTask(String title) {
        String normalized = normalizeTitle(title);
        if (nextId > 100) throw new IllegalArgumentException("limit");
        tasks.add(new Task(nextId, normalized));
        return nextId++;
    }

    public boolean completeTask(int id) {
        if (id <= 0) throw new IllegalArgumentException("id");
        for (Task task : tasks) {
            if (task.id == id && !task.done) { task.done = true; return true; }
        }
        return false;
    }

    public boolean removeTask(int id) {
        if (id <= 0) throw new IllegalArgumentException("id");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).id == id) { tasks.remove(i); return true; }
        }
        return false;
    }

    public ArrayList<String> listTasks(boolean openOnly) {
        ArrayList<String> rows = new ArrayList<>();
        for (Task task : tasks) {
            if (!openOnly || !task.done)
                rows.add(task.id + " | " + (task.done ? "DONE" : "OPEN") + " | " + task.title);
        }
        return rows;
    }

    public String summary() {
        int done = 0;
        for (Task task : tasks) if (task.done) done++;
        return "Total: " + tasks.size() + " | Open: " + (tasks.size() - done) + " | Done: " + done;
    }
}
