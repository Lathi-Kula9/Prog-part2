import java.util.ArrayList;
import java.util.Scanner;

public class MessageApp {

    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {

        int choice;

        System.out.println("Welcome to QuickChat!");

        do {
            System.out.println("\n1. Send Messages");
            System.out.println("2. Display Messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
                choice = 0;
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                sendMessages();
            } else if (choice == 2) {
                displayMessages();
            } else if (choice == 3) {
                System.out.println("Thank you for using QuickChat!");
            } else {
                System.out.println("Invalid option. Please choose 1, 2, 3.");
            }

        } while (choice != 4);
    }

    // Send messages
    public static void sendMessages() {

        System.out.print("How many messages would you like to send? ");

        if (!input.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            input.nextLine();
            return;
        }

        int numberOfMessages = input.nextInt();
        input.nextLine();

        if (numberOfMessages <= 0) {
            System.out.println("Please enter a number greater than zero.");
            return;
        }

        for (int i = 0; i < numberOfMessages; i++) {

            System.out.println("\nEntering message " + (i + 1));

            // Enter recipient number
            System.out.print("Enter recipient cellphone number: ");
            String recipient = input.nextLine().trim();

            if (recipient.matches("\\+27[0-9]{9}")) {
                System.out.println("Valid cellphone number.");
            } else {
                System.out.println("Invalid cellphone number.");
                System.out.println("Use +27 followed by 9 digits.");
                return;
            }

            // Enter message
            System.out.print("Enter your message: ");
            String messageText = input.nextLine().trim();

            if (messageText.isEmpty()) {
                System.out.println("Message cannot be empty.");
                return;
            }

            if (messageText.length() > 250) {
                System.out.println("Message cannot exceed 250 characters.");
                return;
            }

            // Create message
            Message message = new Message(
                    messages.size() + 1,
                    recipient,
                    messageText
            );

            // Store message in the list
            messages.add(message);

            System.out.println("Message successfully sent.");
            System.out.println("Message successfully stored.");
            System.out.println(message.displayMessage());
        }
    }

    // Display messages
    public static void displayMessages() {

        if (messages.isEmpty()) {
            System.out.println("No messages to display yet.");
        } else {
            System.out.println("\nSaved Messages:");

            for (Message message : messages) {
                System.out.println(message.displayMessage());
                System.out.println();
            }
        }
    }

}