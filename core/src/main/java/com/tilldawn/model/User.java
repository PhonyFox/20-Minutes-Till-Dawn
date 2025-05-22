package com.tilldawn.model;

import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.model.character.player.Player;
import com.tilldawn.model.enums.SecurityQuestionType;

public class User {
    private String password;
    private String username;
    private boolean isGuest;
    private SecurityQuestionType securityQuestionType;
    private String securityQuestionAnswer;
    private Texture playerTexture;
    private GameSave gameSave;
    private UserState userState;
    private UserSetting userSetting;
    private Player player;
    private final String avatarPath;

    public String getUsername() {
        return username;
    }

    public User(String username, String password, SecurityQuestionType securityQuestionType,
                String securityQuestionAnswer, String avatar) {
        this.username = username;
        this.password = password;
        this.securityQuestionType = securityQuestionType;
        this.securityQuestionAnswer = securityQuestionAnswer;
        this.avatarPath = avatar;
    }
}
