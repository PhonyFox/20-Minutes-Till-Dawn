package com.tilldawn.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.Random;
import com.tilldawn.model.Repository;
import com.tilldawn.model.character.enemy.Enemy;
import com.tilldawn.model.character.enemy.TentacleMonster;
import com.tilldawn.model.character.enemy.Tree;

import java.util.ArrayList;
import java.util.List;

public class EnemiesController implements InputProcessor {
    private final List<Enemy> enemies = new ArrayList<>();
    private final static int NUMBER_OF_TREES = 30;
    private final Repository repo;
    private long lastTentacleSpawnTime = 0;

    public EnemiesController(Repository repo) {
        initializeTrees();
        this.repo = repo;
    }

    public void update(float delta) {
        if (System.currentTimeMillis() - lastTentacleSpawnTime > 3000) {
            System.out.println("Tentacle spawn time: " + lastTentacleSpawnTime);
            System.out.println("Number of tentacles: " + (System.currentTimeMillis() - repo.getStartingTime()) / 30000);
            for (int i = 0; i < (System.currentTimeMillis() - repo.getStartingTime()) / 30000; i++) {
                makeTentacle();
            }
            lastTentacleSpawnTime = System.currentTimeMillis();
        }

        for (Enemy enemy : enemies) {
            handleMovement(delta, enemy);
            render(Main.getBatch(), enemy);
            enemy.updateTime(delta);
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
            enemies.add(tree);
        }
    }

    private void makeTentacle() {
        enemies.add(new TentacleMonster());
    }

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
