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

    public Shotgun(int magazineCapacity, long shootingCooldown, long reloadingTime) {
        super(magazineCapacity, shootingCooldown, reloadingTime);
        this.projectile = 4;
        ammo = magazineCapacity;
    }


    @Override
    public TextureRegion getWeaponTexture() {
        return texture;
    }


    @Override
    public BulletType getBulletType() {
        return BulletType.SLUG;
    }

    public void setProjectile(int projectile) {
        this.projectile = projectile;
    }
}
