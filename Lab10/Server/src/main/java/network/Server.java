package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static final int PORT = 8100;
    public Server() throws IOException {
        ServerSocket serverSocket = null;
        SocialNetwork socialNetwork = new SocialNetwork();
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket, socialNetwork).start();
            }
        }
        catch (IOException e) {
            System.err.println("Error: " + e);
        }
        finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();

    }
}
