import java.util.*;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;

    
    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption; 
    }

  
    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;
    private List<String> results; 
    private static final int TIME_LIMIT_SECONDS = 10; 

   
    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
        this.results = new ArrayList<>();
    }

    
    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            List<String> options = question.getOptions();

            
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            System.out.println("You have " + TIME_LIMIT_SECONDS + " seconds to answer.");

            
            long startTime = System.currentTimeMillis();
            int userAnswer = -1;

            while (System.currentTimeMillis() - startTime < TIME_LIMIT_SECONDS * 1000) {
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt();
                    break;
                }
            }

            
            if (userAnswer == -1) {
                System.out.println("Time's up! No answer selected.");
                results.add("Incorrect");
            } else if (userAnswer - 1 == question.getCorrectOption()) {
                System.out.println("Correct!");
                score++;
                results.add("Correct");
            } else {
                System.out.println("Incorrect!");
                results.add("Incorrect");
            }
            System.out.println();
        }

        
        displayResults();
    }

   
    private void displayResults() {
        System.out.println("Quiz Completed!");
        System.out.println("Your final score: " + score + "/" + questions.size());
        System.out.println("Summary:");

        for (int i = 0; i < results.size(); i++) {
            System.out.println("Question " + (i + 1) + ": " + results.get(i));
        }
    }
}

public class QuizSystem {
    public static void main(String[] args) {
        
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "What is the capital of France?",
                Arrays.asList("Berlin", "Madrid", "Paris", "Rome"),
                2 
        ));
        questions.add(new Question(
                "Which programming language is known as the mother of all languages?",
                Arrays.asList("C", "Python", "Java", "Ruby"),
                0 
        ));
        questions.add(new Question(
                "Who wrote 'Romeo and Juliet'?",
                Arrays.asList("Mark Twain", "William Shakespeare", "Charles Dickens", "Jane Austen"),
                1 
        ));

       
        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}