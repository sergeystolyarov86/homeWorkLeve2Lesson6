import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    private static Socket socket;
    private static int PORT = 8190;
    private final static String IP_ADDRESS = "localhost";

    public static void main(String[] args) {
        try {

            socket = new Socket(IP_ADDRESS, PORT);
            System.out.println("Chat started");

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc=new Scanner(System.in);

            new Thread(()->{ while (true) {
                    String strIn = in.nextLine();
                    System.out.println("Server: " + strIn);
            }}).start();

            while (true){
                String strOut = sc.nextLine();
                out.println(strOut);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



