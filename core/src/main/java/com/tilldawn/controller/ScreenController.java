package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.character.player.Player;

public class ScreenController {
    private final GameController gameController;
    private final Player player;
    private Stage uiStage;


    public ScreenController(GameController gameController) {
        this.gameController = gameController;
        this.player = gameController.getRepo().getCurrentUser().getPlayer();
        this.uiStage = new Stage(new ScreenViewport());
    }


    public void update() {
        render(Main.getBatch());
    }

    public void render(SpriteBatch batch) {
        float startX = 15;
        float startY = Gdx.graphics.getHeight() - 15;

        float heartWidth = 60;
        float heartHeight = 60;

        for (int i = 0; i < player.getHp(); i++) {
            TextureRegion heartRegion = player.getHeartFrame();
            batch.draw(heartRegion, startX + i * (heartWidth + 5), startY - heartHeight, heartWidth, heartHeight);
        }
        for (int i = player.getHp(); i < player.getMaxHp(); i++) {
            TextureRegion emptyHeart = AssetManager.getAssetManager().getHeartTexture();
            batch.draw(emptyHeart, startX + i * (heartWidth + 5), startY - heartHeight, heartWidth, heartHeight);
        }
    }

}
