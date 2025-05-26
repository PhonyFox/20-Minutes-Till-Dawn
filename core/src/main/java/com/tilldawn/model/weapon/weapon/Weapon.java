package com.tilldawn.model.weapon.weapon;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.tilldawn.model.character.enemy.BulletType;

public abstract class Weapon {
    protected int ammo = 100;
    protected float x;
    protected float y;
    protected float aimAngle;
    protected TextureRegion weaponTexture;
    protected float reloadingTime;
    protected float shootingCooldown;
    protected long lastShootTime = System.currentTimeMillis();
    protected int magazineCapacity;
    protected BulletType bulletType;

    public Weapon(int magazineCapacity, float shootingCooldown, float reloadingTime) {
        this.x = 0;
        this.y = 0;
        this.aimAngle = 0;
        this.magazineCapacity = magazineCapacity;
        this.reloadingTime = reloadingTime;
        this.shootingCooldown = shootingCooldown;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getAimAngle() {
        return aimAngle;
    }

    public void setAimAngle(float aimAngle) {
        this.aimAngle = aimAngle;
    }

    public TextureRegion getWeaponTexture() {
        return weaponTexture;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public boolean canShoot() {
        long now = System.currentTimeMillis();
        return ammo > 0 && (now - lastShootTime) >= shootingCooldown * 1000;
    }

    public abstract BulletType getBulletType();

    public void setLastShootTime() {
        lastShootTime = System.currentTimeMillis();

    }
}
