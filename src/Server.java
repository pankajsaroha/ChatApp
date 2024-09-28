import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        final ServerSocket serverSocket;
        final Socket clientSocket;
        final PrintWriter out;
        final BufferedReader in;
        final Scanner sc = new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(8000);
            clientSocket = serverSocket.accept();
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
                    String msg;
                    msg = in.readLine();
                    while (msg != null) {
                        System.out.println("Client: " + msg);
                        msg = in.readLine();
                    }
                    System.out.println("Client Disconnected");
                    out.close();
                    clientSocket.close();
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiver.start();
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }
}
