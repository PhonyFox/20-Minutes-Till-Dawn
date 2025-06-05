package com.tilldawn.controller;

import com.tilldawn.Main;
import com.tilldawn.model.Repository;
import com.tilldawn.view.GameView;

import java.util.ArrayList;
import java.util.List;

public class PauseMenuController {
    private final Repository repo;
    private final GameView gameView;

    public PauseMenuController(Repository repo, GameView gameView) {
        this.repo = repo;
        this.gameView = gameView;
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
        System.out.println("*************");
        Main.getMain().setScreen(gameView);
    }
}
