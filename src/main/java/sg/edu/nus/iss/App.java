package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException{
        String dirPath = "data2";
        File newDirectory = new File(dirPath);

        //Creating a directory to store the cookie.txt file
        if (newDirectory.exists()) {
            System.out.println("Directory aleady exist!");
        }
        else {
            newDirectory.mkdir();
        }

        Cookie cookie = new Cookie();
        cookie.readCookieFile();
        System.out.println(cookie.returnCookie());

        ServerSocket ss = new ServerSocket(7000);
        // Establish connection and wait for client to connect
        Socket s = ss.accept();

        try (InputStream is = s.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            String msgReceived = "";

            while (msgReceived.equals("close")) {
                msgReceived = dis.readUTF();

                if (msgReceived.equalsIgnoreCase("get cookie")) {
                    String cookieValue = cookie.returnCookie();
                    System.out.println(cookieValue);
                }
            }
        }
        catch (EOFException ex) {  // handling end of file exception
            s.close();
            ss.close();
        }
    }
}
