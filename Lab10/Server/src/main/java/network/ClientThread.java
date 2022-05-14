package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientThread extends Thread {
    private Socket socket = null;
    private SocialNetwork socialNetwork;
    private String userName;
    private boolean isRegistered;
    private long startIdle;

    public ClientThread(Socket socket, SocialNetwork socialNetwork) {
        this.socket = socket;
        this.socialNetwork = socialNetwork;
        this.isRegistered = false;
    }

    public void run() {
        PrintWriter out = null;
        try {
            socket.setSoTimeout(15000);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            while (true) {
                out = new PrintWriter((socket.getOutputStream()));
                String request = in.readLine();
                String command = null;
                int pos = request.indexOf(' ');
                if (pos != -1) {
                    command = request.substring(0, pos);
                } else {
                    command = request;
                }
                System.err.println("Server received the request " + command);

                switch (command) {
                    case "register" -> {
                        if (!isRegistered) {
                            userName = request.substring(pos + 1);
                            isRegistered = socialNetwork.addUser(userName, out);
                        } else {
                            out.println("You have already registered one account under this session!");
                            out.flush();
                        }
                    }
                    case "login" -> {
                        if (!isRegistered || userName.equals(request.substring(pos + 1))) {
                            userName = request.substring(pos + 1);
                            socialNetwork.loginUser(userName, socket, out);
                        } else {
                            out.println("During this session you must login " +
                                    "with the previously registered username.");
                            out.flush();
                        }
                    }
                    case "friend" -> {
                        String friendList = request.substring(pos + 1);
                        socialNetwork.addFriends(userName, friendList, out);
                    }
                    case "send" -> {
                        String message = request.substring(pos + 1);
                        socialNetwork.send(userName, message, out);
                    }
                    case "read" -> {
                        socialNetwork.read(userName, out);
                    }
                    case "stop" -> {
                        out.println("Server stopped.");
                        out.flush();
                        socket.close();
                        return;
                    }
                    default -> {
                        out.println("Command not found..");
                        out.flush();
                    }
                }
            }
        } catch (SocketTimeoutException e) {
            System.err.println("Server timed out!");
            out.println("Server timed out!");
            out.flush();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error: " + e);
            }
        }
    }
}
