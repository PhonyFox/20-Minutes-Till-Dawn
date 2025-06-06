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
import com.tilldawn.model.GamaText;

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

        TextButton heroHintsButton = new TextButton(GamaText.HINT_HERO.get(), skin);
        TextButton abilityHintsButton = new TextButton(GamaText.HINT_ABILITIES.get(), skin);
        TextButton cheatButton = new TextButton(GamaText.MENU_CHEATS.get(), skin);
        TextButton keyBindingButton = new TextButton(GamaText.LABEL_KEYBINDING.get(), skin);

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
                showTextDialog(GamaText.LABEL_ABILITIES.get(), controller.getAbilityDescription());
            }
        });

        cheatButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                showTextDialog(GamaText.LABEL_CHEATS.get(), controller.getCheatCodes());
            }
        });

        keyBindingButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                showTextDialog(GamaText.MENU_KEY_BINDING.get(), controller.getKeyBindings());
            }
        });
    }

    private void showHeroDialog() {
        Dialog heroListDialog = new Dialog(GamaText.HINT_HERO.get(), skin);
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
        heroListDialog.button(GamaText.BUTTON_CLOSE.get(), false);
        heroListDialog.show(stage);
    }

    private void showTextDialog(String title, String description) {
        Dialog dialog = new Dialog(title, skin);
        dialog.text(description);
        dialog.button(GamaText.MENU_OK.get(), false);
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
