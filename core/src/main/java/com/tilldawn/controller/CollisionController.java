package com.tilldawn.controller;

import com.tilldawn.model.CollisionRect;
import com.tilldawn.model.character.enemy.Elder;
import com.tilldawn.model.character.enemy.Enemy;
import com.tilldawn.model.character.enemy.Eyebat;
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
        Iterator<Enemy> iterator = gameController.getEnemiesController().getEnemies().iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (enemy instanceof Eyebat) {
                Iterator<Bullet> bulletIterator = ((Eyebat) enemy).getBullets().iterator();
                //System.out.println("Bullet collided: " + ((Eyebat) enemy).getBullets().size());
                while (bulletIterator.hasNext()) {

                    Bullet bullet = bulletIterator.next();
                    //System.out.println((player.getX() - bullet.getCollisionRect().getX()) + ", " + (player.getY() - bullet.getCollisionRect().getY())  + " for player");
                    if (bullet.getCollisionRect().collidesWith(new CollisionRect(player.getX() - 560, player.getY() - 560, 18, 28))) {
                        //System.out.println("ho");
                        bulletIterator.remove();
                    }
                }
            }
            if (enemy.getCollisionRect().collidesWith(new CollisionRect(player.getX(), player.getY(), 18, 28))) {
                if (!(enemy instanceof Elder)) {
                    iterator.remove();
                    //System.out.println(enemy.getCollisionRect().getX());
                    //System.out.println(enemy.getCollisionRect().getY());
                }
            }
            Iterator<Bullet> playerBullet = gameController.getPlayerController().getController().getBullets().iterator();
            while (playerBullet.hasNext()) {
                Bullet bullet = playerBullet.next();
                if (enemy.getCollisionRect().collidesWith(new CollisionRect(bullet.getCollisionRect().getX() + 560, bullet.getCollisionRect().getY() + 560, 5, 5))) {
                    playerBullet.remove();
                    iterator.remove();
                }
            }
        }


    }

}
