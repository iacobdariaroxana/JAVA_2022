package com.laborator11.network.services;

import entities.Friend;
import entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.AbstractRepository;
import repositories.FriendsRepository;
import repositories.UserRepository;

import java.util.List;

@Component
@Service
public class UserService {
    private AbstractRepository<User, Integer> dataUser = new UserRepository("User");
    private FriendsRepository dataFriend = new FriendsRepository("Friend");

    public User findUser(int id) {
        User user = dataUser.findById(id);
        return user;
    }

    public List<User> getUsers() {
        return dataUser.findAll();
    }

    public Long createUser(String name) {
        dataUser.create(new User(name));
        return dataUser.findByName(name).get(0).getId();
    }

    public Long createUserObject(User user) {
        dataUser.create(user);
        return dataUser.findByName(user.getUsername()).get(0).getId();
    }

    public boolean updateUser(int id, String name) {
        User user = dataUser.findById(id);
        if (user == null) {
            return false;
        }
        dataFriend.updateByUsername1(user.getUsername(), name);
        dataFriend.updateByUsername2(user.getUsername(), name);
        Friend.updateFriend(user.getUsername(), name);
        dataUser.updateUsername(user.getUsername(), name);
        user.setUsername(name);
        return true;
    }

    public boolean deleteUser(int id) {
        User user = dataUser.findById(id);
        if (user == null) {
            return false;
        }
        dataUser.delete(id);
        dataFriend.deleteByUsername(user.getUsername());
        return true;
    }
}
