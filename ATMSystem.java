import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: Rs" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: Rs" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance for this withdrawal.");
            return false;
        } else {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
    }

    
    public double checkBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }


    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to the ATM!");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    
    private void checkBalance() {
        System.out.println("Your current balance is: Rs" + account.checkBalance());
    }

    
    private void depositMoney() {
        System.out.print("Enter the amount to deposit: Rs");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

   
    private void withdrawMoney() {
        System.out.print("Enter the amount to withdraw: Rs");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}

public class ATMSystem {
    public static void main(String[] args) {
       
        BankAccount userAccount = new BankAccount(1000.00); 

        
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}