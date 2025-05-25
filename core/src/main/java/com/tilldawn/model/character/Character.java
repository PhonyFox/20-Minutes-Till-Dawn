package com.tilldawn.model.character;

import com.tilldawn.model.CollisionRect;
import com.tilldawn.model.Position;

public class Character {
    protected Position position;
    protected boolean facingRight;
    protected float speed;
    protected int health;
    protected CollisionRect collisionRect;
    protected boolean isIdle;


    public CollisionRect getCollisionRect() {
        return collisionRect;
    }
}
