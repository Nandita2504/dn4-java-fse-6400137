import java.util.Arrays;
import java.util.Comparator;

 class Product {
    String productId;
    String productName;
    String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String toString() {
        return productId + " - " + productName + " (" + category + ")";
    }
}

class SearchAlgorithms {
    
    public static int linearSearch(Product[] products, String targetName) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equalsIgnoreCase(targetName)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(Product[] products, String targetName) {
        int left = 0, right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = products[mid].productName.compareToIgnoreCase(targetName);

            if (compare == 0) return mid;
            else if (compare < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "T-shirt", "Clothing"),
            new Product("P002", "Novels", "Books"),
            new Product("P003", "Chair", "Furniture"),
            new Product("P004", "Table", "Furniture"),
            new Product("P005", "Monitor", "Electronics")
        };

        // Linear search
        int indexLinear = SearchAlgorithms.linearSearch(products, "Chair");
        if (indexLinear != -1)
            System.out.println("Linear Search: Found -> " + products[indexLinear]);
        else
            System.out.println("Linear Search: Product not found.");
            
        //Sorting
        Arrays.sort(products, Comparator.comparing(p -> p.productName));

        // Binary search
        int indexBinary = SearchAlgorithms.binarySearch(products, "Chair");
        if (indexBinary != -1)
            System.out.println("Binary Search: Found -> " + products[indexBinary]);
        else
            System.out.println("Binary Search: Product not found.");
    }
}
