package com.tilldawn.model.character.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum BulletType {
    MM9(new TextureRegion(new Texture("bullet.png"))),
    SLUG(new TextureRegion(new Texture("bullet.png"))),
    MAGNUM(new TextureRegion(new Texture("bullet.png")));

    private BulletType(TextureRegion textureRegion) {
        this.texture = textureRegion;
    }

    private final TextureRegion texture;

    public TextureRegion getTexture() {
        return texture;
    }
}
