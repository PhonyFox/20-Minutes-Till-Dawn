package com.tilldawn.controller;

import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.Repository;
import com.tilldawn.model.Sfx;
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
        MusicController.getInstance().setVolume(musicVolume);
    }

    public float getMusicVolume() {
        return currentUser.getUserSetting().getMusicVolume();
    }

    public void toggleSFX() {
        currentUser.getUserSetting().toggleSFX();
        Sfx.toggleSfx();
    }

    public boolean isSfxEnable() {
        return currentUser.getUserSetting().isSfxEnable();
    }

    public void setCurrentMusic(String currentMusic) {
        currentUser.getUserSetting().setCurrentMusic(currentMusic);
        switch (currentMusic.trim().toLowerCase()) {
            case "default":
                MusicController.getInstance().loadMusic(AssetManager.getAssetManager().getFirstMusic(), true);
                MusicController.getInstance().play();
                break;
            case "battle":
                MusicController.getInstance().loadMusic(AssetManager.getAssetManager().getSecondMusic(), true);
                MusicController.getInstance().play();
                break;
            case "calm":
                MusicController.getInstance().loadMusic(AssetManager.getAssetManager().getThirdMusic(), true);
                MusicController.getInstance().play();
                break;
            case "menu":
                MusicController.getInstance().loadMusic(AssetManager.getAssetManager().getStartingMusic(), true);
                MusicController.getInstance().play();
                break;
        }
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
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(repo)));
    }
}
