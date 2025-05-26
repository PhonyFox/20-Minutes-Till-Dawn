package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.GameController;
import com.tilldawn.controller.MainMenuController;
import com.tilldawn.controller.PreGameMenuController;


public class PreGameMenuView extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private final PreGameMenuController controller;
    private Label statusLabel;

    public PreGameMenuView(PreGameMenuController controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        Table table = new Table(skin);
        stage.addActor(table);
        table.setFillParent(true);

        Label heroLabel = new Label("Select Hero:", skin);
        SelectBox<String> heroSelect = new SelectBox<>(skin);
        heroSelect.setItems("Shana", "Diamond", "Scarlet", "Lilith", "Dasher");
        heroSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.selectHero(heroSelect.getSelected());
            }
        });

        Label weaponLabel = new Label("Select Weapon:", skin);
        SelectBox<String> weaponSelect = new SelectBox<>(skin);
        weaponSelect.setItems("Shotgun", "SMGs Dual", "Revolver");
        weaponSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.selectWeapon(weaponSelect.getSelected());
            }
        });

        Label durationLabel = new Label("Select Duration:", skin);
        SelectBox<String> durationSelect = new SelectBox<>(skin);
        durationSelect.setItems("2", "5", "10", "20");
        durationSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.selectDuration(Integer.parseInt(durationSelect.getSelected()));
            }
        });

        TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (controller.isReadyToStart()) {
                    statusLabel.setText("Starting game with hero: " + heroSelect.getSelected()
                    + ", weapon: " + weaponSelect.getSelected()
                    + ", duration: " + durationSelect.getSelected()
                    );
                    controller.startGame();
                } else {
                    statusLabel.setText("Please select all options");
                }
            }
        });

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.goToMainMenu();
            }
        });

        statusLabel = new Label("", skin);

        table.add(heroLabel).left(); table.add(heroSelect).width(200).row();
        table.add(weaponLabel).left(); table.add(weaponSelect).width(200).row();
        table.add(durationLabel).left(); table.add(durationSelect).width(200).row();
        table.add(startButton).colspan(2).padTop(10).row();
        table.add(statusLabel).colspan(2).padTop(10).row();
        table.add(backButton).colspan(2).padTop(10).row();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.08f, 0.08f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
