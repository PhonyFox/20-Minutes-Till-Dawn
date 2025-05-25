package com.tilldawn.model.character.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.model.enums.HeroType;

public class Hero {
    private Sprite heroSprite;
    private HeroType heroType;

    public Hero(HeroType heroType) {
        this.heroType = heroType;
    }

    public HeroType getHeroType() {
        return heroType;
    }


}

