package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.controller.PauseMenuController;

public class PauseMenuView extends ScreenAdapter {
    private final PauseMenuController controller;
    private Stage stage;
    private Skin skin;
    private final GameView gameView;

    public PauseMenuView(PauseMenuController controller, GameView gameView) {
        this.controller = controller;
        this.gameView = gameView;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        Table table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        TextButton resumeButton = new TextButton("Resume", skin);
        TextButton cheatButton = new TextButton("Cheat Codes", skin);
        TextButton abilitiesButton = new TextButton("Abilities", skin);
        TextButton grayscaleButton = new TextButton("Toggle Grayscale", skin);
        TextButton giveUpButton = new TextButton("Give Up", skin);
        TextButton saveExitButton = new TextButton("Save & Exit", skin);

        table.add(resumeButton).pad(10).row();
        table.add(cheatButton).pad(10).row();
        table.add(abilitiesButton).pad(10).row();
        table.add(grayscaleButton).pad(10).row();
        table.add(giveUpButton).pad(10).row();
        table.add(saveExitButton).pad(10).row();

        resumeButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.resumeGame();
            }
        });

        cheatButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //@
            }
        });

        abilitiesButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //@
            }
        });

        grayscaleButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.toggleGrayscale();
            }
        });

        giveUpButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //@
            }
        });

        saveExitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //@
            }
        });
    }

    private void showDialog(String title, String message) {
        Dialog dialog = new Dialog(message, skin);
        dialog.text(message);
        dialog.button("OK");
        dialog.show(stage);
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

    public GameView getGameView() {
        return gameView;
    }
}
