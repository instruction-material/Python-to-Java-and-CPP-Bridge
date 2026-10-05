import java.util.Scanner;

public class Main {
    static int scoreAnswer(String answer, String expectedAnswer) {
        return answer.trim().equalsIgnoreCase(expectedAnswer) ? 1 : 0;
    }

    public static void main(String[] args) {
        String[] prompts = {"What keyword defines a class in Java? ",
                            "What method compares string contents in Java? "};
        String[] answers = {"class", "equals"};
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        for (int question = 0; question < prompts.length; question++) {
            System.out.print(prompts[question]);
            if (!scanner.hasNextLine()) {
                System.out.println("Input ended.");
                break;
            }
            score += scoreAnswer(scanner.nextLine(), answers[question]);
        }
        System.out.println("Final score: " + score + "/2");
    }
}
