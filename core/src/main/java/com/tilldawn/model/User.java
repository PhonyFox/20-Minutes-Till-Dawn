package com.tilldawn.model;

import com.badlogic.gdx.graphics.Texture;
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
}
