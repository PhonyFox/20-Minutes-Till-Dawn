package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.character.player.Player;

public class LightController {
    private final TextureRegion region = AssetManager.getAssetManager().getBlackTexture();
    private final Player player;

    public LightController(Player player) {
        this.player = player;
    }

    public void update() {
        render(Main.getBatch());
    }

    private void render(SpriteBatch batch) {
        float squareSize = 20f;
        float lightRadius = 200f;
        // 150 250 400
        float firstRadius = 150f;
        float secondRadius = 300f;
        float thirdRadius = 500f;

        float playerCenterX = player.getX() + 20;
        float playerCenterY = player.getY() + 30;


        for (float x = 0; x < 3900; x += squareSize) {
            for (float y = 0; y < 2800; y += squareSize) {
                float centerX = x + squareSize / 2f;
                float centerY = y + squareSize / 2f;

                float dist = Vector2.dst(centerX, centerY, playerCenterX, playerCenterY);

                if (dist > thirdRadius) {
                    batch.setColor(0, 0, 0, 0.5f);
                    batch.draw(region, x, y, squareSize, squareSize);
                    batch.setColor(1, 1, 1, 1);
                } else if (dist > secondRadius) {
                    batch.setColor(0, 0, 0, 0.3f);
                    batch.draw(region, x, y, squareSize, squareSize);
                    batch.setColor(1, 1, 1, 1);
                } else if (dist > firstRadius) {
                    batch.setColor(0, 0, 0, 0.15f);
                    batch.draw(region, x, y, squareSize, squareSize);
                    batch.setColor(1, 1, 1, 1);
                }
            }
        }
    }
}
