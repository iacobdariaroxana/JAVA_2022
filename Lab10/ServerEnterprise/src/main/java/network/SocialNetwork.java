package network;

import entities.Friend;
import repositories.AbstractRepository;
import repositories.FriendsRepository;
import repositories.UserRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.*;

public class SocialNetwork {
    Set<User> users = new HashSet<>();

    public SocialNetwork() {
        AbstractRepository<entities.User, Integer> dataUser = new UserRepository("User");
        AbstractRepository<Friend, Integer> dataFriend = new FriendsRepository("Friend");
        List<entities.User> databaseUsers = dataUser.findAll();
        for(entities.User basicUser : databaseUsers) {
            users.add(new User(basicUser.getUsername()));
        }
        for (User user : users) {
            List<Friend> friendList= dataFriend.findByName(user.getName());
            for(Friend friend : friendList) {
                user.getFriends().add(getUser(friend.getUser2()));
            }
        }
        StoreMapSVG.storeMap(Paths.get("src/main/test.svg"), this);
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
            AbstractRepository<entities.User, Integer> dataUser = new UserRepository("User");
            dataUser.create(new entities.User(userName));
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
            AbstractRepository<Friend, Integer> dataFriend = new FriendsRepository("Friend");
            for (String friend : friends) {
                currentFriend = getUser(friend);
                if (currentFriend != null && currentFriend != user) {
                    user.getFriends().add(currentFriend);
                    currentFriend.getFriends().add(user);
                    friendsAdded.append(friend).append(" ");
                    dataFriend.create(new Friend(userName, currentFriend.getName()));
                    dataFriend.create(new Friend(currentFriend.getName(), userName));
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
                AbstractRepository<entities.User, Integer> dataUser = new UserRepository("User");
                for (User friend : user.getFriends()) {
                    if (friend.isStatus()) {
                        PrintWriter friendOut = new PrintWriter((friend.getSocket().getOutputStream()));

                        friendOut.println("message " + userName + " says <<" + message + ">> to all friends.");
                        friendOut.flush();

                        if (!friend.getMessages().containsKey(user)) {
                            friend.getMessages().put(user, new ArrayList<>());
                            friend.getMessages().get(user).add(message);
                        } else {
                            friend.getMessages().get(user).add(message);
                        }
                        dataUser.updateMessages(friend.getName(), message);

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

    public Set<User> getUsers() {
        return users;
    }
}
