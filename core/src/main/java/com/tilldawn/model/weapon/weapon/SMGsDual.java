package com.tilldawn.model.weapon.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.model.character.enemy.BulletType;

public class SMGsDual extends Weapon {
    private final BulletType bulletType = BulletType.MM9;
    private final TextureRegion texture = new TextureRegion(new Texture("smg/still.png"));
    private final Sprite sprite = null;
    //@
    private int magazineCapacity;
    private int damage;
    private int projectile;

    public SMGsDual(int magazineCapacity, long shootingCooldown, long reloadingTime) {
        super(magazineCapacity, shootingCooldown, reloadingTime);
        ammo = magazineCapacity;
    }

    @Override
    public TextureRegion getWeaponTexture() {
        return texture;
    }

    @Override
    public BulletType getBulletType() {
        return BulletType.MM9;
    }

    public void setProjectile(int projectile) {
        this.projectile = projectile;
    }

    private int getProjectile() {
        return projectile;
    }
}
