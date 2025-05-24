package com.tilldawn.controller;

import com.tilldawn.model.Repository;

public class MainMenuController {
    private final Repository repo;

    public MainMenuController(Repository repo) {
        this.repo = repo;
    }

    public void continueSavedGame() {
        if (repo.getCurrentUser().hasSavedGame()) {
            //@
        } else {
            //@ error popup
        }
    }

    public void goToSetting() {

    }

    public void goToProfile() {

    }

    public void goToGameMenu() {

    }

    public void goToScoreboard() {

    }

    public void goToTalentMenu() {

    }

    public void logout() {

    }

    public Repository getRepo() {
        return repo;
    }
}
