package com.tilldawn.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.model.character.enemy.Enemy;

public class DeadBody {
    private final Enemy enemy;
    private final float x;
    private final float y;
    private float duration = 0.4f;
    private float startTime = 0f;
    private final Animation<TextureRegion> deadBodyAnimation = AssetManager.getAssetManager().getDeathAnimation();

    public DeadBody(Enemy enemy, float x, float y) {
        this.enemy = enemy;
        this.x = x;
        this.y = y;
    }

    public void update(float delta) {
        duration -= delta;
        startTime += delta;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public boolean isDone() {
        return duration <= 0;
    }

    public TextureRegion getDeadBodyTexture() {
        return deadBodyAnimation.getKeyFrame(startTime, true);
    }

    public TextureRegion getEnemyTexture() {
        return enemy.getAnimation().getKeyFrame(startTime, true);
    }
}
