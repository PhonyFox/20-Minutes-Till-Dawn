package com.tilldawn.controller;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.model.GamaText;
import com.tilldawn.model.Repository;
import com.tilldawn.model.User;
import com.tilldawn.view.MainMenuView;

public class EndGameScreen extends ScreenAdapter {

    private final Stage stage;
    private final Skin skin;

    private final String username;
    private final int kills;
    private final int secondsSurvived;
    private final boolean isWinner;
    private final Repository repo;

    public EndGameScreen(User user, int kills, int secondsSurvived, boolean isWinner, Repository repo) {
        this.kills = kills;
        this.secondsSurvived = secondsSurvived;
        this.isWinner = isWinner;
        this.username = user.getUsername();
        this.repo = repo;

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        buildUI();
    }

    private void buildUI() {
        Table root = new Table();
        root.setFillParent(true);
        root.defaults().pad(10);
        stage.addActor(root);

        Label title = new Label(isWinner ? GamaText.STATUS_WIN.get() : GamaText.STATUS_DEAD.get(), skin, "title");
        title.setAlignment(Align.center);
        root.add(title).colspan(2).center().padBottom(30);
        root.row();

        root.add(new Label(GamaText.RESET_USERNAME.get(), skin));
        root.add(new Label(username, skin));
        root.row();

        root.add(new Label(GamaText.TIME_SURVIVED.get(), skin));
        root.add(new Label(secondsSurvived + " s", skin));
        root.row();

        root.add(new Label(GamaText.KILLS_LABEL.get(), skin));
        root.add(new Label(String.valueOf(kills), skin));
        root.row();


        int score = kills * secondsSurvived;
        root.add(new Label(GamaText.SCORE_LABEL.get(), skin));
        root.add(new Label(String.valueOf(score), skin));
        root.row().padTop(30);

        TextButton backBtn = new TextButton(GamaText.BACK_MAIN_MENU.get(), skin);
        backBtn.addListener(evt -> {
            if (backBtn.isPressed()) {
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(repo)));
            }
            return true;
        });
        root.add(backBtn).colspan(2).center();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
