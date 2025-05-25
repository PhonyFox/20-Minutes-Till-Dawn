package com.tilldawn.model.weapon.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.model.character.enemy.BulletType;

public class SMGsDual extends Weapon {
    private final BulletType bulletType = BulletType.MM9;
    private final TextureRegion texture = null;
    private final Sprite sprite = null;
    //@
    private int magazineCapacity;
    private int damage;
    private int projectile;

    public SMGsDual(int magazineCapacity, float shootingCooldown, float reloadingTime) {
        super(magazineCapacity, shootingCooldown, reloadingTime);
    }

    @Override
    public TextureRegion getWeaponTexture() {
        return texture;
    }

    @Override
    public BulletType getBulletType() {
        return null;
    }
}
