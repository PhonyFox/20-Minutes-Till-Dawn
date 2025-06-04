package com.tilldawn.model.weapon.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.model.character.enemy.BulletType;

public class Revolver extends Weapon {


    private final BulletType bulletType = BulletType.MAGNUM;
    private final TextureRegion texture = new TextureRegion(new Texture("revolver/still.png"));
    private final Sprite sprite = null;
    private int magazineCapacity;
    private int damage;

    public Revolver(int magazineCapacity, long shootingCooldown, long reloadingTime) {
        super(magazineCapacity, shootingCooldown, reloadingTime);
        ammo = magazineCapacity;
        projectile = 1;
    }

    @Override
    public TextureRegion getWeaponTexture() {
        return texture;
    }

    @Override
    public BulletType getBulletType() {
        return BulletType.MAGNUM;
    }

    public void setProjectile(int projectile) {
        this.projectile = projectile;
    }

}
