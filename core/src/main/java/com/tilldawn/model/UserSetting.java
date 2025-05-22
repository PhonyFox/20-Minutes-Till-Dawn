package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;

public class UserSetting {

    private final Preferences preferences;

    private int infoKey;
    private int moveUpKey;
    private int moveDownKey;
    private int moveLeftKey;
    private int moveRightKey;
    private int reloadKey;
    private int pauseKey;

    private int levelUpKeyUp;
    private int levelUpKeyDown;
    private int levelUpKeyLeft;
    private int levelUpKeyRight;

    private int shootButton;

    public UserSetting() {
        preferences = Gdx.app.getPreferences("UserKeyBindings");
        loadSettings();
    }

    private void loadSettings() {
        infoKey = preferences.getInteger("info", Input.Keys.I);
        moveUpKey = preferences.getInteger("moveUp", Input.Keys.W);
        moveDownKey = preferences.getInteger("moveDown", Input.Keys.S);
        moveLeftKey = preferences.getInteger("moveLeft", Input.Keys.A);
        moveRightKey = preferences.getInteger("moveRight", Input.Keys.D);
        reloadKey = preferences.getInteger("reload", Input.Keys.R);
        pauseKey = preferences.getInteger("pause", Input.Keys.ESCAPE);

        levelUpKeyUp = preferences.getInteger("levelUpUp", Input.Keys.UP);
        levelUpKeyDown = preferences.getInteger("levelUpDown", Input.Keys.DOWN);
        levelUpKeyLeft = preferences.getInteger("levelUpLeft", Input.Keys.LEFT);
        levelUpKeyRight = preferences.getInteger("levelUpRight", Input.Keys.RIGHT);

        shootButton = preferences.getInteger("shoot", Input.Buttons.LEFT);
    }

    public void saveSettings() {
        preferences.putInteger("info", infoKey);
        preferences.putInteger("moveUp", moveUpKey);
        preferences.putInteger("moveDown", moveDownKey);
        preferences.putInteger("moveLeft", moveLeftKey);
        preferences.putInteger("moveRight", moveRightKey);
        preferences.putInteger("reload", reloadKey);
        preferences.putInteger("pause", pauseKey);

        preferences.putInteger("levelUpUp", levelUpKeyUp);
        preferences.putInteger("levelUpDown", levelUpKeyDown);
        preferences.putInteger("levelUpLeft", levelUpKeyLeft);
        preferences.putInteger("levelUpRight", levelUpKeyRight);

        preferences.putInteger("shoot", shootButton);
        preferences.flush();
    }

    public void setKey(String action, int keyCode) {
        switch (action) {
            case "info": infoKey = keyCode; break;
            case "moveUp": moveUpKey = keyCode; break;
            case "moveDown": moveDownKey = keyCode; break;
            case "moveLeft": moveLeftKey = keyCode; break;
            case "moveRight": moveRightKey = keyCode; break;
            case "reload": reloadKey = keyCode; break;
            case "pause": pauseKey = keyCode; break;
            case "levelUpUp": levelUpKeyUp = keyCode; break;
            case "levelUpDown": levelUpKeyDown = keyCode; break;
            case "levelUpLeft": levelUpKeyLeft = keyCode; break;
            case "levelUpRight": levelUpKeyRight = keyCode; break;
            default: System.out.println("Unknown action: " + action); return;
        }
        saveSettings();
    }

    public void setMouseButton(String action, int button) {
        if ("shoot".equals(action)) {
            shootButton = button;
            saveSettings();
        }
    }

    public int getInfoKey() { return infoKey; }
    public int getMoveUpKey() { return moveUpKey; }
    public int getMoveDownKey() { return moveDownKey; }
    public int getMoveLeftKey() { return moveLeftKey; }
    public int getMoveRightKey() { return moveRightKey; }
    public int getReloadKey() { return reloadKey; }
    public int getPauseKey() { return pauseKey; }
    public int getLevelUpKeyUp() { return levelUpKeyUp; }
    public int getLevelUpKeyDown() { return levelUpKeyDown; }
    public int getLevelUpKeyLeft() { return levelUpKeyLeft; }
    public int getLevelUpKeyRight() { return levelUpKeyRight; }
    public int getShootButton() { return shootButton; }

    public boolean isActionPressed(String action) {
        switch (action) {
            case "info": return Gdx.input.isKeyPressed(infoKey);
            case "moveUp": return Gdx.input.isKeyPressed(moveUpKey);
            case "moveDown": return Gdx.input.isKeyPressed(moveDownKey);
            case "moveLeft": return Gdx.input.isKeyPressed(moveLeftKey);
            case "moveRight": return Gdx.input.isKeyPressed(moveRightKey);
            case "reload": return Gdx.input.isKeyPressed(reloadKey);
            case "pause": return Gdx.input.isKeyPressed(pauseKey);
            case "levelUpUp": return Gdx.input.isKeyPressed(levelUpKeyUp);
            case "levelUpDown": return Gdx.input.isKeyPressed(levelUpKeyDown);
            case "levelUpLeft": return Gdx.input.isKeyPressed(levelUpKeyLeft);
            case "levelUpRight": return Gdx.input.isKeyPressed(levelUpKeyRight);
            default: return false;
        }
    }

    public boolean isShootPressed() {
        return Gdx.input.isButtonPressed(shootButton);
    }
}
