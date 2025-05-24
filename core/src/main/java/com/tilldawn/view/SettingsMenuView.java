package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.controller.SettingsMenuController;

public class SettingsMenuView extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private final SettingsMenuController controller;

    public SettingsMenuView(SettingsMenuController controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        Table table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        Label volumeLabel = new Label("Music Volume", skin);
        Slider volumeSlider = new Slider(0, 1, 0.1f, false, skin);
        volumeSlider.setValue(controller.getMusicVolume());

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.setMusicVolume(volumeSlider.getValue());
            }
        });

        Label musicLabel = new Label("Current Music", skin);
        SelectBox<String> musicSelectBox = new SelectBox<>(skin);
        musicSelectBox.setItems("default", "battle", "calm", "menu");
        musicSelectBox.setSelected(controller.getCurrentMusic());
        musicSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.setCurrentMusic(musicSelectBox.getSelected());
            }
        });

        CheckBox sfxCheckbox = new CheckBox("Enable SFX", skin);
        sfxCheckbox.setChecked(controller.isSfxEnable());
        sfxCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.toggleSFX();
            }
        });

        CheckBox autoReloadCheckbox = new CheckBox("Auto Reload", skin);
        autoReloadCheckbox.setChecked(controller.isAutoReload());
        autoReloadCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.toggleAutoReload();
            }
        });

        CheckBox grayscaleCheckbox = new CheckBox("Grayscale Mode", skin);
        grayscaleCheckbox.setChecked(controller.isGrayscale());
        grayscaleCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.toggleGrayscale();
            }
        });


        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //@
            }
        });

        table.add(volumeLabel).left(); table.add(volumeSlider).width(500).row();
        table.add(musicLabel).left(); table.add(musicSelectBox).width(500).row();
        table.add(sfxCheckbox).colspan(2).left().row();
        table.add(autoReloadCheckbox).colspan(2).left().row();
        table.add(grayscaleCheckbox).colspan(2).left().row();
        table.add(backButton).padTop(20).colspan(2).center().row();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.08f, 0.08f, 0.08f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
