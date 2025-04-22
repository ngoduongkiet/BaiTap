package StreamAPI;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
public class Order {
	 private int id;
	    private LocalDate orderDate;
	    private LocalDate deliveryDate;
	    private String status;
	    private Customer customer;
	    private List<Product> products;

	    public Order(int id, LocalDate orderDate, LocalDate deliveryDate, String status, Customer customer, List<Product> products) {
	        this.id = id;
	        this.orderDate = orderDate;
	        this.deliveryDate = deliveryDate;
	        this.status = status;
	        this.customer = customer;
	        this.products = products;
	    }

	    public LocalDate getOrderDate() {
	        return orderDate;
	    }

	    public Customer getCustomer() {
	        return customer;
	    }

	    public List<Product> getProducts() {
	        return products;
	    }

	    @Override
	    public String toString() {
	        return "Order #" + id + " - " + orderDate + " - Customer tier: " + customer.getTier();
	    }
}
