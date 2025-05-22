package com.tilldawn.model.character.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.model.User;
import com.tilldawn.model.character.Character;

public class Player extends Character {
    private final User user;
    private Hero hero;
    private float aimAngle;
    private int currentLevelNumber;
    private float currentLevelProgress;
    private int requiredExpToFinishLevel;

    public Player(User user) {
        this.user = user;
    }
}
