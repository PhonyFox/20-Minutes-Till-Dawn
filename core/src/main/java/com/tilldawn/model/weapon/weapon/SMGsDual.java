package com.tilldawn.model.weapon.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.model.User;
import com.tilldawn.model.character.enemy.BulletType;

public class SMGsDual extends Weapon {
    private final BulletType bulletType = BulletType.MM9;
    private final TextureRegion texture = new TextureRegion(new Texture("smg/still.png"));
    private final Sprite sprite = null;
    //@
    private int magazineCapacity;
    private int damage;

    public SMGsDual(int magazineCapacity, long shootingCooldown, long reloadingTime, User user) {
        super(magazineCapacity, shootingCooldown, reloadingTime, user);
        ammo = magazineCapacity;
        projectile = 1;
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
}
