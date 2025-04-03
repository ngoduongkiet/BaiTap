package baitapjava;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
        List<RealEstate> realEstateList = new ArrayList<>();

        // Crawl dữ liệu
        CrawlerService.crawlData(realEstateList);

        // Lưu dữ liệu vào file
        FileService.saveToCSV("real_estate_data.csv", realEstateList);
        FileService.saveToJSON("real_estate_data.json", realEstateList);
        
        System.out.println("Crawl completed. Data saved to CSV and JSON.");
    }
}
