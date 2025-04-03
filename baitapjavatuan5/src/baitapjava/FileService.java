package baitapjava;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class FileService {
	public static void saveToCSV(String fileName, List<RealEstate> realEstateList) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Title,Price,Address,Area,Description\n");
            for (RealEstate estate : realEstateList) {
                writer.append(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n",
                        estate.title, estate.price, estate.address, estate.area, estate.description));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToJSON(String fileName, List<RealEstate> realEstateList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(realEstateList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
