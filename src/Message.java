import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class Message {

    // These variables store the information for each message.
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // The constructor runs when a new message is created.
    // It receives the message number, recipient and message text.
    public Message(int messageNumber, String recipient,
                   String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        // Generate an ID and hash for the new message.
        this.messageID = generateMessageID();
        this.messageHash = generateMessageHash();
    }

    // This method generates a 10-digit message ID.
    private String generateMessageID() {

        long randomNumber =
                (long) (Math.random() * 10000000000L);

        // Format the number so that it always has 10 digits.
        return String.format("%010d", randomNumber);
    }

    // This method checks whether the message ID
    // contains no more than 10 characters.
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // This method checks whether the recipient number
    // contains digits and an optional leading plus sign.
    public boolean checkRecipientCell() {

        return recipient != null
                && recipient.matches("\\+?[0-9]{1,15}");
    }

    // This method checks that the message is not
    // longer than 250 characters.
    public boolean checkMessageLength() {
        return messageText != null
                && messageText.length() <= 250;
    }

    // This method creates a hash using the message ID,
    // message number and the first and last words.
    private String generateMessageHash() {

        try {

            // Get the first two characters of the message ID.
            String firstTwoID =
                    messageID.substring(0, 2);

            // Separate the message into individual words.
            String[] words = messageText.trim().split("\\s+");

            // Get the first and last words of the message.
            String firstWord = words[0];
            String lastWord = words[words.length - 1];

            // Combine the information needed for the hash.
            String hashInput = firstTwoID
                    + ":" + messageNumber
                    + ":" + firstWord
                    + lastWord;

            // Use MD5 to generate the message hash.
            MessageDigest digest =
                    MessageDigest.getInstance("MD5");

            byte[] hashBytes = digest.digest(
                    hashInput.toUpperCase()
                            .getBytes(StandardCharsets.UTF_8));

            // Convert the hash bytes into a readable string.
            StringBuilder result = new StringBuilder();

            for (byte b : hashBytes) {
                result.append(String.format("%02X", b));
            }

            return result.toString();

        } catch (Exception e) {

            // Return an empty string if hash generation fails.
            return "";
        }
    }

    // This method displays all the details of a message.
    public String displayMessage() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nMessage Number: " + messageNumber
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    // These getter methods allow other classes
    // to access the private message information.

    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }
}