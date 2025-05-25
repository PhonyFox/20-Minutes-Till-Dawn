package com.tilldawn.model.enums;

import com.badlogic.gdx.graphics.Texture;

public enum HeroType {
    SHANA(4, 4, null),
    DIAMOND(7, 1, null),
    SCARLET(3, 5, null),
    LILITH(5, 3, null),
    DASHER(2, 10, null),;

    private final int HP;
    private final int speed;
    private final Texture heroTexture;

    private HeroType(int HP, int speed, Texture heroTexture) {
        this.HP = HP;
        this.speed = speed;
        this.heroTexture = heroTexture;
    }

    public int getHP() {
        return HP;
    }

    public int getSpeed() {
        return speed;
    }

    public Texture getHeroTexture() {
        return heroTexture;
    }

    public String getHeroName() {
        return this.name().toLowerCase();
    }
}
