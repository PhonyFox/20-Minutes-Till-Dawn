package com.tilldawn.model;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Character {
    private final User user;
    private Sprite PlayerSprite;
    private float aimAngle;
    private static final float MAX_HEALTH = 100;

    public Player(User user) {
        this.user = user;
    }
}
