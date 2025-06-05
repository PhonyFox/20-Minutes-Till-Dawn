package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.Random;
import com.tilldawn.model.Seed;
import com.tilldawn.model.character.enemy.Enemy;
import com.tilldawn.model.character.player.Player;

import java.util.List;

public class PlayerController implements InputProcessor {
    private final Player player;
    private float speed = 200f;
    private final WeaponController controller;
    private final GameController gameController;
    private boolean isLevelupStarted = false;
    private boolean isLevelupAnimationDone = false;
    private boolean isAbilityWindowShown = false;
    private float levelUpAnimationElapsed = 0f;



    public PlayerController(Player player, WeaponController controller, GameController gameController) {
        this.player = player;
        this.controller = controller;
        this.gameController = gameController;
    }

    public void update(float delta, List<Enemy> enemies) {

        if (player.getXp() > player.getExpToFinishLevel()) {
            if (!isLevelupStarted && !isLevelupAnimationDone) {
                isLevelupStarted = true;
                levelUpAnimationElapsed = 0f;
            }
        }

        if (isLevelupStarted && !isLevelupAnimationDone) {
            levelUpAnimationElapsed += delta;

            if (levelUpAnimationElapsed >= 2f) {
                isLevelupAnimationDone = true;
                isLevelupStarted = false;
            }
        }

        if (isLevelupAnimationDone && !isAbilityWindowShown) {
            gameController.showAbilityWindow();
            isAbilityWindowShown = true;
        }

        if (isAbilityWindowShown
            && player.getXp() <= player.getExpToFinishLevel()) {
            isAbilityWindowShown = false;
            isLevelupAnimationDone = false;
        }


        if (System.currentTimeMillis() - player.getDamagerStartTime() > 10000) {
            player.setDamager(false);
        }

        if (System.currentTimeMillis() - player.getSpeedyStartTime() > 10000) {
            player.setSpeedy(false);
        }

        handleMovement(delta);
        handleAim();
        player.updateTime(delta);
        render(Main.getBatch());
        controller.update(delta, player);
        if (player.hasAutoAim()) {
            controller.handleAutoShoot(player, enemies);
        }
    }

    private void handleMovement(float delta) {
        boolean moving = false;
        float currentSpeed = player.hasSpeedy() ? speed * 2 : speed;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setPosition(player.getX() - currentSpeed * delta, player.getY());
            player.setFacingRight(false);
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setPosition(player.getX() + currentSpeed * delta, player.getY());
            player.setFacingRight(true);
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.setPosition(player.getX(), player.getY() + currentSpeed * delta);
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.setPosition(player.getX(), player.getY() - currentSpeed * delta);
            moving = true;
        }
        player.setWalking(moving);
    }

    private void handleAim() {
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        float dx = mouseX - 986;
        float dy = mouseY - 589;
        float angle = (float)Math.atan2(dy, dx);
        player.setAimAngle(angle);
    }

    public void render(SpriteBatch batch) {
        TextureRegion frame = player.getCurrentFrame();

        if (!player.isFacingRight() && !frame.isFlipX()) {
            frame.flip(true, false);
        } else if (player.isFacingRight() && frame.isFlipX()) {
            frame.flip(true, false);
        }

        batch.draw(frame, player.getX(), player.getY(),
            frame.getRegionWidth() * AssetManager.SCALE, frame.getRegionHeight() * AssetManager.SCALE);

        for (Seed seed : player.getSeeds()) {
            batch.draw(seed.getRegion(), seed.getX(), seed.getY(), 15, 15);
        }

        if (isLevelupStarted) {
            batch.draw(player.getLevelUpFrame(), player.getX(), player.getY());
        }

    }

    public WeaponController getController() {
        return controller;
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
        controller.handleWeaponRotation(i, i1);
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
