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
        // TODO: validate and normalize the title using the README contract.
        throw new UnsupportedOperationException("Implement normalizeTitle");
    }
    public int addTask(String title) {
        // TODO: validate before allocating an ID or changing the collection.
        throw new UnsupportedOperationException("Implement addTask");
    }
    public boolean completeTask(int id) {
        // TODO: change only an existing open task; report whether it changed.
        throw new UnsupportedOperationException("Implement completeTask");
    }
    public boolean removeTask(int id) {
        // TODO: remove one matching task while preserving the remaining order.
        throw new UnsupportedOperationException("Implement removeTask");
    }
    public ArrayList<String> listTasks(boolean openOnly) {
        // TODO: produce fresh formatted rows with the required filter and order.
        throw new UnsupportedOperationException("Implement listTasks");
    }
    public String summary() {
        // TODO: compute the exact summary from the current collection.
        throw new UnsupportedOperationException("Implement summary");
    }
}
