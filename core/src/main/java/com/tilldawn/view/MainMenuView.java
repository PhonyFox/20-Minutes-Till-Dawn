package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.controller.MainMenuController;

public class MainMenuView extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private final MainMenuController controller;

    public MainMenuView(MainMenuController controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        Table table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        Label usernameLabel = new Label("Username:" + controller.getRepo().getCurrentUser().getUsername(), skin);
        Label scoreLabel = new Label("Score:" + controller.getRepo().getCurrentUser().getScore(), skin);
        Image avatarImage = controller.getRepo().getCurrentUser().getAvatarImage();

        TextButton continueButton = new TextButton("Continue", skin);
        TextButton settingsButton = new TextButton("Settings", skin);
        TextButton profileButton = new TextButton("Profile", skin);
        TextButton gamePreButton = new TextButton("Game Pre", skin);
        TextButton scoreboardButton = new TextButton("Score", skin);
        TextButton hintsButton = new TextButton("Hints", skin);
        TextButton logoutButton = new TextButton("Logout", skin);

        table.add(avatarImage).size(128).colspan(2).padBottom(10).row();
        table.add(usernameLabel).colspan(2).row();
        table.add(scoreLabel).colspan(2).padBottom(20).row();

        table.add(continueButton).colspan(2).width(600).pad(5).row();
        table.add(settingsButton).colspan(2).width(600).pad(5).row();
        table.add(profileButton).colspan(2).width(600).pad(5).row();
        table.add(gamePreButton).width(600).pad(5).row();
        table.add(scoreboardButton).width(600).pad(5).row();
        table.add(hintsButton).width(600).pad(5).row();
        table.add(logoutButton).width(600).padTop(20).row();

        continueButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                controller.continueSavedGame();
            }
        });

        settingsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                controller.goToSetting();
            }
        });

        profileButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                controller.goToProfile();
            }
        });

        gamePreButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                controller.goToGameMenu();
            }
        });

        scoreboardButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                controller.goToScoreboard();
            }
        });

        hintsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                controller.goToTalentMenu();
            }
        });

        logoutButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                controller.logout();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
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
