package com.tilldawn.model.character.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.model.AssetManager;
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
    protected float hp;
    protected final Animation<TextureRegion> deathAnimation;

    public Enemy() {
        this.x = this.y = 0.0f;
        this.position = new Vector2(x, y);
        this.deathAnimation = AssetManager.getAssetManager().getDeathAnimation();
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

    public int spawnX() {
        int randomX;
        if (System.currentTimeMillis() % 2 == 1) {
            randomX = Random.rand(-100, -10);
        } else randomX = Random.rand(3700, 3800);
        this.x = randomX;
        return randomX;
    }

    public int spawnY() {
        int randomY;
        if (System.currentTimeMillis() % 2 == 1) {
            randomY = Random.rand(-100, -10);
        } else randomY = Random.rand(2700, 2800);
        this.y = randomY;
        return randomY;
    }

    public void spawnFromWall() {
        int side = Random.rand(0, 4);

        switch (side) {
            case 0:
                this.x = Random.rand(-100, -10);
                this.y = Random.rand(0, 2700);
                break;
            case 1:
                this.x = Random.rand(3700, 3800);
                this.y = Random.rand(0, 2700);
                break;
            case 2:
                this.x = Random.rand(0, 3700);
                this.y = Random.rand(2700, 2800);
                break;
            case 3:
                this.x = Random.rand(0, 3700);
                this.y = Random.rand(-100, -10);
                break;
        }
    }

    public TextureRegion getCurrentDeathFrame() {
        return deathAnimation.getKeyFrame(stateTime, true);
    }


    public void updateTime(float delta) { stateTime += delta; }

    public abstract void handleMovement(float delta, Player player);

    public float getHP(float delta) {
        return hp;
    }

    public void decreaseHP(float delta) {
        hp -= delta;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public abstract Animation<TextureRegion> getAnimation();
}
