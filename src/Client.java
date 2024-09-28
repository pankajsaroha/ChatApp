import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main (String args[]) {
        final Socket clientSocket;
        final PrintWriter out;
        final BufferedReader in;
        final Scanner sc = new Scanner(System.in);
        try {
            clientSocket = new Socket("127.0.0.1", 8000);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread sender = new Thread(() -> {
               String msg;
               while (true) {
                   msg = sc.nextLine();
                   out.println(msg);
                   out.flush();
               }
            });
            sender.start();

            Thread receiver = new Thread(() -> {
                try {
                    String msg = in.readLine();
                    while (msg != null) {
                        System.out.println("Server: " + msg);
                        msg = in.readLine();
                    }
                    System.out.println("Server disconnected !!!");
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
            receiver.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
