package com.tilldawn.model.character.enemy;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.model.Random;
import com.tilldawn.model.character.Character;
import com.tilldawn.model.character.player.Player;

public abstract class Enemy extends Character {
    protected float startingSpawnTime;
    protected float spawnTimePeriod;
    protected float x, y;
    protected boolean facingRight = false;
    protected float stateTime = 0f;
    protected Vector2 position;

    public Enemy() {
        this.x = this.y = 0.0f;
        this.position = new Vector2(x, y);
    }

    public abstract TextureRegion getCurrentFrame();

    public boolean isFacingRight() { return facingRight; }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public static int spawnX() {
        int x;
        if (System.currentTimeMillis() % 2 == 1) {
            x = Random.rand(-100, -10);
        } else x = Random.rand(3700, 3800);
        return x;
    }

    public static int spawnY() {
        int y;
        if (System.currentTimeMillis() % 2 == 1) {
            y = Random.rand(-100, -10);
        } else y = Random.rand(2700, 2800);
        return y;
    }

    public void updateTime(float delta) { stateTime += delta; }

    public abstract void handleMovement(float delta, Player player);
}
