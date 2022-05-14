package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class SocialNetwork {
    Set<User> users = new HashSet<>();

    public SocialNetwork() {

    }

    public boolean addUser(String userName, PrintWriter out) {
        User newUser = new User(userName);
        if (users.contains(newUser)) {
            out.println(userName + " has already been registered!");
            out.flush();
            return false;
        } else {
            users.add(newUser);
            out.println(userName + " has been registered!");
            out.flush();
            return true;
        }
    }

    public void loginUser(String userName, Socket socket, PrintWriter out) {
        User user = getUser(userName);
        if (user != null) {
            if (user.isStatus()) {
                out.println("User already logged in!");
            } else {
                out.println(userName + " has logged on!");
                user.setStatus(true);
                user.setSocket(socket);
            }
        } else {
            out.println("User not found! Please register first!");
        }
        out.flush();
    }

    public void addFriends(String userName, String friendList, PrintWriter out) {
        User user = getUser(userName);
        if (user == null) {
            out.println("User not found! Please register first!");
        } else if (user.isStatus()) {
            String[] friends = friendList.split("\s+");
            User currentFriend;
            StringBuilder friendsAdded = new StringBuilder(userName);
            friendsAdded.append(" has added the following friends: ");
            for (String friend : friends) {
                currentFriend = getUser(friend);
                if (currentFriend != null && currentFriend != user) {
                    user.getFriends().add(currentFriend);
                    currentFriend.getFriends().add(user);
                    friendsAdded.append(friend).append(" ");
                }
            }
            out.println(friendsAdded);
        } else {
            out.println("You must login first!");
        }
        out.flush();
    }

    public void send(String userName, String message, PrintWriter out) {
        User user = getUser(userName);
        if (user == null) {
            out.println("User not found! Please register first!");
        } else if (user.isStatus()) {
            try {
                for (User friend : user.getFriends()) {
                    PrintWriter friendOut = new PrintWriter((friend.getSocket().getOutputStream()));

                    friendOut.println("message " + userName + " says <<" + message + ">> to all friends.");
                    friendOut.flush();

                    if (!friend.getMessages().containsKey(user)) {
                        friend.getMessages().put(user, new ArrayList<>());
                        friend.getMessages().get(user).add(message);
                    } else {
                        friend.getMessages().get(user).add(message);
                    }
                }
                out.println("Message sent!");
            } catch (IOException e) {
                System.err.println("Error " + e);
            }
        } else {
            out.println("You must login first!");
        }
        out.flush();
    }


    public void read(String userName, PrintWriter out) {
        User user = getUser(userName);
        if (user == null) {
            out.println("User not found! Please register first!");
        } else if (user.isStatus()) {
            StringBuilder inbox = new StringBuilder("inbox");
            for (Map.Entry<User, List<String>> entry : user.getMessages().entrySet()) {
                inbox.append(" [").append(entry.getKey().getName()).append("] ");
                for (String message : entry.getValue()) {
                    inbox.append("<< ").append(message).append(" >>,");
                }
            }
            if (inbox.toString().equals("inbox")) {
                out.println("You have no messages.");
            } else {
                out.println(inbox);
            }
        } else {
            out.println("You must login first!");
        }
        out.flush();
    }

    public User getUser(String userName) {
        Optional<User> toBeFound = users.stream().
                filter(user -> user.getName().equals(userName)).findFirst();
        return toBeFound.orElse(null);
    }
}
