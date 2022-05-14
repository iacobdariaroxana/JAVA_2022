package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        Socket socket = null;
        try {
            socket = new Socket(serverAddress, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Enter your command:");
                String request = scanner.nextLine();

                if (request.equals("exit") || request.equals("stop")) {
                    out.println("stop");
                    out.flush();
                } else {
                    out.println(request);
                    out.flush();
                }

                String response = in.readLine();
                if (response.substring(0, response.indexOf(" ")).equals("message")) {
                    System.out.println("New message! " + response.substring(response.indexOf(" ")));
                    response = in.readLine();
                }
                if (response.substring(0, response.indexOf(" ")).equals("inbox")) {
                    System.out.println(response.replace(',', '\n'));
                } else {
                    System.out.println(response);
                }
                if (response.equals("Server stopped.")) {
                    socket.close();
                    return;
                }
            }
        } catch (SocketException e) {
            System.out.println("Server timed out!");
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("No server listening: " + e);
        }
    }
}
