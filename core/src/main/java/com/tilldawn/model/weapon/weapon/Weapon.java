package com.tilldawn.model.weapon.weapon;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.tilldawn.model.character.enemy.BulletType;

public abstract class Weapon {
    protected int ammo = 100;
    protected float x;
    protected float y;
    protected float aimAngle;
    protected TextureRegion weaponTexture;
    protected long reloadingTime;
    protected long shootingCooldown;
    protected long lastShootTime = System.currentTimeMillis();
    protected int magazineCapacity;
    protected BulletType bulletType;
    protected boolean isWhileReloading = false;
    protected long ReloadingStartTime;
    protected float damage;
    protected int projectile;

    public Weapon(int magazineCapacity, long shootingCooldown, long reloadingTime) {
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
        //System.out.println(ammo);
        if (ammo <= 0 && !isWhileReloading) {
            isWhileReloading = true;
            setReloadingStartTime(System.currentTimeMillis());
        }
        if (isWhileReloading()) return false;
        return ammo > 0 && (now - lastShootTime) >= shootingCooldown;
    }

    public abstract BulletType getBulletType();

    public void setLastShootTime() {
        lastShootTime = System.currentTimeMillis();

    }

    public long getReloadingStartTime() {
        return ReloadingStartTime;
    }

    public void setReloadingStartTime(long reloadingStartTime) {
        ReloadingStartTime = reloadingStartTime;
    }

    public long getReloadingTime() {
        return reloadingTime;
    }

    public void setReloadingTime(long reloadingTime) {
        this.reloadingTime = reloadingTime;
    }

    public int getMagazineCapacity() {
        return magazineCapacity;
    }

    public boolean isWhileReloading() {
        return isWhileReloading;
    }

    public void setWhileReloading(boolean isWhileReloading) {
        this.isWhileReloading = isWhileReloading;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getProjectile() {
        return projectile;
    }
}
