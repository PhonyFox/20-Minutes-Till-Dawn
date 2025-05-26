package com.tilldawn.controller;

import com.tilldawn.Main;
import com.tilldawn.model.Repository;
import com.tilldawn.view.*;

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
        Main.getMain().setScreen(new SettingsMenuView(new SettingsMenuController(repo)));
    }

    public void goToProfile() {
        Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(repo)));
    }

    public void goToGameMenu() {
        Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(repo)));
    }

    public void goToScoreboard() {
        Main.getMain().setScreen(new ScoreboardMenuView(new ScoreboardMenuController(repo)));
    }

    public void goToTalentMenu() {
        Main.getMain().setScreen(new HintMenuView(new HintMenuController(repo)));
    }

    public void logout() {
        Main.getMain().setScreen(new SignupMenuView(new SignupMenuController(repo)));
    }

    public Repository getRepo() {
        return repo;
    }
}
