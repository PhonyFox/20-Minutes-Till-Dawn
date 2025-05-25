package com.tilldawn.controller;


import com.tilldawn.view.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;


    public GameController() {
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
}
