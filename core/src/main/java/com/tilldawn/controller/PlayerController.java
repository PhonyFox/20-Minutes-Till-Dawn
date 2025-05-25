package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.model.character.player.Player;

public class PlayerController {
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

        controller.update(delta, player.getX() + 32, player.getY() + 32, player.getAimAngle());
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

        float dx = mouseX - (player.getX() + 32);
        float dy = mouseY - (player.getY() + 32);

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


        batch.draw(frame, player.getX(), player.getY());


        if (player.getWeapon() != null) {
            TextureRegion weaponRegion = player.getWeapon().getWeaponTexture();


            float originX = weaponRegion.getRegionWidth() / 2f;
            float originY = weaponRegion.getRegionHeight() / 2f;

            float weaponX = player.getX() + 32;
            float weaponY = player.getY() + 32;

            batch.draw(
                weaponRegion,
                weaponX, weaponY,
                originX, originY,
                weaponRegion.getRegionWidth(),
                weaponRegion.getRegionHeight(),
                1f, 1f,
                (float) Math.toDegrees(player.getAimAngle())
            );
        }
    }

}
