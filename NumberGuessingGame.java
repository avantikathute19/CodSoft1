import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;

        while (playAgain) {
            int randomNumber = random.nextInt(100) + 1; 
            int attemptsLeft = 5; 
            int userScore = 0;
            boolean numberGuessed = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have chosen a number between 1 and 100. You have " + attemptsLeft + " attempts to guess it.");

            while (attemptsLeft > 0 && !numberGuessed) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == randomNumber) {
                    System.out.println("Woww Congratulations! You have guessed the number correctly.");
                    numberGuessed = true;
                    userScore++;
                } else if (userGuess < randomNumber) {
                    System.out.println("Number is too low! Try again.");
                } else {
                    System.out.println(" Number is too high! Try again.");
                }

                if (!numberGuessed && attemptsLeft > 0) {
                    System.out.println("Attempts left: " + attemptsLeft);
                }
            }

            if (!numberGuessed) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            }

            System.out.println("Your current score: " + userScore);
            System.out.print("Would you like to play another round? (yes/no): ");
            scanner.nextLine(); 
            String response = scanner.nextLine();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Goodbye!");
        scanner.close();
    }
}