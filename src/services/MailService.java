package services;

public class MailService {
    public static void sendEmail(String title, int quantity, String email) {
        System.out.printf("Quantum book store - Sending %d copy(ies) of '%s' to %s%n",
                quantity, title, email);
    }
}
