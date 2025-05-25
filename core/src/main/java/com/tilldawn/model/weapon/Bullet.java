package com.tilldawn.model.weapon;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.model.character.enemy.BulletType;

public class Bullet {
    private BulletType bulletType;
    private Sprite sprite;
    private float speed;
    private Vector2 direction;

    public Bullet(float x, float y, BulletType bulletType) {
        this.bulletType = bulletType;
        sprite = new Sprite(bulletType.getTexture());
        sprite.setPosition(x, y);
        direction = new Vector2(0, 0);
        speed = 0f;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction.nor();
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
