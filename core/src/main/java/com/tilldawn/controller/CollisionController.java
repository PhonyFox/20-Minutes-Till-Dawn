package com.tilldawn.controller;

import com.badlogic.gdx.math.Vector2;
import com.tilldawn.model.CollisionRect;
import com.tilldawn.model.DeadBody;
import com.tilldawn.model.Seed;
import com.tilldawn.model.character.enemy.Elder;
import com.tilldawn.model.character.enemy.Enemy;
import com.tilldawn.model.character.enemy.Eyebat;
import com.tilldawn.model.character.enemy.Tree;
import com.tilldawn.model.character.player.Player;
import com.tilldawn.model.weapon.Bullet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionController {
    private final GameController gameController;
    private Player player;

    public CollisionController(GameController gameController) {
        this.gameController = gameController;
        this.player = gameController.getRepo().getCurrentUser().getPlayer();
    }

    public void update() {
        List<Enemy> enemiesToRemove = new ArrayList<>();
        Iterator<Enemy> Enemyiterator = gameController.getEnemiesController().getEnemies().iterator();
        while (Enemyiterator.hasNext()) {
            Enemy enemy = Enemyiterator.next();
            if (enemy instanceof Eyebat) {
                Iterator<Bullet> bulletIterator = ((Eyebat) enemy).getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if (bullet.getCollisionRect().collidesWith(new CollisionRect(player.getX() - 560, player.getY() - 560, 18, 28))) {
                        player.decreaseHp(1);
                        bulletIterator.remove();
                    }
                }
            }
            if (enemy.getCollisionRect().collidesWith(new CollisionRect(player.getX(), player.getY(), 18, 28))) {
                if (!(enemy instanceof Elder)) {
                    player.decreaseHp(1);
                    if (!(enemy instanceof Tree)) {
                        enemiesToRemove.add(enemy);
                        player.advanceNumberOfKills();
                        gameController.getEnemiesController().addDeadBody(new DeadBody(enemy, enemy.getX(), enemy.getY()));
                        player.addSeed(new Seed(enemy.getCollisionRect().getX(), enemy.getCollisionRect().getY()));
                    }
                }
            }
            Iterator<Bullet> playerBullet = gameController.getPlayerController().getController().getBullets().iterator();
            while (playerBullet.hasNext()) {
                Bullet bullet = playerBullet.next();
                if (enemy.getCollisionRect().collidesWith(new CollisionRect(bullet.getCollisionRect().getX() + 560, bullet.getCollisionRect().getY() + 560, 5, 5))) {
                    playerBullet.remove();
                    if (!(enemy instanceof Tree)) {
                        enemy.decreaseHP(player.hasDamager() ? 1.25f : 1f);

                        Vector2 bulletDirection = bullet.getDirection(); //
                        Vector2 knockbackDirection = new Vector2(bulletDirection).nor().scl(30f);
                        enemy.setX(enemy.getX() + knockbackDirection.x);
                        enemy.setY(enemy.getY() + knockbackDirection.y);

                        if (enemy.isDead()) {
                            enemiesToRemove.add(enemy);
                            gameController.getEnemiesController().addDeadBody(new DeadBody(enemy, enemy.getX(), enemy.getY()));
                            player.advanceNumberOfKills();
                            player.addSeed(new Seed(enemy.getCollisionRect().getX(), enemy.getCollisionRect().getY()));
                            if (enemy instanceof Elder) {
                                gameController.getEnemiesController().killElder();
                            }
                        }
                    }
                }
            }

        }

        gameController.getEnemiesController().getEnemies().removeAll(enemiesToRemove);

        Iterator<Seed> seedIterator = player.getSeeds().iterator();
        while (seedIterator.hasNext()) {
            Seed seed = seedIterator.next();
            if (new CollisionRect(player.getX() + 30, player.getY(), 36, 56).collidesWith(seed.getX(), seed.getY())) {
                seedIterator.remove();
                player.increaseXp(3);
            }
        }
    }


}
