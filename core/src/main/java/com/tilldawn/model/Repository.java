package com.tilldawn.model;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<User> users = new ArrayList<User>();

    public boolean isUsernameTaken(String username) {
        return users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean isPasswordCorrect(String username, String password) {
        User user = users.stream()
            .filter(u -> u.getUsername().equalsIgnoreCase(username))
            .findFirst()
            .orElse(null);
        return user.getPassword().equalsIgnoreCase(password);
    }

    public User getUser(String username) {
        return users.stream()
            .filter(u -> u.getUsername().equalsIgnoreCase(username))
            .findFirst()
            .orElse(null);
    }
}
