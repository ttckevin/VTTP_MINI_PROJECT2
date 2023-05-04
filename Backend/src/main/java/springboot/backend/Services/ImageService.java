package springboot.backend.Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;

@Service
public class ImageService {

    @Value("${UNSPLASH_ACCESS_KEY}")
    String UNSPLASH_ACCESS_KEY;

    private static final String unSplash_URL = 
    "https://api.unsplash.com/search/photos?query=";

    public static String readFile() throws IOException{
        File file = new File("C:/Users/T1me/Desktop/my-project/payload.txt");
        BufferedReader br
            = new BufferedReader(new FileReader(file));
        String st;
        st = br.readLine();
        br.close();

        return st;
    }


    public List<String> getImages(String query) throws IOException{
        List<String> l = new ArrayList<>();
        String clientId = "&client_id=6Ib5d4omxi3vqMz69SrYnCEi2_I_HHB0bLmlM3AWrNA";
        System.out.println("At the imageservice: " + query);
        String imageDirectory = unSplash_URL + query + clientId;
        RequestEntity<Void> req = RequestEntity.get(imageDirectory).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        
        String payload = resp.getBody();
        // String payload = readFile();
        StringReader sr = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(sr);
        JsonObject object = jsonReader.readObject(); 
        JsonArray results = object.getJsonArray("results");
        int arrSize = results.size();
        for(int i = 0; i < arrSize; i++){
            JsonObject result = results.getJsonObject(i);
            JsonObject imageTypes = result.getJsonObject("urls");
            JsonString imageUrl = imageTypes.getJsonString("small");
            l.add(imageUrl.getString());
        }
        return l;
    }

    public Blob convertToBlob(MultipartFile file) throws SQLException, IOException {
    byte[] bytes = file.getBytes();
    Blob blob = new SerialBlob(bytes);
    return blob;
}
}
