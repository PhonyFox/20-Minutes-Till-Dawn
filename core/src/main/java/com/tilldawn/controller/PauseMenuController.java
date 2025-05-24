package com.tilldawn.controller;

import com.tilldawn.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class PauseMenuController {
    private final Repository repo;

    public PauseMenuController(Repository repo) {
        this.repo = repo;
    }

    public String getCheatCodes() {
        return "- GODMODE : G\n- INFINITE_AMMO : I\n- KILL_ALL : K";
    }

    public String getAcquiredAbilities() {
        return String.join("\n", repo.getCurrentUser().getPlayer().getAcquiredAbilities());
    }

    public void toggleGrayscale() {
        repo.getCurrentUser().getUserSetting().toggleGrayscale();
    }

    public void saveAndExitGame() {
        //@
    }

    public void giveUp() {
        //@
    }

    public void resumeGame() {
        //@
    }
}
