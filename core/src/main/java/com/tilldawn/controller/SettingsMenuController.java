package com.tilldawn.controller;

import com.tilldawn.Main;
import com.tilldawn.model.Repository;
import com.tilldawn.model.User;
import com.tilldawn.view.GameView;
import com.tilldawn.view.MainMenuView;

public class SettingsMenuController {
    private final Repository repo;
    private final User currentUser;

    public SettingsMenuController(Repository repo) {
        this.repo = repo;
        this.currentUser = repo.getCurrentUser();
    }

    public void setMusicVolume(float musicVolume) {
        currentUser.getUserSetting().setMusicVolume(musicVolume);
    }

    public float getMusicVolume() {
        return currentUser.getUserSetting().getMusicVolume();
    }

    public void toggleSFX() {
        currentUser.getUserSetting().toggleSFX();
    }

    public boolean isSfxEnable() {
        return currentUser.getUserSetting().isSfxEnable();
    }

    public void setCurrentMusic(String currentMusic) {
        currentUser.getUserSetting().setCurrentMusic(currentMusic);
    }

    public String getCurrentMusic() {
        return currentUser.getUserSetting().getCurrentMusic();
    }

    public void toggleAutoReload() {
        currentUser.getUserSetting().toggleAutoReload();
    }

    public boolean isAutoReload() {
        return currentUser.getUserSetting().isAutoReload();
    }

    public void toggleGrayscale() {
        repo.getCurrentUser().getUserSetting().toggleGrayscale();
    }

    public boolean isGrayscale() {
        return repo.getCurrentUser().getUserSetting().isGrayscale();
    }

    public void changeKeyBinding(String action, int newKey) {
        repo.getCurrentUser().getUserSetting().setKey(action, newKey);
    }

    public void goToMainMenu() {
        Main.getMain().setScreen(new GameView(new GameController(repo)));
    }
}
