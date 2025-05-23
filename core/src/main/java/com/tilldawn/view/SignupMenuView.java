package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.LoginMenuController;
import com.tilldawn.controller.SignupMenuController;
import com.tilldawn.model.User;

public class SignupMenuView extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private final SignupMenuController controller;

    public SignupMenuView(SignupMenuController controller) {
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

        TextField usernameField = new TextField("", skin);
        TextField passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        SelectBox<String> questionBox = new SelectBox<>(skin);
        questionBox.setItems(
            "What is your pet's name?",
            "What is your favorite color?",
            "What city were you born in?"
        );
        TextField answerField = new TextField("", skin);

        Label messageLabel = new Label("", skin);

        TextButton registerButton = new TextButton("Register", skin);
        TextButton guestButton = new TextButton("Guest", skin);

        table.add("Username:").left(); table.add(usernameField).width(780).row();
        table.add("Password:").left(); table.add(passwordField).width(780).row();
        table.add("Security Q:").left(); table.add(questionBox).width(780).row();
        table.add("Answer:").left(); table.add(answerField).width(780).row();
        table.add(registerButton).colspan(2).padTop(10).row();
        table.add(guestButton).colspan(2).padTop(10).row();
        table.add(messageLabel).colspan(2).padTop(10).row();

        registerButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                String message = controller.register(
                    usernameField.getText(),
                    passwordField.getText(),
                    questionBox.getSelected(),
                    answerField.getText()
                );

                if (message.toLowerCase().contains("success")) {
                    messageLabel.setText("Registered Successfully");
                    Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(controller.getRepo())));
                } else {
                    messageLabel.setText(message);
                }

            }
        });

        guestButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                User guest = controller.guestLogin();
                //@ move to game screen with guest
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
