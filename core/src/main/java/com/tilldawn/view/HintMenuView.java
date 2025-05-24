package com.tilldawn.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.controller.HintMenuController;
import com.tilldawn.controller.PreGameMenuController;

public class HintMenuView extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private final HintMenuController controller;

    public HintMenuView(HintMenuController controller) {
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

        TextButton heroHintsButton = new TextButton("Hero Hints", skin);
        TextButton abilityHintsButton = new TextButton("Ability Hints", skin);
        TextButton cheatButton = new TextButton("Cheats", skin);
        TextButton keyBindingButton = new TextButton("Key Binding", skin);

        table.add(heroHintsButton);
        table.add(abilityHintsButton);
        table.add(cheatButton);
        table.add(keyBindingButton);

        heroHintsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                showHeroDialog();
            }
        });

        abilityHintsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                showTextDialog("Abilities", controller.getAbilityDescription());
            }
        });

        cheatButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                showTextDialog("Cheat Codes", controller.getCheatCodes());
            }
        });

        keyBindingButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                showTextDialog("Key Binding", controller.getKeyBindings());
            }
        });
    }

    private void showHeroDialog() {
        Dialog heroListDialog = new Dialog("Hero Hints", skin);
        Table content = heroListDialog.getContentTable();
        for (String hero : controller.getHeroNames()) {
            TextButton heroButton = new TextButton(hero, skin);
            heroButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    heroListDialog.hide();
                    showTextDialog(hero, controller.getHeroDescription(hero));
                }
            });
            content.add(heroButton).pad(5).row();
        }
        heroListDialog.button("Close", false);
        heroListDialog.show(stage);
    }

    private void showTextDialog(String title, String description) {
        Dialog dialog = new Dialog(title, skin);
        dialog.text(description);
        dialog.button("OK", false);
        dialog.show(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
