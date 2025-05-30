package com.tilldawn.model;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<User> users = new ArrayList<User>();
    private User currentUser;
    private long startingTime;

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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void deleteUser(String username) {
        users.remove(currentUser);
        currentUser = null;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setStartingTime(long startingTime) {
        this.startingTime = startingTime;
    }

    public long getStartingTime() {
        return startingTime;
    }
}
