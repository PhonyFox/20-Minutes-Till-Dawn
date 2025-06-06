package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.EndGameScreen;
import com.tilldawn.controller.PauseMenuController;
import com.tilldawn.model.GamaText;
import com.tilldawn.model.character.player.Player;

public class PauseMenuView extends ScreenAdapter {
    private final PauseMenuController controller;
    private Stage stage;
    private Skin skin;
    private final GameView gameView;

    public PauseMenuView(PauseMenuController controller, GameView gameView) {
        this.controller = controller;
        this.gameView = gameView;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        Table table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        TextButton resumeButton = new TextButton(GamaText.BUTTON_RESUME.get(), skin);
        TextButton cheatButton = new TextButton(GamaText.BUTTON_CHEATS.get(), skin);
        TextButton abilitiesButton = new TextButton(GamaText.LABEL_ABILITIES.get(), skin);
        TextButton grayscaleButton = new TextButton(GamaText.TOGGLE_GRAYSCALE.get(), skin);
        TextButton giveUpButton = new TextButton(GamaText.BUTTON_GIVEUP.get(), skin);
        TextButton saveExitButton = new TextButton(GamaText.BUTTON_SAVE_EXIT.get(), skin);

        table.add(resumeButton).pad(10).row();
        table.add(cheatButton).pad(10).row();
        table.add(abilitiesButton).pad(10).row();
        table.add(grayscaleButton).pad(10).row();
        table.add(giveUpButton).pad(10).row();
        table.add(saveExitButton).pad(10).row();

        resumeButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.resumeGame();
            }
        });

        cheatButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                String cheatsInfo =
                    "🎮 Cheat Codes:\n\n" +
                        "1️⃣  NUM_1:\n" +
                        GamaText.CHEAT_MINUS_ONE_MINUTE.get() +
                        "2️⃣  NUM_2:\n" +
                        GamaText.CHEAT_INSTANT_XP.get() +
                        "3️⃣  NUM_3:\n" +
                        GamaText.CHEAT_HEAL_ONE.get() +
                        "4️⃣  NUM_4:\n" +
                        GamaText.CHEAT_MATCH_DURATION +
                        "5️⃣  NUM_5:\n" +
                        GamaText.CHEAT_FULL_HEAL.get();
                showDialog(GamaText.BUTTON_CHEATS.get(), cheatsInfo);
            }
        });

        abilitiesButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                String abilitiesInfo =
                    GamaText.POWER_VITALITY.get() +
                        GamaText.POWER_DAMAGER.get() +
                        GamaText.POWER_PROCREASE.get() +
                        GamaText.POWER_AMOCREATE.get() +
                        GamaText.POWER_SPEEDY.get();
                showDialog(GamaText.LABEL_ABILITIES.get(), abilitiesInfo);
            }
        });

        grayscaleButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.toggleGrayscale();
            }
        });

        giveUpButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Player player = controller.getRepo().getCurrentUser().getPlayer();
                player.getUser().getUserState().advanceKills(player.getNumberOfKills());
                player.getUser().getUserState().advanceScore((int)(System.currentTimeMillis() - controller.getRepo().getStartingTime()) / 1000 * player.getNumberOfKills());
                player.getUser().getUserState().advanceSurvivalTime((int)(System.currentTimeMillis() - controller.getRepo().getStartingTime()));
                Main.getMain().setScreen(new EndGameScreen(player.getUser(), player.getNumberOfKills(), (controller.getRepo().getCurrentUser().getDuration() * 60 - (int)((System.currentTimeMillis() - controller.getRepo().getStartingTime()) / 1000)), false, controller.getRepo()));
            }
        });

        saveExitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //@
            }
        });
    }

    private void showDialog(String title, String message) {
        Dialog dialog = new Dialog(title + "\n" + message, skin);
        dialog.text(message);
        dialog.button(GamaText.MENU_OK.get());
        dialog.setSize(400, 700);
        dialog.show(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    public GameView getGameView() {
        return gameView;
    }
}
