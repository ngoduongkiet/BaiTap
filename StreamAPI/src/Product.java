package StreamAPI;

import java.util.Objects;

public class Product {
	 private String name;
	    private String category;
	    private double price;

	    public Product(String name, String category, double price) {
	        this.name = name;
	        this.category = category;
	        this.price = price;
	    }

	    public String getCategory() {
	        return category;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void applyDiscount(double discount) {
	        this.price = price * (1 - discount);
	    }

	    public String getName() {
	        return name;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Product)) return false;
	        Product product = (Product) o;
	        return Objects.equals(name, product.name);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(name);
	    }

	    @Override
	    public String toString() {
	        return name + " - " + category + " - " + price;
	    }
}
