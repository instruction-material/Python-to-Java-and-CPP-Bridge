import java.util.Scanner;

public class Main {
    private static int parseId(String raw) {
        if (!raw.matches("[1-9][0-9]*") || raw.length() > 10)
            throw new IllegalArgumentException("id");
        try { return Integer.parseInt(raw); }
        catch (NumberFormatException error) { throw new IllegalArgumentException("id"); }
    }
    public static void main(String[] args) {
        TaskTracker tracker = new TaskTracker();
        Scanner input = new Scanner(System.in);
        System.out.println("Task tracker. Commands: ADD <title>, DONE <id>, REMOVE <id>, LIST, OPEN, SUMMARY, QUIT.");
        while (input.hasNextLine()) {
            String line = input.nextLine();
            try {
                if (line.equals("QUIT")) break;
                if (line.startsWith("ADD "))
                    System.out.println("ADDED " + tracker.addTask(line.substring(4)));
                else if (line.startsWith("DONE "))
                    System.out.println(tracker.completeTask(parseId(line.substring(5))) ? "CHANGED" : "UNCHANGED");
                else if (line.startsWith("REMOVE "))
                    System.out.println(tracker.removeTask(parseId(line.substring(7))) ? "CHANGED" : "UNCHANGED");
                else if (line.equals("LIST") || line.equals("OPEN")) {
                    var rows = tracker.listTasks(line.equals("OPEN"));
                    if (rows.isEmpty()) System.out.println("(empty)");
                    else for (String row : rows) System.out.println(row);
                }
                else if (line.equals("SUMMARY")) System.out.println(tracker.summary());
                else throw new IllegalArgumentException("command");
            }
            catch (IllegalArgumentException error) { System.out.println("ERROR: " + error.getMessage()); }
        }
        input.close();
    }
}
