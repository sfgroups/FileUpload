import java.util.*;

public class MultipleChoiceQuiz {

    public static void main(String[] args) {

        // Create a list of questions
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("What is the capital of France?", Arrays.asList("Paris", "London", "Berlin", "Rome"), "Paris"));
        questions.add(new Question("What is the name of the current US president?", Arrays.asList("Joe Biden", "Donald Trump", "Barack Obama", "Bill Clinton"), "Joe Biden"));
        questions.add(new Question("What is the square root of 16?", Arrays.asList("4", "8", 12", 16), "4"));

        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Iterate over the questions and ask the user for their answer
        int correctAnswers = 0;
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            for (int i = 0; i < question.getAnswers().size(); i++) {
                System.out.println((i + 1) + ". " + question.getAnswers().get(i));
            }

            // Get the user's answer
            int userAnswer = scanner.nextInt();

            // Check if the user's answer is correct
            if (userAnswer == question.getCorrectAnswer()) {
                correctAnswers++;
            }
        }

        // Display the quiz results
        System.out.println("You answered " + correctAnswers + " questions correctly.");
    }
}

class Question {

    private String question;
    private List<String> answers;
    private String correctAnswer;

    public Question(String question, List<String> answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
