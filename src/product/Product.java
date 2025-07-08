package product;

import services.MailService;
import services.ShippingService;

public class Product {
    private String ISBN;
    private String title;
    private int publishYear;
    private double price;
    private int numberInStock;
    private ProductType type;
    public Product(String ISBN, String title, int publishYear, double price,int numberInStock,ProductType type) {
        this.ISBN = ISBN;
        this.title = title;
        this.publishYear = publishYear;
        this.price = price;
        this.numberInStock=numberInStock;
        this.type=type;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
    public boolean isOutdated(int numOfAllowedYears) {
        int currentYear = java.time.Year.now().getValue();
        return (currentYear - this.publishYear) > numOfAllowedYears;
    }

    private boolean isAvailable(int quantity){
        return numberInStock>=quantity;
    }
    private void decreaseStock(int quantity) {
        if (numberInStock >= quantity) {
            numberInStock -= quantity;
        }
    }
    public double buy(int quantity, String email, String address)throws Exception{
        if (!isAvailable(quantity)) {
            throw new Exception(String.format(
                    "Quantum book store - Not enough stock for %s. Available: %d, Requested: %d",
                    title, numberInStock, quantity));
        }
        if(!this.type.isSalable()){
            throw new Exception(String.format(
                    "Quantum book store - %s is a showcase book and not available for purchase", title));
        }
        decreaseStock(quantity);
        double totalAmount=quantity*this.price;
        if(this.type.isRequiredShipping()){
            ShippingService.ship(title, quantity, address);
        }
        else {
            MailService.sendEmail(title, quantity, email);
        }
        System.out.printf("Quantum book store - Purchased %d copy(ies) of '%s' for $%.2f%n",
                quantity, title, totalAmount);
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", publishYear=" + publishYear +
                ", price=" + price +
                ", numberInStock=" + numberInStock +
                ", type=" + type.toString() +
                '}';
    }
}
