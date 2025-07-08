package services;

public class ShippingService {
    public static void ship(String title, int quantity, String address) {
        System.out.printf("Quantum book store - Shipping %d copy(ies) of '%s' to %s%n",
                quantity, title, address);
    }
}
