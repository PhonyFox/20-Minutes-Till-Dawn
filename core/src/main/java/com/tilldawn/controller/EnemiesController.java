package com.tilldawn.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.Random;
import com.tilldawn.model.Repository;
import com.tilldawn.model.character.enemy.*;

import java.util.ArrayList;
import java.util.List;

public class EnemiesController implements InputProcessor {
    private final List<Enemy> enemies = new ArrayList<>();
    private final static int NUMBER_OF_TREES = 30;
    private final Repository repo;
    private long lastTentacleSpawnTime = 0;
    private long lastEyebatSpawnTime = 0;
    private boolean isElderSpawned = false;
    private boolean isElderDead = false;
    private final ZoneController zoneController;
    //private TextureRegion textureRegion;
    //private Animation<TextureRegion> zoneAnimation;

    public EnemiesController(Repository repo, ZoneController zoneController) {
        initializeTrees();
        this.repo = repo;
        this.zoneController = zoneController;
        //zoneAnimation = AssetManager.getAssetManager().getThunderAnimation();
    }

    public void update(float delta) {
//        if (isElderSpawned && !isElderDead) {
//            handleZone();
//        }
        if (System.currentTimeMillis() - lastTentacleSpawnTime > 3000) {
            for (int i = 0; i < (System.currentTimeMillis() - repo.getStartingTime()) / 30000; i++) {
                makeTentacle();
                lastTentacleSpawnTime = System.currentTimeMillis();
            }
        }

//        if ((System.currentTimeMillis() - repo.getStartingTime()) > repo.getCurrentUser().getDuration() * 15000L) {
//            if ((System.currentTimeMillis() - lastEyebatSpawnTime) > 10000) {
//                for (int i = 0; i < (4*((System.currentTimeMillis() - repo.getStartingTime()) / 1000) - repo.getCurrentUser().getDuration() * 15L + 30) / 30; i++) {
//                    makeEyebat();
//                    lastEyebatSpawnTime = System.currentTimeMillis() * 2;
//                    break;
//                    //#
//                }
//            }
//        }
        //#

        if (!isElderSpawned && ((System.currentTimeMillis() - repo.getStartingTime()) > repo.getCurrentUser().getDuration() * 500L)) {
            makeElder();
            isElderSpawned = true;
            makeEyebat();
        }

        for (Enemy enemy : enemies) {
            handleMovement(delta, enemy);
            render(Main.getBatch(), enemy);
            enemy.updateTime(delta);
        }

        if (isElderSpawned && !isElderDead) {
            zoneController.update(delta);
        }
    }

    public void render(SpriteBatch batch, Enemy enemy) {
        TextureRegion frame = enemy.getCurrentFrame();

        if (!enemy.isFacingRight() && !frame.isFlipX()) {
            frame.flip(true, false);
        } else if (enemy.isFacingRight() && frame.isFlipX()) {
            frame.flip(true, false);
        }

        batch.draw(frame, enemy.getX(), enemy.getY(),
            frame.getRegionWidth() * AssetManager.SCALE, frame.getRegionHeight() * AssetManager.SCALE);

    }

    private void handleMovement(float delta, Enemy enemy) {
        enemy.handleMovement(delta, repo.getCurrentUser().getPlayer());
//        if (enemy instanceof Elder) {
//            System.out.println("width: " + enemy.getCurrentFrame().getRegionWidth());
//            System.out.println("height: " + enemy.getCurrentFrame().getRegionHeight());
//        }

    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    private void initializeTrees() {
        for (int i = 0; i < NUMBER_OF_TREES; i++) {
            Tree tree = new Tree();
            tree.setX(Random.rand(Tree.getSpawnXStart(), Tree.getSpawnXEnd()));
            tree.setY(Random.rand(Tree.getSpawnYStart(), Tree.getSpawnYEnd()));
            tree.setCollisionRect();
            enemies.add(tree);
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    private void makeTentacle() {
        enemies.add(new TentacleMonster());
    }

    private void makeEyebat() {
        enemies.add(new Eyebat());
    }

    private void makeElder() {
        enemies.add(new Elder());
    }


//    private TextureRegion getCurrentZoneFrame() {
//
//    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
