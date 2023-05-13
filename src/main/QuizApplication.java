import java.util.Scanner;

public class QuizApplication {
    public static void main(String[] args) {
        QuizQuestion[] questions = createQuestions();

        int score = 0;
        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : questions) {
            System.out.println(question.getQuestion());
            System.out.println("Options:");
            for (int i = 0; i < question.getOptions().length; i++) {
                System.out.println((i + 1) + ". " + question.getOptions()[i]);
            }

            System.out.print("Enter your answer (1-" + question.getOptions().length + "): ");
            int answer = scanner.nextInt();

            if (answer == question.getCorrectAnswer()) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Incorrect!\n");
            }
        }

        System.out.println("Quiz finished!");
        System.out.println("Your score: " + score + "/" + questions.length);
    }

    private static QuizQuestion[] createQuestions() {
        QuizQuestion[] questions = new QuizQuestion[3];

        QuizQuestion question1 = new QuizQuestion();
        question1.setQuestion("What is the capital of France?");
        question1.setOptions(new String[]{"London", "Paris", "Berlin", "Rome"});
        question1.setCorrectAnswer(2);
        questions[0] = question1;

        QuizQuestion question2 = new QuizQuestion();
        question2.setQuestion("Which planet is known as the red planet?");
        question2.setOptions(new String[]{"Mars", "Jupiter", "Venus", "Saturn"});
        question2.setCorrectAnswer(1);
        questions[1] = question2;

        QuizQuestion question3 = new QuizQuestion();
        question3.setQuestion("What is the largest ocean in the world?");
        question3.setOptions(new String[]{"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"});
        question3.setCorrectAnswer(1);
        questions[2] = question3;

        return questions;
    }
}

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
