//TASK 3::

import java.util.ArrayList;
import java.util.Scanner;

class User {
    String userId;
    String userPin;
    double balance;
    ArrayList<String> transactionHistory;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }
}

class ATM {
    private User currentUser;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the ATM Interface");
        authenticateUser();
        showMenu();
    }

    private void authenticateUser() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPin = scanner.nextLine();

        currentUser = new User("12345", "6789", 1000.0);  // Sample user

        if (userId.equals(currentUser.userId) && userPin.equals(currentUser.userPin)) {
            System.out.println("Authentication Successful!");
        } else {
            System.out.println("Invalid credentials, please try again.");
            authenticateUser();
        }
    }

    private void showMenu() {
        while (true) {
            System.out.println("\n1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showTransactionHistory();
                case 2 -> withdraw();
                case 3 -> deposit();
                case 4 -> transfer();
                case 5 -> {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (String transaction : currentUser.transactionHistory) {
            System.out.println(transaction);
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > currentUser.balance) {
            System.out.println("Insufficient balance.");
        } else {
            currentUser.balance -= amount;
            currentUser.transactionHistory.add("Withdrawn: $" + amount);
            System.out.println("$" + amount + " withdrawn successfully.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        currentUser.balance += amount;
        currentUser.transactionHistory.add("Deposited: $" + amount);
        System.out.println("$" + amount + " deposited successfully.");
    }

    private void transfer() {
        System.out.print("Enter recipient's User ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount > currentUser.balance) {
            System.out.println("Insufficient balance.");
        } else {
            currentUser.balance -= amount;
            currentUser.transactionHistory.add("Transferred $" + amount + " to User " + recipientId);
            System.out.println("$" + amount + " transferred to User " + recipientId);
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
