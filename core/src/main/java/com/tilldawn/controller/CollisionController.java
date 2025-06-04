package com.tilldawn.controller;

import com.tilldawn.model.CollisionRect;
import com.tilldawn.model.Seed;
import com.tilldawn.model.character.enemy.Elder;
import com.tilldawn.model.character.enemy.Enemy;
import com.tilldawn.model.character.enemy.Eyebat;
import com.tilldawn.model.character.enemy.Tree;
import com.tilldawn.model.character.player.Player;
import com.tilldawn.model.weapon.Bullet;

import java.util.Iterator;

public class CollisionController {
    private final GameController gameController;
    private Player player;

    public CollisionController(GameController gameController) {
        this.gameController = gameController;
        this.player = gameController.getRepo().getCurrentUser().getPlayer();
    }

    public void update() {
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
                    player.addSeed(new Seed(enemy.getCollisionRect().getX(), enemy.getCollisionRect().getY()));
                    player.decreaseHp(1);
                    if (!(enemy instanceof Tree)) Enemyiterator.remove();
                }
            }
            Iterator<Bullet> playerBullet = gameController.getPlayerController().getController().getBullets().iterator();
            while (playerBullet.hasNext()) {
                Bullet bullet = playerBullet.next();
                if (enemy.getCollisionRect().collidesWith(new CollisionRect(bullet.getCollisionRect().getX() + 560, bullet.getCollisionRect().getY() + 560, 5, 5))) {
                    playerBullet.remove();
                    if (!(enemy instanceof Tree)) {
                        enemy.decreaseHP(1f);
                        if (enemy.isDead()) {
                            Enemyiterator.remove();
                            player.addSeed(new Seed(enemy.getCollisionRect().getX(), enemy.getCollisionRect().getY()));
                        }
                    }
                }
            }
        }

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
