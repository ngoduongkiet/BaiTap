package bai3;

public class Main {
	 public static void main(String[] args) {
	        Buffer buffer = new Buffer(8);
	        Producer t1 = new Producer(100, buffer);
	        Consumer c1 = new Consumer(123, buffer);

	        t1.stop();
	        c1.start();
	       
	    }
	}