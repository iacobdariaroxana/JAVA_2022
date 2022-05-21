package com.laborator11.network;

import entities.Friend;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.AbstractRepository;
import repositories.FriendsRepository;
import repositories.UserRepository;
import com.laborator11.network.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable("id") int id) {
        User user = userService.findUser(id);
        if (user == null) {
            return new ResponseEntity<>(
                    "User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

    @GetMapping
    public List<User> getPersons() {
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestParam String name) {
        return new ResponseEntity<>(
                "User added successfully id : " + userService.createUser(name), HttpStatus.CREATED);
    }

    @PostMapping(value = "/obj", consumes = "application/json")
    public ResponseEntity<String> createUserObject(@RequestBody User databaseUser) {
        return new ResponseEntity<>(
                "User added successfully id : " + userService.createUserObject(databaseUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable int id, @RequestParam String name) {
        if (!userService.updateUser(id, name)) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        if (!userService.deleteUser(id)) {
            return new ResponseEntity<>("User not found", HttpStatus.GONE);
        }
        return new ResponseEntity<>("User removed", HttpStatus.OK);
    }
}
