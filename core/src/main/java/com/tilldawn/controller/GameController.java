package com.tilldawn.controller;


import com.tilldawn.model.Repository;
import com.tilldawn.view.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private final Repository repo;


    public GameController(Repository repo) {
        this.repo = repo;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void updateGame(float delta) {
        playerController.update(delta);
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public Repository getRepo() {
        return repo;
    }
}
