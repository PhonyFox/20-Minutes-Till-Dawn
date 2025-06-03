package com.tilldawn.controller;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;

public class ZoneController {
    private Animation<TextureRegion> zoneAnimation;
    private float stateTime;
    private float x, y;
    private float width, height;


    public ZoneController() {
        zoneAnimation = AssetManager.getAssetManager().getThunderAnimation();
        stateTime = 0f;
        x = 0;
        y = 0;
        width = 3776;
        height = 2688;
    }

    public void update(float delta) {
        stateTime += delta;
        float shrinkSpeed = 100f;
        float shrinkStep = shrinkSpeed * delta;
        if (width > 20 && height > 20) {
            width -= shrinkStep;
            height -= shrinkStep;
            x += shrinkStep / 2;
            y += shrinkStep / 2;
        }
        render(Main.getBatch());
    }

    public TextureRegion getCurrentFrame() {
        return zoneAnimation.getKeyFrame(stateTime, true);
    }

    private void render(SpriteBatch batch) {
        batch.draw(getCurrentFrame(), x, y, width, height);
    }
}
