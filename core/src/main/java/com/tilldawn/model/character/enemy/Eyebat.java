package com.tilldawn.model.character.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.CollisionRect;
import com.tilldawn.model.character.player.Player;
import com.tilldawn.model.weapon.Bullet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Eyebat extends Enemy {
    private final Animation<TextureRegion> walkingAnimation;
    private final float speed = 40f;
    private long lastShootTime;
    private List<Bullet> bullets = new LinkedList<>();
    private final float bulletSpeed = 120f;


    public Eyebat() {
        super();
        walkingAnimation = AssetManager.getAssetManager().getEyebatAnimation();
        x = spawnX();
        y = spawnY();
        position = new Vector2(x, y);
        lastShootTime = System.currentTimeMillis();
        collisionRect = new CollisionRect(x, y, getCurrentFrame().getRegionWidth(), getCurrentFrame().getRegionHeight());
    }

    public TextureRegion getCurrentFrame() {
        return walkingAnimation.getKeyFrame(stateTime, true);
    }

    @Override
    public void handleMovement(float delta, Player player) {
        facingRight = player.getX() - x > 0;
        Vector2 direction = new Vector2(new Vector2(player.getX(), player.getY())).sub(position).nor();
        position.add(direction.scl(speed * delta));
        x = position.x;
        y = position.y;
        collisionRect.move(x, y);
        if (System.currentTimeMillis() - lastShootTime > 3000) {
            lastShootTime = System.currentTimeMillis();
            handleShoot(player);
        }
        updateBullets(delta);
    }

    private void handleShoot(Player player) {
        float startX = x + getCurrentFrame().getRegionWidth() / 2f;
        float startY = y + getCurrentFrame().getRegionHeight() / 2f;

        Vector2 direction = new Vector2(new Vector2(player.getX(), player.getY())).sub(position).nor();

        Bullet bullet = new Bullet(startX, startY, BulletType.MM9);
        bullet.setDirection(direction);
        bullet.setSpeed(bulletSpeed);

        bullet.getSprite().setRotation(player.getAimAngle() * MathUtils.radiansToDegrees);
        bullet.getSprite().setPosition(startX - bullet.getSprite().getWidth() / 2f, startY - bullet.getSprite().getHeight() / 2f);

        bullets.add(bullet);
    }

    private void updateBullets(float delta) {
        SpriteBatch batch = Main.getBatch();
        Iterator<Bullet> iterator = bullets.iterator();

        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            //System.out.println(bullet.getCollisionRect().getX() + ", " + bullet.getCollisionRect().getY() + "for bullet");

            Vector2 movement = new Vector2(bullet.getDirection())
                .scl(bullet.getSpeed() * delta);

            //System.out.println("Bullet movement: (" + movement.x + ", " + movement.y + ")");
            bullet.getSprite().setPosition(
                bullet.getSprite().getX() + movement.x,
                bullet.getSprite().getY() + movement.y
            );

            bullet.getCollisionRect().move(bullet.getSprite().getX(), bullet.getSprite().getY());

            //System.out.println(bullet.getSprite().getX() - weapon.getX());

            bullet.getSprite().setOriginCenter();
            bullet.getSprite().setScale(0.1f);
            bullet.getSprite().draw(batch);
//
//
//            if (isBulletOutOfScreen(bullet)) {
//                //iterator.remove();
//            }
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}
