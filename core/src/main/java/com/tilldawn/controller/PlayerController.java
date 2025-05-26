package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.character.player.Player;

public class PlayerController implements InputProcessor {
    private final Player player;
    private float speed = 200f;
    private final WeaponController controller;

    public PlayerController(Player player, WeaponController controller) {
        this.player = player;
        this.controller = controller;
    }

    public void update(float delta) {
        handleMovement(delta);
        handleAim();
        player.updateTime(delta);
        render(Main.getBatch());
        controller.update(delta, player);
    }

    private void handleMovement(float delta) {
        boolean moving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setPosition(player.getX() - speed * delta, player.getY());
            player.setFacingRight(false);
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setPosition(player.getX() + speed * delta, player.getY());
            player.setFacingRight(true);
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.setPosition(player.getX(), player.getY() + speed * delta);
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.setPosition(player.getX(), player.getY() - speed * delta);
            moving = true;
        }

        player.setWalking(moving);
    }

    private void handleAim() {
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        float dx = mouseX - 986;
        float dy = mouseY - 589;
        //System.out.println(player.getX() + "   " + mouseX + "     " + mouseY + "      " + player.getY());
        float angle = (float)Math.atan2(dy, dx);
        player.setAimAngle(angle);
    }

    public void render(SpriteBatch batch) {
        TextureRegion frame = player.getCurrentFrame();
        //System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());

        if (!player.isFacingRight() && !frame.isFlipX()) {
            frame.flip(true, false);
        } else if (player.isFacingRight() && frame.isFlipX()) {
            frame.flip(true, false);
        }

        batch.draw(frame, player.getX(), player.getY(),
            frame.getRegionWidth() * AssetManager.SCALE, frame.getRegionHeight() * AssetManager.SCALE);


//        if (player.getWeapon() != null) {
//            TextureRegion weaponRegion = player.getWeapon().getWeaponTexture();
//
//
//            float originX = weaponRegion.getRegionWidth() / 2f;
//            float originY = weaponRegion.getRegionHeight() / 2f;
//
//            float weaponX = player.getX() + 32;
//            float weaponY = player.getY() + 32;
//
//            batch.draw(
//                weaponRegion,
//                weaponX, weaponY,
//                originX, originY,
//                weaponRegion.getRegionWidth(),
//                weaponRegion.getRegionHeight(),
//                1f, 1f,
//                (float) Math.toDegrees(player.getAimAngle())
//            );
//    }
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
        //controller.handleWeaponShoot(i, i1);
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
        //System.out.println("d");
        controller.handleWeaponRotation(i, i1);
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
