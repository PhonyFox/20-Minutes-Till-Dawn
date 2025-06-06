package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sfx {
    private static final Sound shooting = Gdx.audio.newSound(Gdx.files.internal(AssetManager.getAssetManager().getShootingSound()));
    private static final Sound ow = Gdx.audio.newSound(Gdx.files.internal(AssetManager.getAssetManager().getOwSound()));
    private static final Sound levelUp = Gdx.audio.newSound(Gdx.files.internal(AssetManager.getAssetManager().getLevelUpSound()));

    private static boolean isSfxOn = true;

    public static void shoot() {
        if(isSfxOn) shooting.play();
    }

    public static void ow() {
        if(isSfxOn) ow.play();
    }

    public static void levelUp() {
        if(isSfxOn) levelUp.play();
    }

    public static void toggleSfx() {
        isSfxOn = !isSfxOn;
    }
}
