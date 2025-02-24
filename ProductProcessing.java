import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductProcessing {
    static class Product {
        private String name;
        private String category;
        private double price;
        private int quantity;
        
        public Product(String name, String category, double price, int quantity) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.quantity = quantity;
        }
        
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }
        
        @Override
        public String toString() {
            return "Product{name='" + name + "', category='" + category + "', price=" + price + ", quantity=" + quantity + "}";
        }
    }
    
    public static void main(String[] args) {
        // Create a large dataset of products
        List<Product> products = createProductDataset();
        
        // 1. Group products by category
        Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        
        System.out.println("Products grouped by category:");
        productsByCategory.forEach((category, productList) -> {
            System.out.println("\nCategory: " + category + " (" + productList.size() + " products)");
            productList.forEach(product -> System.out.println("  - " + product.getName() + ": $" + product.getPrice()));
        });
        
        // 2. Find the most expensive product in each category
        System.out.println("\nMost expensive product in each category:");
        productsByCategory.forEach((category, productList) -> {
            Optional<Product> mostExpensive = productList.stream()
                    .max(Comparator.comparing(Product::getPrice));
            mostExpensive.ifPresent(product -> System.out.println(category + ": " + product.getName() + " ($" + product.getPrice() + ")"));
        });
        
        // 3. Calculate the average price of all products
        double averagePrice = products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage price of all products: $" + String.format("%.2f", averagePrice));
        
        // 4. Find products with low stock (quantity < 10)
        System.out.println("\nProducts with low stock (quantity < 10):");
        products.stream()
                .filter(product -> product.getQuantity() < 10)
                .forEach(product -> System.out.println(product.getName() + " - " + product.getQuantity() + " units remaining"));
        
        // 5. Calculate total inventory value by category
        System.out.println("\nTotal inventory value by category:");
        Map<String, Double> inventoryValueByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summingDouble(product -> product.getPrice() * product.getQuantity())
                ));
        
        inventoryValueByCategory.forEach((category, value) -> 
                System.out.println(category + ": $" + String.format("%.2f", value)));
    }
    
    // Helper method to create a large dataset
    private static List<Product> createProductDataset() {
        List<Product> products = new ArrayList<>();
        
        // Electronics
        products.add(new Product("Smartphone X", "Electronics", 999.99, 50));
        products.add(new Product("Laptop Pro", "Electronics", 1499.99, 30));
        products.add(new Product("Wireless Earbuds", "Electronics", 129.99, 100));
        products.add(new Product("Smart TV", "Electronics", 799.99, 25));
        products.add(new Product("Digital Camera", "Electronics", 599.99, 15));
        products.add(new Product("Gaming Console", "Electronics", 449.99, 8));
        
        // Clothing
        products.add(new Product("Denim Jeans", "Clothing", 49.99, 200));
        products.add(new Product("Winter Jacket", "Clothing", 119.99, 45));
        products.add(new Product("Cotton T-Shirt", "Clothing", 19.99, 350));
        products.add(new Product("Running Shoes", "Clothing", 89.99, 80));
        products.add(new Product("Formal Shirt", "Clothing", 59.99, 65));
        products.add(new Product("Casual Dress", "Clothing", 79.99, 5));
        
        // Home & Kitchen
        products.add(new Product("Coffee Maker", "Home & Kitchen", 79.99, 40));
        products.add(new Product("Blender", "Home & Kitchen", 59.99, 35));
        products.add(new Product("Toaster", "Home & Kitchen", 39.99, 25));
        products.add(new Product("Microwave Oven", "Home & Kitchen", 149.99, 20));
        products.add(new Product("Dining Set", "Home & Kitchen", 199.99, 12));
        products.add(new Product("Cookware Set", "Home & Kitchen", 249.99, 7));
        
        // Books
        products.add(new Product("Programming Guide", "Books", 34.99, 50));
        products.add(new Product("Novel Collection", "Books", 24.99, 120));
        products.add(new Product("Children's Story", "Books", 14.99, 200));
        products.add(new Product("Cookbook", "Books", 29.99, 75));
        products.add(new Product("Self-Help Book", "Books", 19.99, 90));
        products.add(new Product("Reference Book", "Books", 49.99, 9));
        
        return products;
    }
}
