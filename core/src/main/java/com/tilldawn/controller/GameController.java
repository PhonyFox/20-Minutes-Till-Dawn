package com.tilldawn.controller;


import com.tilldawn.model.Repository;
import com.tilldawn.view.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WeaponController weaponController;
    private EnemiesController enemiesController;
    private final Repository repo;


    public GameController(Repository repo) {
        this.repo = repo;
        this.weaponController = new WeaponController(repo.getCurrentUser().getPlayer().getWeapon());
        this.playerController = new PlayerController(repo.getCurrentUser().getPlayer(), weaponController);
        this.enemiesController = new EnemiesController(repo, new ZoneController());
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void updateGame(float delta) {
        playerController.update(delta, enemiesController.getEnemies());
        enemiesController.update(delta);
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public Repository getRepo() {
        return repo;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }
}
