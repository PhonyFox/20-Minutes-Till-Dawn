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
import com.tilldawn.model.*;
import com.tilldawn.model.character.enemy.Enemy;
import com.tilldawn.model.character.enemy.Tree;
import com.tilldawn.model.character.player.Player;
import com.tilldawn.view.PauseMenuView;

import java.util.List;

public class PlayerController implements InputProcessor {
    private final Player player;
    private float speed;
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
        this.speed = player.getSpeed();
    }

    public void update(float delta, List<Enemy> enemies) {
        checkStatus();
        if (player.getDamageTimer() > 0) {
            player.updateDamageTaken(delta);
        }

        if (player.getXp() > player.getExpToFinishLevel()) {
            if (!isLevelupStarted && !isLevelupAnimationDone) {
                isLevelupStarted = true;
                Sfx.levelUp();
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

        float zoneX = gameController.getZoneController().getX();
        float zoneY = gameController.getZoneController().getY();
        float zoneWidth = gameController.getZoneController().getWidth();
        float zoneHeight = gameController.getZoneController().getHeight();

        float playerWidth = 36;
        float playerHeight = 56;

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(gameController.getRepo(), gameController.getGameView()), gameController.getGameView()));
            return;
        }

        if (Gdx.input.isKeyJustPressed(KeyBindingsManager.getKeyBinding("jump"))) {
            player.toggleAutoAim();
        }

        if (Gdx.input.isKeyJustPressed(KeyBindingsManager.getKeyBinding("reload"))) {
            if (!player.getWeapon().isWhileReloading()) {
                player.getWeapon().setWhileReloading(true);
                player.getWeapon().setReloadingStartTime(System.currentTimeMillis());
            }
        }

        if (Gdx.input.isKeyJustPressed(KeyBindingsManager.getKeyBinding("cheat1"))) {
            gameController.getRepo().setStartingTime(gameController.getRepo().getStartingTime() - 60000);
        }

        if (Gdx.input.isKeyJustPressed(KeyBindingsManager.getKeyBinding("cheat2"))) {
            player.increaseXp(player.getExpToFinishLevel() + 1);
        }

        if (Gdx.input.isKeyJustPressed(KeyBindingsManager.getKeyBinding("cheat3"))) {
            if (player.getHp() < player.getMaxHp()) {
                player.setHp(player.getHp() + 1);
            }
        }

        if (Gdx.input.isKeyJustPressed(KeyBindingsManager.getKeyBinding("cheat4"))) {
            gameController.getRepo().setStartingTime(System.currentTimeMillis() - gameController.getRepo().getCurrentUser().getDuration() * 30000L);
        }

        if (Gdx.input.isKeyJustPressed(KeyBindingsManager.getKeyBinding("cheat5"))) {
            player.setHp(player.getMaxHp());
        }

        if (Gdx.input.isKeyPressed(KeyBindingsManager.getKeyBinding("left"))) {
            float nextX = player.getX() - currentSpeed * delta;
            if (nextX >= zoneX) {
                player.setPosition(nextX, player.getY());
                player.setFacingRight(false);
                moving = true;
            } else {
                player.decreaseHp(1);
            }
        }
        if (Gdx.input.isKeyPressed(KeyBindingsManager.getKeyBinding("right"))) {
            float nextX = player.getX() + currentSpeed * delta;
            if (nextX + playerWidth <= zoneX + zoneWidth) {
                player.setPosition(nextX, player.getY());
                player.setFacingRight(true);
                moving = true;
            } else {
                player.decreaseHp(1);
            }
        }
        if (Gdx.input.isKeyPressed(KeyBindingsManager.getKeyBinding("up"))) {
            float nextY = player.getY() + currentSpeed * delta;
            if (nextY + playerHeight <= zoneY + zoneHeight) {
                player.setPosition(player.getX(), nextY);
                moving = true;
            } else {
                player.decreaseHp(1);
            }
        }
        if (Gdx.input.isKeyPressed(KeyBindingsManager.getKeyBinding("down"))) {
            float nextY = player.getY() - currentSpeed * delta;
            if (nextY >= zoneY) {
                player.setPosition(player.getX(), nextY);
                moving = true;
            } else {
                player.decreaseHp(1);
            }
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

        if (player.getDamageTimer() > 0 && ((int)(player.getDamageTimer() * 10) % 2) == 0) {
            batch.setColor(1f, 0.3f, 0.3f, 1f);
        } else {
            batch.setColor(1f, 1f, 1f, 1f);
        }

        batch.draw(frame, player.getX(), player.getY(),
            frame.getRegionWidth() * AssetManager.SCALE, frame.getRegionHeight() * AssetManager.SCALE);
        batch.setColor(1f, 1f, 1f, 1f);

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

    public Enemy getClosestNonTreeEnemy(List<Enemy> enemies) {
        if (enemies == null || enemies.isEmpty()) {
            return null;
        }

        Enemy closestEnemy = null;
        double minDistance = Double.MAX_VALUE;

        for (Enemy enemy : enemies) {
            if (enemy instanceof Tree) continue;

            double dx = player.getX() - enemy.getX();
            double dy = player.getY() - enemy.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < minDistance) {
                minDistance = distance;
                closestEnemy = enemy;
            }
        }

        return closestEnemy;
    }

    private void checkStatus() {
        if (player.getHp() <= 0) {
            player.getUser().getUserState().advanceKills(player.getNumberOfKills());
            player.getUser().getUserState().advanceScore((int)(System.currentTimeMillis() - gameController.getRepo().getStartingTime()) / 1000 * player.getNumberOfKills());
            player.getUser().getUserState().advanceSurvivalTime((int)(System.currentTimeMillis() - gameController.getRepo().getStartingTime()));
            Main.getMain().setScreen(new EndGameScreen(player.getUser(), player.getNumberOfKills(), (gameController.getRepo().getCurrentUser().getDuration() * 60 - (int)((System.currentTimeMillis() - gameController.getRepo().getStartingTime()) / 1000)), false, gameController.getRepo()));
        }
        if (gameController.getRepo().getCurrentUser().getDuration() * 60 - (int)((System.currentTimeMillis() - gameController.getRepo().getStartingTime()) / 1000) <= 0) {
            player.getUser().getUserState().advanceKills(player.getNumberOfKills());
            player.getUser().getUserState().advanceScore((int)(System.currentTimeMillis() - gameController.getRepo().getStartingTime()) / 1000 * player.getNumberOfKills());
            player.getUser().getUserState().advanceSurvivalTime((int)(System.currentTimeMillis() - gameController.getRepo().getStartingTime()));
            Main.getMain().setScreen(new EndGameScreen(player.getUser(), player.getNumberOfKills(), player.getUser().getDuration(), true, gameController.getRepo()));
        }
    }



    @Override
    public boolean keyDown(int i) {
        System.out.println("yy");
        if (i == Input.Keys.SPACE) {
            System.out.println("SPACE pressed!");
            return true;
        }
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
