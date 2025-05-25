package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.model.weapon.Bullet;
import com.tilldawn.model.weapon.weapon.Weapon;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WeaponController {
    private final Weapon weapon;
    private List<Bullet> bullets = new LinkedList<>();

    private float bulletSpeed = 500;
    private float lastShootTime = 0f;
    private float shootCooldown = 0.3f;

    public WeaponController(Weapon weapon, float x, float y) {
        this.weapon = weapon;
    }

    public void update(float delta, float x, float y, float angle) {
        weapon.setAimAngle(angle);
        weapon.setPosition(x, y);
        updateWeaponPositionAndRotation();
        updateBullets(delta);

    }

    private void updateWeaponPositionAndRotation() {
        SpriteBatch batch = Main.getBatch();
        batch.begin();

        TextureRegion weaponRegion = weapon.getWeaponTexture();

        float x = weapon.getX();
        float y = weapon.getY();

        float originX = weaponRegion.getRegionWidth() / 2f;
        float originY = weaponRegion.getRegionHeight() / 2f;

        float rotationDegrees = weapon.getAimAngle() * MathUtils.radiansToDegrees;

        batch.draw(
            weaponRegion,
            x, y,
            originX, originY,
            weaponRegion.getRegionWidth(),
            weaponRegion.getRegionHeight(),
            1f, 1f,
            rotationDegrees
        );

        batch.end();
    }

    public void handleWeaponRotation(int x, int y) {
        float weaponCenterX = weapon.getX() + weapon.getWeaponTexture().getRegionWidth() / 2f;
        float weaponCenterY = weapon.getY() + weapon.getWeaponTexture().getRegionHeight() / 2f;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);
        weapon.setAimAngle(angle);
    }

    public void handleWeaponShoot(int x, int y) {
        if (!weapon.canShoot(Gdx.graphics.getDeltaTime())) {
            return;
        }

        float currentTime = Gdx.graphics.getDeltaTime() + lastShootTime;
        if (currentTime < lastShootTime + shootCooldown) {
            return;
        }

        lastShootTime = currentTime;

        float startX = weapon.getX() + weapon.getWeaponTexture().getRegionWidth() / 2f;
        float startY = weapon.getY() + weapon.getWeaponTexture().getRegionHeight() / 2f;

        Vector2 direction = new Vector2(x - startX, y - startY).nor();

        Bullet bullet = new Bullet(startX, startY, weapon.getBulletType());
        bullet.setDirection(direction);
        bullet.setSpeed(bulletSpeed);

        bullets.add(bullet);

        weapon.setAmmo(weapon.getAmmo() - 1);
    }

    private void updateBullets(float delta) {
        SpriteBatch batch = Main.getBatch();
        batch.begin();

        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            Vector2 pos = new Vector2(bullet.getSprite().getX(), bullet.getSprite().getY());
            Vector2 velocity = new Vector2(bullet.getDirection()).scl(bullet.getSpeed() * delta);
            pos.add(velocity);

            bullet.getSprite().setPosition(pos.x, pos.y);
            bullet.getSprite().draw(batch);

            if (pos.x < 0 || pos.x > Gdx.graphics.getWidth() || pos.y < 0 || pos.y > Gdx.graphics.getHeight()) {
                iterator.remove();
            }
        }
        batch.end();
    }
}
