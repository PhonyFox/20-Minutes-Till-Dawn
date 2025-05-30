package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.model.character.enemy.Enemy;
import com.tilldawn.model.character.enemy.Tree;
import com.tilldawn.model.character.player.Player;
import com.tilldawn.model.weapon.Bullet;
import com.tilldawn.model.weapon.weapon.Weapon;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WeaponController {
    private final Weapon weapon;
    private List<Bullet> bullets = new LinkedList<>();

    private float bulletSpeed = 350;
    private float shootCooldown = 0.3f;

    public WeaponController(Weapon weapon) {
        this.weapon = weapon;
    }

    public void update(float delta, Player player) {
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        float dx = mouseX - (weapon.getX() + player.getCurrentFrame().getRegionWidth()*2f);
        float dy = mouseY - (player.getY() + player.getCurrentFrame().getRegionHeight()*2f);

        float angle = (float)Math.atan2(dy, dx);
        //weapon.setAimAngle(angle);
        weapon.setPosition(player.getX() + player.getCurrentFrame().getRegionWidth()/2f, player.getY() + player.getCurrentFrame().getRegionHeight()/2f);
        updateWeaponPositionAndRotation(player);
        updateBullets(delta);

    }

    private void updateWeaponPositionAndRotation(Player player) {
        SpriteBatch batch = Main.getBatch();
        TextureRegion weaponRegion = weapon.getWeaponTexture();

        float playerCenterX = player.getX() + player.getCurrentFrame().getRegionWidth() / 2f;
        float playerCenterY = player.getY() + player.getCurrentFrame().getRegionHeight() / 2f;

        float weaponWidth = weaponRegion.getRegionWidth();
        float weaponHeight = weaponRegion.getRegionHeight();

        float originX = weaponWidth / 2f;
        float originY = weaponHeight / 2f;

        float drawX = playerCenterX - originX;
        float drawY = playerCenterY - originY;

        float rotationDegrees = weapon.getAimAngle() * MathUtils.radiansToDegrees;
//        weapon.setAimAngle(player.getAimAngle());
//        System.out.println("rotation: " + player.getAimAngle());
//        System.out.println("weapon angel: " + weapon.getAimAngle());
//        System.out.println("drawX: " + drawX);
//        System.out.println("drawY: " + drawY);

        //System.out.println(drawX - weapon.getX());
        batch.draw(
            weaponRegion,
            drawX, drawY,
            originX, originY,
            weaponWidth, weaponHeight,
            4f, 4f,
            player.getAimAngle() * MathUtils.radiansToDegrees
        );
    }


    public void handleWeaponRotation(float x, float y) {
        float weaponCenterX = weapon.getX() + weapon.getWeaponTexture().getRegionWidth() / 2f;
        float weaponCenterY = weapon.getY() + weapon.getWeaponTexture().getRegionHeight() / 2f;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);
        weapon.setAimAngle(angle);
    }

    public void handleWeaponShoot(Player player) {
        if (!weapon.canShoot()) {
            return;
        }

//        float startX = weapon.getX() + weapon.getWeaponTexture().getRegionWidth() / 2f;
//        float startY = weapon.getY() + weapon.getWeaponTexture().getRegionHeight() / 2f;

        float startX = player.getX() + player.getCurrentFrame().getRegionWidth() / 2f;
        float startY = player.getY() + player.getCurrentFrame().getRegionWidth() / 2f;

        float angleRadians = player.getAimAngle();

        Vector2 direction = new Vector2(
            (float) Math.cos(angleRadians),
            (float) Math.sin(angleRadians)
        ).nor();


        Bullet bullet = new Bullet(startX, startY, weapon.getBulletType());
        bullet.setDirection(direction);
        bullet.setSpeed(bulletSpeed);

        bullet.getSprite().setRotation(player.getAimAngle() * MathUtils.radiansToDegrees);
//        bullet.getSprite().setPosition(startX, startY);
        bullet.getSprite().setPosition(startX - bullet.getSprite().getWidth() / 2f, startY - bullet.getSprite().getHeight() / 2f);

        bullets.add(bullet);

        weapon.setLastShootTime();
        weapon.setAmmo(weapon.getAmmo() - 1);
    }

    public void handleAutoShoot(Player player, List<Enemy> enemies) {
        if (enemies == null || enemies.isEmpty()) {
            return;
        }

        List<Enemy> nonTreeEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (!(enemy instanceof Tree)) {
                nonTreeEnemies.add(enemy);
            }
        }

        if (nonTreeEnemies.isEmpty()) {
            return;
        }

        Enemy closestEnemy = nonTreeEnemies.get(0);
        double minDistance = Double.MAX_VALUE;

        for (Enemy enemy : nonTreeEnemies) {
            double dx = player.getX() - enemy.getX();
            double dy = player.getY() - enemy.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < minDistance) {
                minDistance = distance;
                closestEnemy = enemy;
            }
        }

        if (!weapon.canShoot()) {
            return;
        }

        float startX = player.getX() + player.getCurrentFrame().getRegionWidth() / 2f;
        float startY = player.getY() + player.getCurrentFrame().getRegionWidth() / 2f;

        Vector2 direction = new Vector2(new Vector2(closestEnemy.getX(), closestEnemy.getY())).sub(new Vector2(startX, startY)).nor();


        Bullet bullet = new Bullet(startX, startY, weapon.getBulletType());
        bullet.setDirection(direction);
        bullet.setSpeed(bulletSpeed);

        bullet.getSprite().setRotation(player.getAimAngle() * MathUtils.radiansToDegrees);
        bullet.getSprite().setPosition(startX - bullet.getSprite().getWidth() / 2f, startY - bullet.getSprite().getHeight() / 2f);

        bullets.add(bullet);

        weapon.setLastShootTime();
        weapon.setAmmo(weapon.getAmmo() - 1);
    }

    private void updateBullets(float delta) {
        SpriteBatch batch = Main.getBatch();
        Iterator<Bullet> iterator = bullets.iterator();

        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();


            Vector2 movement = new Vector2(bullet.getDirection())
                .scl(bullet.getSpeed() * delta);

            //System.out.println("Bullet movement: (" + movement.x + ", " + movement.y + ")");
            bullet.getSprite().setPosition(
                bullet.getSprite().getX() + movement.x,
                bullet.getSprite().getY() + movement.y
            );


            //System.out.println(bullet.getSprite().getX() - weapon.getX());

            bullet.getSprite().setOriginCenter();
            bullet.getSprite().setScale(0.03f);
            bullet.getSprite().draw(batch);


            if (isBulletOutOfScreen(bullet)) {
                //iterator.remove();
            }
        }
    }

    private boolean isBulletOutOfScreen(Bullet bullet) {
        float x = bullet.getSprite().getX();
        float y = bullet.getSprite().getY();
        float width = bullet.getSprite().getWidth() * bullet.getSprite().getScaleX();
        float height = bullet.getSprite().getHeight() * bullet.getSprite().getScaleY();

        return x + width < 0 || x > Gdx.graphics.getWidth() ||
            y + height < 0 || y > Gdx.graphics.getHeight();
    }
}
