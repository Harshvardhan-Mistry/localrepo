import java.util.*;
import java.util.concurrent.*;

class QuizQuestion {
    private String question;
    private String[] options;
    private String correctAnswer;
    private long timeLimit;

    public QuizQuestion(String question, String[] options, String correctAnswer, long timeLimit) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.timeLimit = timeLimit;
    }

    public String getQuestions() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public long getTimeLimit() {
        return timeLimit;
    }
}

// QuizApplication Class
public class QuizApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<QuizQuestion> quizQuestions = new ArrayList<>();

        // Add quiz questions
        quizQuestions.add(new QuizQuestion("What is the capital of India?",
                new String[] { "Gujarat", "Tamilnadu", "Delhi", "Jammu Kashmir" }, "Delhi", 10));
        quizQuestions.add(new QuizQuestion("Which is the largest planet in our solar system?",
                new String[] { "Mercury", "Venus", "Earth", "Jupiter" }, "Jupiter", 10));
        quizQuestions.add(
                new QuizQuestion("Which is the smallest prime number?", new String[] { "1", "2", "3", "4" }, "2", 10));

        int score = 0;
        int totalQuestions = quizQuestions.size();

        for (QuizQuestion quizQuestion : quizQuestions) {
            System.out.println("Question:" + quizQuestion.getQuestions());
            System.out.println("Options:");
            for (int i = 0; i < quizQuestion.getOptions().length; i++) {
                System.out.println((i + 1) + "." + quizQuestion.getOptions()[i]);
            }

            System.out.print("Enter your answer (1-" + quizQuestion.getOptions().length + "): ");
            int userAnswer = sc.nextInt();
            sc.nextLine(); // Consume newline

            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;

            while (elapsedTime < quizQuestion.getTimeLimit() * 10) {
                elapsedTime = (System.currentTimeMillis() - startTime) / 10;
                System.out.print("\rTime left: " + (quizQuestion.getTimeLimit() - elapsedTime) + " seconds   ");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("\nTime's up!");

            if (quizQuestion.getOptions()[userAnswer - 1].equals(quizQuestion.getCorrectAnswer())) {
                System.out.println("Correct answer!");
                score++;
            } else {
                System.out.println("Incorrect answer. The correct answer is" + quizQuestion.getCorrectAnswer());
            }

            System.out.println("-----------------");
        }
        System.out.println("Your final scoreis" + score + "/" + totalQuestions);
        sc.close();
    }
}
