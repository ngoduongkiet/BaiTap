package StreamAPI;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
public class Main {
	 public static void main(String[] args) {
	        // Sample Data
	        Customer c1 = new Customer(1, 2);
	        Customer c2 = new Customer(2, 1);

	        Product p1 = new Product("Lego", "Toys", 100.0);
	        Product p2 = new Product("Book", "Stationery", 50.0);
	        Product p3 = new Product("Car", "Toys", 200.0);

	        Order o1 = new Order(101, LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 20), "Delivered", c1, Arrays.asList(p1, p2));
	        Order o2 = new Order(102, LocalDate.of(2021, 3, 10), LocalDate.of(2021, 3, 15), "Delivered", c2, Arrays.asList(p2));
	        Order o3 = new Order(103, LocalDate.of(2021, 2, 25), LocalDate.of(2021, 3, 5), "Delivered", c1, Arrays.asList(p3));

	        List<Order> orders = Arrays.asList(o1, o2, o3);

	      
	        List<Product> discountedProducts = orders.stream()
	                .filter(o -> o.getCustomer().getTier() == 2)
	                .filter(o -> o.getOrderDate().isAfter(LocalDate.of(2021, 1, 31)) &&
	                             o.getOrderDate().isBefore(LocalDate.of(2021, 4, 2)))
	                .peek(System.out::println)
	                .flatMap(o -> o.getProducts().stream())
	                .filter(p -> p.getCategory().equalsIgnoreCase("Toys"))
	                .peek(p -> p.applyDiscount(0.1))
	                .distinct()
	                .collect(Collectors.toList());

	
	        System.out.println("\nSản phẩm đã giảm giá:");
	        discountedProducts.forEach(System.out::println);
	    }
}
