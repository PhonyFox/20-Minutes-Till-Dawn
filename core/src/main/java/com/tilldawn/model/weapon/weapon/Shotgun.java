package com.tilldawn.model.weapon.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.model.character.enemy.BulletType;

public class Shotgun extends Weapon {
    private final BulletType bulletType = BulletType.SLUG;

    private final TextureRegion texture = new TextureRegion(new Texture("shotgun/still.png"));
    private final Sprite sprite = null;
    //@
    private int magazineCapacity;
    private int damage;
    private int projectile;

    public Shotgun(int magazineCapacity, float shootingCooldown, float reloadingTime) {
        super(magazineCapacity, shootingCooldown, reloadingTime);
    }


    @Override
    public TextureRegion getWeaponTexture() {
        return texture;
    }


    @Override
    public BulletType getBulletType() {
        return BulletType.SLUG;
    }
}
