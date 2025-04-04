package bai3;
import java.util.ArrayList;
public class Buffer {
    private int capacity;
    private ArrayList<Integer> products = new ArrayList<>();

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addProduct(int product, int producerId) {
        while (products.size() >= capacity) {
            try {
                wait(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products.add(product);
        System.out.println("Nhan vien banh mi " + producerId + " da them banh mi: " + product);
        System.out.println("So luong banh mi co trong kho kho: " + products.size());
        notifyAll(); 
    }

    public synchronized void removeProduct(int consumerId) {
        while (products.isEmpty()) {
            try {
                wait(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Khach hang " + consumerId + " da mua banh mi " + products.get(0));
        products.remove(0);
        System.out.println("So luong banh mi ton kho: " + products.size());
        notifyAll(); 
    }

    public int getSize() {
        return this.products.size();
    }

    public int getCapacity() {
        return this.capacity;
    }
}
