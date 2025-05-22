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
}
