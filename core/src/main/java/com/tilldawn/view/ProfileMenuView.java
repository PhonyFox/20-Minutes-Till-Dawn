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
import com.tilldawn.controller.ProfileMenuController;
import com.tilldawn.controller.SignupMenuController;
import com.tilldawn.model.User;

public class ProfileMenuView extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private final ProfileMenuController controller;
    private Label messageLabel;

    public ProfileMenuView(ProfileMenuController controller) {
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

        User user = controller.getCurrentUser();

        Label usernameLabel = new Label("Username", skin);
        TextField usernameField = new TextField(user.getUsername(), skin);
        TextButton changeUsernameButton = new TextButton("Change", skin);
        changeUsernameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if (!controller.changeUsername(usernameField.getText())) {
                    messageLabel.setText("Username already taken!");
                } else {
                    messageLabel.setText("Username updated!");
                }
            }
        });

        Label passwordLabel = new Label("New Password", skin);
        TextField passwordField = new TextField("", skin);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        TextButton changePasswordButton = new TextButton("Change", skin);
        changePasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if (!controller.changePassword(passwordField.getText())) {
                    messageLabel.setText("Password too weak!");
                } else {
                    messageLabel.setText("Password changed!");
                }
            }
        });

        TextButton deleteButton = new TextButton("Delete Account", skin);
        deleteButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                controller.deleteAccount();
                messageLabel.setText("Account deleted.");
                Main.getMain().setScreen(new SignupMenuView(new SignupMenuController(controller.getRepo())));
            }
        });

        Label avatarLabel = new Label("Avatar", skin);
        SelectBox<String> avatarSelect = new SelectBox<>(skin);
        avatarSelect.setItems("avatars/a1.png", "avatars/a2.png", "avatars/a3.png");
        TextButton chooseAvatarButton = new TextButton("Choose Avatar", skin);
        chooseAvatarButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                controller.changeAvatar(avatarSelect.getSelected());
                messageLabel.setText("Avatar changed!");
            }
        });

        //@

        messageLabel = new Label("", skin);

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                //@
            }
        });

        table.add(usernameLabel).left(); table.add(usernameField).width(300); table.add(changeUsernameButton).row();
        table.add(passwordLabel).left(); table.add(passwordField).width(300); table.add(changePasswordButton).row();
        table.add(deleteButton).colspan(3).padTop(10).row();
        table.add(avatarLabel).left(); table.add(avatarSelect); table.add(chooseAvatarButton).row();
        table.add(messageLabel).colspan(3).padTop(10).row();
        table.add(backButton).colspan(3).padTop(15).center().row();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.07f, 0.07f, 0.1f, 1);
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
