import product.Product;

import java.util.*;

public class QuantumBookStore {
    private Map<String, Product> inventory = new HashMap<>();
    public void addProduct(Product product){
        inventory.put(product.getISBN(),product);
    }
    public List<Product>removeOutdatedProducts(int numOfYears){
        List<Product>removedProducts=new ArrayList<>();
        Iterator<Product>iterator=inventory.values().iterator();
        while(iterator.hasNext()){
            Product product=iterator.next();
            if(product.isOutdated(numOfYears)){
                removedProducts.add(product);
                iterator.remove();
            }
        }
        return removedProducts;
    }
    public double buy(String isbn, int quantity, String email, String address) throws Exception{
        if (!inventory.containsKey(isbn)) {
            throw new Exception(String.format("Quantum book store - Book with ISBN %s not found", isbn));
        }
        Product product = inventory.get(isbn);
        double totalAmount=product.buy( quantity, email,address);
        return totalAmount;
    }
    public void displayInventory(){
        for(Product product:inventory.values()){
            System.out.println(product);
        }
    }
}
