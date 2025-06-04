package com.tilldawn.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Seed {
    private final float x, y;
    private final TextureRegion region = new TextureRegion(new Texture("seed.png"));

    public Seed(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
