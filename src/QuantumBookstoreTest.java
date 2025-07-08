
import product.Product;
import product.ProductType;
import java.util.List;

public class QuantumBookstoreTest {
    private QuantumBookStore bookstore;


    public QuantumBookstoreTest() {
        this.bookstore = new QuantumBookStore();

    }
    public void runAllTests() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Run All Tests");
        System.out.println("=".repeat(60));

        testProductCreation();
        testInventoryManagement();
        testPurchaseScenarios();
        testOutdatedProductRemoval();
        testErrorHandling();
        testInventoryDisplay();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Product System - All tests completed successfully!");
        System.out.println("=".repeat(60));
    }
    private void testProductCreation() {
        System.out.println("\n>>> Testing Product Creation");
        System.out.println("-".repeat(50));

        // Create different product types
        ProductType physicalBook = new ProductType("Physical Book", true, true);
        ProductType ebook = new ProductType("E-Book", false, true);
        ProductType showcaseItem = new ProductType("Showcase Item", false, false);

        // Create products with realistic data
        Product novel = new Product("978-0-123456-78-9", "The Great Adventure", 2020, 15.99, 25, physicalBook);
        Product digitalGuide = new Product("978-1-111111-11-1", "Digital Photography Mastery", 2023, 29.99, 100, ebook);
        Product vintageBook = new Product("978-0-555555-55-5", "Ancient Wisdom Chronicles", 1850, 999.99, 1, showcaseItem);

        // Test product properties
        System.out.println("Testing Novel creation:");
        System.out.println("novel" + novel);


        System.out.println("\nTesting Digital Guide creation:");
        System.out.println("digitalGuide " + digitalGuide);


        System.out.println("\nTesting Vintage Book creation:");
        System.out.println("vintageBook " + vintageBook);


        System.out.println("Product creation tests completed successfully!");
    }

    /**
     * Test adding products to inventory
     * Tests: Multiple product additions, inventory building
     */
    private void testInventoryManagement() {
        System.out.println("\n>>> Testing Inventory Management");
        System.out.println();

        // Create product types
        ProductType physicalBook = new ProductType("Physical Book", true, true);
        ProductType ebook = new ProductType("E-Book", false, true);
        ProductType showcaseItem = new ProductType("Showcase Item", false, false);
        ProductType audiobook = new ProductType("Audiobook", false, true);

        // Create diverse product inventory
        Product[] products = {
                new Product("978-0-123456-78-9", "The Great Adventure", 2020, 15.99, 25, physicalBook),
                new Product("978-0-987654-32-1", "Grandma's Secret Recipes", 2019, 12.50, 10, physicalBook),
                new Product("978-1-111111-11-1", "Digital Photography Mastery", 2023, 29.99, 100, ebook),
                new Product("978-0-555555-55-5", "Ancient Wisdom Chronicles", 1850, 999.99, 1, showcaseItem),
                new Product("978-2-222222-22-2", "Learning Spanish Fast", 2022, 19.99, 15, ebook),
                new Product("978-3-333333-33-3", "Mystery of the Lost City", 2021, 14.75, 8, physicalBook),
                new Product("978-4-444444-44-4", "Meditation for Beginners", 2023, 24.99, 50, audiobook),
                new Product("978-5-555555-55-6", "Rare Manuscript Collection", 1200, 5000.00, 1, showcaseItem)
        };

        // Add products to inventory
        System.out.println("Adding products to inventory:");
        for (Product product : products) {
            bookstore.addProduct(product);
            System.out.println(" Added: " + product.getTitle() + " (Type: " + product.toString().split("type=")[1].split("\\{")[0] + ")");
        }

        System.out.println(" Inventory management tests completed successfully!");
    }

    private void testPurchaseScenarios() {
        System.out.println("\n>>> Testing Purchase Scenarios");
        System.out.println("-".repeat(50));

        try {
            // Test 1: Buy physical book (should trigger shipping)
            System.out.println("Test 1: Purchasing physical book 'The Great Adventure'");
            double amount1 = bookstore.buy("978-0-123456-78-9", 2, "john@reader.com", "123 Book Street, Reading City");
            System.out.printf("Purchase successful! Total: $%.2f%n", amount1);

            // Test 2: Buy ebook (should trigger email)
            System.out.println("\nTest 2: Purchasing ebook 'Digital Photography Mastery'");
            double amount2 = bookstore.buy("978-1-111111-11-1", 1, "photographer@pics.com", "456 Digital Ave");
            System.out.printf(" Purchase successful! Total: $%.2f%n", amount2);

            // Test 3: Buy multiple copies of cookbook
            System.out.println("\nTest 3: Purchasing multiple copies of 'Grandma's Secret Recipes'");
            double amount3 = bookstore.buy("978-0-987654-32-1", 3, "chef@kitchen.com", "789 Cooking Lane");
            System.out.printf("Purchase successful! Total: $%.2f%n", amount3);

            // Test 4: Buy audiobook
            System.out.println("\nTest 4: Purchasing audiobook 'Meditation for Beginners'");
            double amount4 = bookstore.buy("978-4-444444-44-4", 1, "zen@mindful.com", "101 Peace Street");
            System.out.printf("Purchase successful! Total: $%.2f%n", amount4);

            // Test 5: Buy Spanish learning ebook
            System.out.println("\nTest 5: Purchasing 'Learning Spanish Fast'");
            double amount5 = bookstore.buy("978-2-222222-22-2", 2, "student@language.com", "202 Study Hall");
            System.out.printf("Purchase successful! Total: $%.2f%n", amount5);

            double totalSpent = amount1 + amount2 + amount3 + amount4 + amount5;
            System.out.printf("\nTotal amount spent across all purchases: $%.2f%n", totalSpent);

        } catch (Exception e) {
            System.out.printf("Unexpected error during purchase: %s%n", e.getMessage());
        }

        System.out.println("Purchase scenario tests completed successfully!");
    }
    private void testOutdatedProductRemoval() {
        System.out.println("\n>>> Testing Outdated Product Removal");
        System.out.println("-".repeat(50));

        // Test removing very old products (over 500 years old)
        System.out.println("Removing products older than 500 years:");
        List<Product> ancientProducts = bookstore.removeOutdatedProducts(500);
        System.out.printf("Removed %d ancient products%n", ancientProducts.size());

        if (!ancientProducts.isEmpty()) {
            System.out.println("Ancient products removed:");
            for (Product product : ancientProducts) {
                System.out.println("  - " + product.getTitle() + " (Published: " + product.toString().split("publishYear=")[1].split(",")[0] + ")");
            }
        }

        // Test removing products older than 20 years
        System.out.println("\nRemoving products older than 20 years:");
        List<Product> oldProducts = bookstore.removeOutdatedProducts(20);
        System.out.printf("✓ Removed %d old products%n", oldProducts.size());

        if (!oldProducts.isEmpty()) {
            System.out.println("Old products removed:");
            for (Product product : oldProducts) {
                System.out.println("  - " + product.getTitle());
            }
        }

        // Test removing products older than 5 years
        System.out.println("\nRemoving products older than 5 years:");
        List<Product> recentlyOldProducts = bookstore.removeOutdatedProducts(5);
        System.out.printf("✓ Removed %d recently old products%n", recentlyOldProducts.size());

        System.out.println("✓ Outdated product removal tests completed successfully!");
    }

    private void testErrorHandling() {
        System.out.println("\n>>> Testing Error Handling");
        System.out.println("-".repeat(50));

        // Test 1: Try to buy non-existent product
        System.out.println("Test 1: Attempting to buy non-existent product");
        try {
            bookstore.buy("978-9-999999-99-9", 1, "ghost@nowhere.com", "999 Phantom Street");
            System.out.println("Should have thrown exception for non-existent product!");
        } catch (Exception e) {
            System.out.printf("Expected error caught: %s%n", e.getMessage());
        }

        // Test 2: Try to buy showcase item (not salable)
        System.out.println("\nTest 2: Attempting to buy showcase item");
        try {
            bookstore.buy("978-0-555555-55-5", 1, "collector@museum.com", "Historical District");
            System.out.println("Should have thrown exception for showcase item!");
        } catch (Exception e) {
            System.out.printf("Expected error caught: %s%n", e.getMessage());
        }

        // Test 3: Try to buy more than available stock
        System.out.println("\nTest 3: Attempting to buy more than available stock");
        try {
            // Try to buy 100 copies of a book that likely has less stock
            bookstore.buy("978-3-333333-33-3", 100, "bulk@buyer.com", "Warehouse District");
            System.out.println("Should have thrown exception for insufficient stock!");
        } catch (Exception e) {
            System.out.printf("Expected error caught: %s%n", e.getMessage());
        }

        // Test 4: Try to buy remaining showcase item
        System.out.println("\nTest 4: Attempting to buy remaining showcase item");
        try {
            bookstore.buy("978-5-555555-55-6", 1, "antique@dealer.com", "Rare Books Street");
            System.out.println("Should have thrown exception for showcase item!");
        } catch (Exception e) {
            System.out.printf("Expected error caught: %s%n", e.getMessage());
        }

        // Test 5: Try to buy after stock is depleted
        System.out.println("\nTest 5: Attempting to buy after depleting stock");
        try {
            // First, try to buy all remaining stock of a book
            bookstore.buy("978-3-333333-33-3", 8, "first@buyer.com", "Early Bird Lane");
            System.out.println("Successfully bought remaining stock");

            // Now try to buy more (should fail)
            bookstore.buy("978-3-333333-33-3", 1, "late@buyer.com", "Too Late Street");
            System.out.println("Should have thrown exception for depleted stock!");
        } catch (Exception e) {
            System.out.printf("Expected error caught: %s%n", e.getMessage());
        }

        System.out.println("Error handling tests completed successfully!");
    }

    private void testInventoryDisplay() {
        System.out.println("\n>>> Testing Inventory Display");
        System.out.println("-".repeat(50));

        System.out.println("Current inventory after all operations:");
        bookstore.displayInventory();

        System.out.println("Inventory display test completed successfully!");
    }

}
