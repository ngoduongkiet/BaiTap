package baitapjava;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class RealEstateCrawler {
	 private static final String[] URLS = {
	            "https://batdongsan.com.vn/nha-dat-ban",
	            "https://alonhadat.com.vn/nha-dat-ban",
	            "https://nhadat247.com.vn/nha-dat-ban"
	    };
	    private static final int THREAD_COUNT = 3;
	    private static final List<RealEstate> realEstateList = new ArrayList<>();

	    public static void main(String[] args) {
	        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

	        for (String url : URLS) {
	            executor.execute(() -> crawlPage(url));
	        }

	        executor.shutdown();
	        try {
	            executor.awaitTermination(10, TimeUnit.MINUTES);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        saveToCSV("real_estate_data.csv");
	        saveToCSV("real_estate_data.json");
	    }

		private static Object crawlPage(String url) {
			// TODO Auto-generated method stub
			return null;
		}

		private static void saveToCSV(String string) {
			// TODO Auto-generated method stub
			
		}

		public static void main(String[] args) {
			RealEstate 
		}
}
