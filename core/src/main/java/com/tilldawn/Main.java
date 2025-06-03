package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.controller.MusicController;
import com.tilldawn.controller.SignupMenuController;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.Repository;
import com.tilldawn.view.SignupMenuView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private final Repository repository = new Repository();

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        MusicController.getInstance().loadMusic(AssetManager.getAssetManager().getStartingMusic(), true);
        MusicController.getInstance().play();
        getMain().setScreen(new SignupMenuView(new SignupMenuController(repository)));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }
}
