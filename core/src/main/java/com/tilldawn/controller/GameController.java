package com.tilldawn.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.Random;
import com.tilldawn.model.Repository;
import com.tilldawn.model.character.enemy.Enemy;
import com.tilldawn.view.GameView;

import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WeaponController weaponController;
    private EnemiesController enemiesController;
    private final Repository repo;
    private CollisionController collisionController;
    private boolean waitingForAbilityChoice = false;
    private boolean abilityChosen = false;
    private Stage abilityStage;
    private Window abilityWindow;
    private Skin skin;
    private ZoneController zoneController;
    private LightController lightController;



    public GameController(Repository repo) {
        this.repo = repo;
        this.weaponController = new WeaponController(repo.getCurrentUser().getPlayer().getWeapon());
        this.playerController = new PlayerController(repo.getCurrentUser().getPlayer(), weaponController, this);
        this.zoneController = new ZoneController();
        this.enemiesController = new EnemiesController(repo, zoneController);
        this.collisionController = new CollisionController(this);
        this.lightController = new LightController(repo.getCurrentUser().getPlayer());
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void updateGame(float delta) {
        System.out.println("mm");
        if (waitingForAbilityChoice) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            abilityStage.act(Gdx.graphics.getDeltaTime());
            abilityStage.draw();
            //System.out.println("in controller");

            if (abilityChosen) {
                waitingForAbilityChoice = false;
                abilityStage.dispose();
                Gdx.input.setInputProcessor(view);
            }

            return;
        }
        playerController.update(delta, enemiesController.getEnemies());
        enemiesController.update(delta);
        collisionController.update();
        lightController.update();
        if (repo.getCurrentUser().getPlayer().hasAutoAim()) {
            Enemy closestEnemy = playerController.getClosestNonTreeEnemy(enemiesController.getEnemies());
            if (closestEnemy != null) {
                Main.getBatch().draw(AssetManager.getAssetManager().getCursorTexture(), closestEnemy.getX() + 10, closestEnemy.getY() + 10);
            }
        }
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

    public EnemiesController getEnemiesController() {
        return enemiesController;
    }


    public void showAbilityWindow() {
        waitingForAbilityChoice = true;
        abilityChosen = false;

        abilityStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(abilityStage);

        List<String> abilityNames = Random.getRandomPowerUps();

        if (skin == null)
            skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        abilityWindow = new Window("Choose Ability", skin);
        abilityWindow.setSize(500, 700);
        abilityWindow.setPosition(
            (Gdx.graphics.getWidth() - abilityWindow.getWidth()) / 2,
            (Gdx.graphics.getHeight() - abilityWindow.getHeight()) / 2
        );

        TextButton btn1 = new TextButton(abilityNames.get(0), skin);
        btn1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                activateAbility(btn1.getText().toString());
                abilityChosen = true;
                repo.getCurrentUser().getPlayer().setXp(0);
                repo.getCurrentUser().getPlayer().increaseLevelNumber();
                abilityWindow.remove();
                abilityStage.unfocusAll();
                waitingForAbilityChoice = false;
                abilityStage.dispose();
                Gdx.input.setInputProcessor(view);
            }
        });

        TextButton btn2 = new TextButton(abilityNames.get(1), skin);
        btn2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                activateAbility(btn2.getText().toString());
                abilityChosen = true;
                repo.getCurrentUser().getPlayer().setXp(0);
                repo.getCurrentUser().getPlayer().increaseLevelNumber();
                abilityWindow.remove();
                abilityStage.unfocusAll();
                waitingForAbilityChoice = false;
                abilityStage.dispose();
                Gdx.input.setInputProcessor(view);
            }
        });

        TextButton btn3 = new TextButton(abilityNames.get(2), skin);
        btn3.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                activateAbility(btn3.getText().toString());
                abilityChosen = true;
                repo.getCurrentUser().getPlayer().setXp(0);
                repo.getCurrentUser().getPlayer().increaseLevelNumber();
                abilityWindow.remove();
                abilityStage.unfocusAll();
                waitingForAbilityChoice = false;
                abilityStage.dispose();
                Gdx.input.setInputProcessor(view);
            }
        });

        abilityWindow.add(btn1).pad(10);
        abilityWindow.row();
        abilityWindow.add(btn2).pad(10);
        abilityWindow.row();
        abilityWindow.add(btn3).pad(10);
        abilityStage.addActor(abilityWindow);
    }

    private void activateAbility(String abilityName) {
        switch (abilityName.toLowerCase().trim()) {
            case "vitality":
                repo.getCurrentUser().getPlayer().increaseMaxHP();
                break;
            case "damager":
                repo.getCurrentUser().getPlayer().setDamager(true);
                break;
            case "procrease":
                getPlayerController().getController().getWeapon().increaseProjectile();
                break;
            case "amocrease":
                getPlayerController().getController().getWeapon().setAmmo();
                break;
            case "speedy":
                repo.getCurrentUser().getPlayer().setSpeedy(true);
                break;
        }
    }

    public void setWaitingForAbilityChoice(boolean waitingForAbilityChoice) {
        this.waitingForAbilityChoice = waitingForAbilityChoice;
    }
//
//                "VITALITY",
//                    "DAMAGER",
//                    "PROCREASE",
//                    "AMOCREASE",
//                    "SPEEDY"

    public Stage getAbilityStage() {
        return abilityStage;
    }

    public boolean isWaitingForAbilityChoice() {
        return waitingForAbilityChoice;
    }

    public ZoneController getZoneController() {
        return zoneController;
    }

    public GameView getGameView() {
        return view;
    }
}
