package com.tilldawn.model.character.enemy;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum BulletType {
    MM9(null),
    SLUG(null),
    MAGNUM(null);

    private BulletType(TextureRegion textureRegion) {
        this.texture = textureRegion;
    }

    private final TextureRegion texture;

    public TextureRegion getTexture() {
        return texture;
    }
}
