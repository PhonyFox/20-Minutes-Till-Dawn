package com.tilldawn.controller;

import com.tilldawn.model.Repository;
import com.tilldawn.model.User;

public class LoginMenuController {
    private final Repository repo;

    public LoginMenuController(Repository repo) {
        this.repo = repo;
    }

    public String login(String username, String password) {
        if (!repo.isUsernameTaken(username)) {
            return "Username does not exist!";
        }

        if (!repo.isPasswordCorrect(username, password)) {
            return "Incorrect password!";
        }

        return "Login successful!";
    }

    public User getUser(String username) {
        return repo.getUser(username);
    }

    public String resetPassword(String username, String answer, String newPassword) {
        if (!repo.isUsernameTaken(username)) {
            return "Username does not exist!";
        }

        User user = repo.getUser(username);
        if (!user.getSecurityAnswer().equalsIgnoreCase(answer.trim())) {
            return "Incorrect security answer!";
        }

        user.setPassword(newPassword);
        return "Password reset successful!";
    }
}
