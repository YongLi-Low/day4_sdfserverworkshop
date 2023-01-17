package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {
    
    String dirPath = "data2";
    String fileName = "cookie.txt";

    List<String> cookieItems = null;

    // Create a function to read cookie.txt file
    public void readCookieFile() throws FileNotFoundException, IOException{
        cookieItems = new ArrayList<>();

        File file = new File(dirPath + "/" + fileName);

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String readString;

        while ((readString = br.readLine()) != null) {
            cookieItems.add(readString);
        }

    }

    // Randomly pick cookie from the jar
    public String returnCookie() {
        Random rand = new Random();

        if (cookieItems != null) {
            return cookieItems.get(rand.nextInt(cookieItems.size()));
        }
        else {
            return "There is no cookie found";
        }
    }

    // To show the cookies inside jar
    public void showCookies() {
        if (cookieItems != null) {
            cookieItems.forEach(ci -> System.out.println(ci));
        }
    };
}
