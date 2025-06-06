package com.tilldawn.controller;

import com.tilldawn.Main;
import com.tilldawn.model.Repository;
import com.tilldawn.model.User;
import com.tilldawn.view.MainMenuView;
import com.tilldawn.view.SignupMenuView;

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

        if (!isStrongPassword(newPassword)) {
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
        Main.getMain().setScreen(new SignupMenuView(new SignupMenuController(repo)));
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Repository getRepo() {
        return repo;
    }

    public void goToMainMenu() {
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(repo)));
    }

    public boolean isStrongPassword(String password) {
        return password.length() >= 8 &&
            password.matches(".*[A-Z].*") &&
            password.matches(".*[0-9].*") &&
            password.matches(".*[@%$#&*()_].*");
    }
}
