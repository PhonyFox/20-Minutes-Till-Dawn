package com.tilldawn.controller;

import com.tilldawn.model.Repository;
import com.tilldawn.model.User;

public class ProfileMenuController {
    private final User currentUser;
    private final Repository repo;

    public ProfileMenuController(Repository repo) {
        this.repo = repo;
        this.currentUser = repo.getCurrentUser();
    }

    public boolean changeUsername(String newUsername) {
        if (repo.getCurrentUser().getUsername().equals(newUsername)) {
            return false;
        }
        currentUser.setUsername(newUsername);
        return true;
    }

    public boolean changePassword(String newPassword) {
        if (newPassword.length() < 6) {
            return false;
        }

        currentUser.setPassword(newPassword);
        return true;
    }

    public void changeAvatar(String newAvatar) {
        currentUser.setAvatarPath(newAvatar);
    }

    public void deleteAccount() {
        repo.deleteUser(repo.getCurrentUser().getUsername());
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Repository getRepo() {
        return repo;
    }
}
