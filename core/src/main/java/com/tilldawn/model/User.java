package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tilldawn.model.character.player.Player;
import com.tilldawn.model.enums.SecurityQuestionType;

public class User {
    private final static int AVATAR_SIZE = 20;
    private String password;
    private String username;
    private boolean isGuest;
    private SecurityQuestionType securityQuestionType;
    private String securityAnswer;
    private Texture playerTexture;
    private GameSave gameSave;
    private UserState userState = new UserState();
    private UserSetting userSetting;
    private Player player;
    private String avatarPath;
    private boolean hasSavedGame = false;
    private int duration;


    public String getUsername() {
        return username;
    }

    public User(String username, String password, SecurityQuestionType securityQuestionType,
                String securityAnswer, String avatar) {
        this.username = username;
        this.password = password;
        this.securityQuestionType = securityQuestionType;
        this.securityAnswer = securityAnswer;
        this.avatarPath = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public int getScore() {
        return userState.getScore();
    }

    public Image getAvatarImage() {
        Texture texture;
        try {
            texture = new Texture(Gdx.files.internal(avatarPath));
        } catch (Exception e) {
            texture = new Texture(Gdx.files.internal("avatars/default.png"));
        }

        TextureRegionDrawable drawable = new TextureRegionDrawable(texture);
        Image image = new Image(drawable);
        image.setSize(AVATAR_SIZE, AVATAR_SIZE);
        return image;
    }

    public boolean hasSavedGame() {
        return hasSavedGame;
    }

    public UserSetting getUserSetting() {
        return userSetting;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Player getPlayer() {
        return player;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getKills() {
        return userState.getKills();
    }

    public int getSurvivalTime() {
        return userState.getSurvivalTime();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
