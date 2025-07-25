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
import com.tilldawn.controller.MainMenuController;
import com.tilldawn.model.GamaText;
import com.tilldawn.model.Repository;

public class LoginMenuView extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private final LoginMenuController controller;

    public LoginMenuView(LoginMenuController controller) {
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

        Label messageLabel = new Label("", skin);

        TextButton loginButton = new TextButton(GamaText.BUTTON_LOGIN.get(), skin);
        TextButton resetButton = new TextButton(GamaText.LINK_FORGOT_PASSWORD.get(), skin);

        table.add(GamaText.RESET_USERNAME.get()).left(); table.add(usernameField).width(780).row();
        table.add(GamaText.RESET_PASSWORD.get()).left(); table.add(passwordField).width(780).row();
        table.add(loginButton).colspan(2).padTop(10).row();
        table.add(resetButton).colspan(2).padTop(10).row();
        table.add(messageLabel).colspan(2).padTop(10).row();

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String result = controller.login(usernameField.getText(), passwordField.getText());

                if (result.contains("successfully")) {
                    messageLabel.setText("Login Successful");
                    controller.getRepo().setCurrentUser(controller.getUser(usernameField.getText()));
                    Main.getMain().setScreen(new MainMenuView(new MainMenuController(controller.getRepo())));
                } else {
                    messageLabel.setText(result);
                }
            }
        });

        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showResetDialog();
            }
        });
    }

    private void showResetDialog() {
        Dialog dialog = new Dialog("Reset Password", skin);
        dialog.setResizable(true);

        TextField username = new TextField("", skin);
        TextField answer = new TextField("", skin);
        TextField newPassword = new TextField("", skin);
        newPassword.setPasswordMode(true);
        newPassword.setPasswordCharacter('*');
        Label resultLabel = new Label("", skin);


        SelectBox<String> questionBox = new SelectBox<>(skin);
        questionBox.setItems(
            "What is your pet's name?",
            "What is your favorite color?",
            "What city were you born in?"
        );

        TextButton resetButton = new TextButton("Reset", skin);

        Table dialogTable = new Table(skin);
        dialogTable.pad(10);
        dialogTable.defaults().pad(5);

        dialogTable.add(GamaText.RESET_USERNAME.get()).left(); dialogTable.add(username).row();
        dialogTable.add(GamaText.RESET_SECURITY_Q.get()).left(); dialogTable.add(questionBox).row();
        dialogTable.add(GamaText.RESET_ANSWER.get()).left(); dialogTable.add(answer).row();
        dialogTable.add(GamaText.INPUT_NEW_PASSWORD.get()).left(); dialogTable.add(newPassword).row();
        dialogTable.add(resetButton).colspan(2).padTop(10).row();
        dialogTable.add(resultLabel).colspan(2).padTop(10).row();

        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String message = controller.resetPassword(username.getText(),
                    answer.getText(),
                    answer.getText()
                );

                resultLabel.setText(message);
                if (message.toLowerCase().contains("success")) {
                    dialog.hide();
                }
            }
        });

        dialog.getContentTable().add(dialogTable).pad(10);
        dialog.pack();
        dialog.show(stage);
    }



        @Override
        public void render(float delta) {
            Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act();
            stage.draw();
        }

        @Override
        public void dispose() {
            stage.dispose();
            skin.dispose();
        }
}
