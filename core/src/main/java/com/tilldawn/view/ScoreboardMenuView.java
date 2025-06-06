package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.controller.ScoreboardMenuController;
import com.tilldawn.model.GamaText;
import com.tilldawn.model.User;

import java.util.List;

public class ScoreboardMenuView extends ScreenAdapter {
    private final Stage stage;
    private final Skin skin;
    private final Table rootTable;
    private final ScoreboardMenuController controller;

    public ScoreboardMenuView(ScoreboardMenuController controller) {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        Gdx.input.setInputProcessor(stage);
        this.controller = controller;

        rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        createUI(ScoreboardMenuController.SortOption.SCORE);
    }

    private void createUI(ScoreboardMenuController.SortOption sortOption) {
        rootTable.clear();

        Label title = new Label("🏆 " + GamaText.SCOREBOARD_TITLE.get(), skin, "title");
        title.setAlignment(Align.center);
        rootTable.add(title).colspan(4).pad(20).row();

        Table scoreboardTable = new Table(skin);
        scoreboardTable.top();

        List<User> topUsers = controller.getTopUsers(sortOption);
        User currentUser = controller.getRepo().getCurrentUser();

        scoreboardTable.add(GamaText.SCOREBOARD_RANK.get()).pad(5);
        scoreboardTable.add(GamaText.SCOREBOARD_USERNAME.get()).pad(5);
        scoreboardTable.add(GamaText.SCOREBOARD_SCORE.get()).pad(5);
        scoreboardTable.add(GamaText.SCOREBOARD_KILLS.get()).pad(5);
        scoreboardTable.add(GamaText.SCOREBOARD_TIME.get()).pad(5);
        scoreboardTable.row();

        int rank = 1;
        for (User user : topUsers) {
            Label.LabelStyle style = new Label.LabelStyle(skin.get(Label.LabelStyle.class));

            if (user.equals(currentUser)) {
                style.fontColor = com.badlogic.gdx.graphics.Color.YELLOW;
            } else if (rank <= 3) {
                style.fontColor = rank == 1 ? com.badlogic.gdx.graphics.Color.GOLD :
                    rank == 2 ? Color.CYAN :
                        Color.BROWN;
            }

            scoreboardTable.add(new Label(rank + "", style)).pad(2);
            scoreboardTable.add(new Label(user.getUsername(), style)).pad(2);
            scoreboardTable.add(new Label(String.valueOf(user.getScore()), style)).pad(2);
            scoreboardTable.add(new Label(String.valueOf(user.getKills()), style)).pad(2);
            scoreboardTable.add(new Label(user.getSurvivalTime() + "s", style)).pad(2);
            scoreboardTable.row();
            rank++;
        }

        ScrollPane scrollPane = new ScrollPane(scoreboardTable);
        scrollPane.setFadeScrollBars(false);
        rootTable.add(scrollPane).colspan(4).padBottom(20).row();

        Table sortTable = new Table();
        addSortButton(sortTable, GamaText.SCOREBOARD_SORT_SCORE.get(), ScoreboardMenuController.SortOption.SCORE);
        addSortButton(sortTable, GamaText.SCOREBOARD_SORT_USERNAME.get(), ScoreboardMenuController.SortOption.USERNAME);
        addSortButton(sortTable, GamaText.SCOREBOARD_SORT_KILLS.get(), ScoreboardMenuController.SortOption.KILLS);
        addSortButton(sortTable, GamaText.SCOREBOARD_SORT_TIME.get(), ScoreboardMenuController.SortOption.SURVIVAL_TIME);

        rootTable.add(sortTable).colspan(4).padBottom(10).row();

        TextButton backButton = new TextButton(GamaText.BUTTON_BACK.get(), skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.goToMainMenu();
            }
        });
        rootTable.add(backButton).colspan(4).padBottom(20);
    }

    private void addSortButton(Table table, String text, ScoreboardMenuController.SortOption option) {
        TextButton button = new TextButton(text, skin);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                createUI(option);
            }
        });
        table.add(button).pad(5);
    }

    public void render() {
        stage.act();
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
