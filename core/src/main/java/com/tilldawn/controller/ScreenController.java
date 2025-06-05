package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.character.player.Player;

public class ScreenController {
    private final GameController gameController;
    private final Player player;
    private Stage uiStage;
    private final BitmapFont font;
    private final Skin skin;
    private ProgressBar xpBar;


    public ScreenController(GameController gameController) {
        this.gameController = gameController;
        this.player = gameController.getRepo().getCurrentUser().getPlayer();
        this.uiStage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        font = skin.getFont("title");
        font.getData().setScale(1.5f);
        xpBar = new ProgressBar(0, 100, 1f, false, skin);
        xpBar.setWidth(300);
        xpBar.setHeight(20);
        float x = (Gdx.graphics.getWidth() - xpBar.getWidth()) / 2f;
        float y = Gdx.graphics.getHeight() - 40;
        xpBar.setPosition(x, y);

        uiStage.addActor(xpBar);
    }


    public void update() {
        float progress = player.getXp() / (float) player.getExpToFinishLevel() * 100;

        xpBar.setValue(progress);

        uiStage.act(Gdx.graphics.getDeltaTime());
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

        int totalSeconds = gameController.getRepo().getCurrentUser().getDuration() * 60 - (int)((System.currentTimeMillis() - gameController.getRepo().getStartingTime()) / 1000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        String timeText = String.format("%02d:%02d", minutes, seconds);

        float timerX = Gdx.graphics.getWidth() - 330;
        float timerY = Gdx.graphics.getHeight() -10;

        String killsText = "Kills: " + player.getNumberOfKills();
        GlyphLayout layout = new GlyphLayout(font, killsText);

        float killsX = Gdx.graphics.getWidth() - layout.width - 30;
        float killsY = 50;

        String magazineText = "Magazine: " + player.getWeapon().getMagazineCapacity() + "/" + player.getWeapon().getAmmo();
        GlyphLayout layout2 = new GlyphLayout(font, magazineText);

        float magazineX = Gdx.graphics.getWidth() - layout2.width - 30;
        float magazineY = 50;


        String levelNumberText = "Level: " + player.getCurrentLevel();
        GlyphLayout layout3 = new GlyphLayout(font, levelNumberText);

        float levelNumberX = Gdx.graphics.getWidth() / 2f;
        float levelNumberY = Gdx.graphics.getHeight() - 80;


        float originalScale = font.getData().scaleX;


        font.getData().setScale(0.4f);

        font.draw(batch, killsText, killsX + 400, killsY);
        font.draw(batch, magazineText, magazineX + 450, magazineY);
        font.draw(batch, levelNumberText, levelNumberX, levelNumberY);



        font.getData().setScale(originalScale);
        font.draw(batch, timeText, timerX, timerY);


        uiStage.draw();
    }

}
