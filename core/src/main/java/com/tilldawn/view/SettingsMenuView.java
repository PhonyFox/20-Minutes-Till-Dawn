package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.SettingsMenuController;
import com.tilldawn.model.GamaText;
import com.tilldawn.model.KeyBindingsManager;

import java.util.LinkedHashMap;
import java.util.Map;

public class SettingsMenuView extends ScreenAdapter {

    private Stage stage;
    private Skin skin;
    private final SettingsMenuController controller;

    /* ---------- وضعیت تغییر کلید ---------- */
    private String waitingForAction = null;
    private final Map<String, TextButton> actionButtons = new LinkedHashMap<>();

    /* ---------- پیام راهنما ---------- */
    private Label infoLabel;

    public SettingsMenuView(SettingsMenuController controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        /* ---------- ریشهٔ رابط ---------- */
        Table root = new Table(skin);
        root.setFillParent(true);
        root.pad(20);
        stage.addActor(root);

        /* ---------- تنظیمات صدا ---------- */
        Label volumeLabel = new Label(GamaText.LABEL_MUSIC_VOLUME.get(), skin);
        Slider volumeSlider = new Slider(0, 1, 0.1f, false, skin);
        volumeSlider.setValue(controller.getMusicVolume());
        volumeSlider.addListener(e -> {
            controller.setMusicVolume(volumeSlider.getValue());
            return true;
        });

        Label musicLabel = new Label(GamaText.LABEL_CURRENT_MUSIC.get(), skin);
        SelectBox<String> musicSelect = new SelectBox<>(skin);
        musicSelect.setItems("default", "battle", "calm", "menu");
        musicSelect.setSelected(controller.getCurrentMusic());
        musicSelect.addListener(e -> {
            controller.setCurrentMusic(musicSelect.getSelected());
            return true;
        });

        /* ---------- چک‌باکس‌ها ---------- */
        CheckBox sfxChk    = makeCheck(GamaText.SETTING_SFX.get(),      controller::toggleSFX,      controller.isSfxEnable());
        CheckBox autoChk   = makeCheck(GamaText.SETTING_AUTORELOAD.get(),controller::toggleAutoReload,controller.isAutoReload());
        CheckBox grayChk   = makeCheck(GamaText.SETTING_GRAYSCALE.get(),controller::toggleGrayscale, controller.isGrayscale());
        CheckBox germanChk = makeCheck("German", () -> Main.getMain().setIsGermany(true), false);

        /* ---------- جدول کلیدها ---------- */
        Table keyTable = new Table(skin);
        keyTable.defaults().pad(4);

        addKeyButton(keyTable, "jump",   "Jump");
        addKeyButton(keyTable, "left",   "Move L");
        addKeyButton(keyTable, "right",  "Move R");
        addKeyButton(keyTable, "up",     "Move U");
        addKeyButton(keyTable, "down",   "Move D");
        addKeyButton(keyTable, "reload", "Reload");
        addKeyButton(keyTable, "shoot",  "Shoot");
        addKeyButton(keyTable, "cheat1", "Cheat 1");
        addKeyButton(keyTable, "cheat2", "Cheat 2");
        addKeyButton(keyTable, "cheat3", "Cheat 3");
        addKeyButton(keyTable, "cheat4", "Cheat 4");
        addKeyButton(keyTable, "cheat5", "Cheat 5");

        ScrollPane scroll = new ScrollPane(keyTable, skin);
        scroll.setFadeScrollBars(false);
        scroll.setScrollingDisabled(true, false);     // فقط عمودی
        scroll.setScrollbarsOnTop(true);

        /* ---------- پیام راهنما ---------- */
        infoLabel = new Label("", skin);

        /* ---------- دکمهٔ بازگشت ---------- */
        TextButton backBtn = new TextButton(GamaText.BUTTON_BACK.get(), skin);
        backBtn.addListener(e -> { controller.goToMainMenu(); return true; });

        /* ---------- چیدمان ---------- */
        root.defaults().pad(6).left();

        root.add(volumeLabel);
        root.add(volumeSlider).width(400).row();

        root.add(musicLabel);
        root.add(musicSelect).width(400).row();

        root.add(sfxChk).colspan(2).row();
        root.add(autoChk).colspan(2).row();
        root.add(grayChk).colspan(2).row();
        root.add(germanChk).colspan(2).row();

        root.add(new Label("Key Bindings", skin, "title")).colspan(2).padTop(15).center().row();
        root.add(scroll).colspan(2).minHeight(250).maxHeight(350).expandY().top().row();

        root.add(infoLabel).colspan(2).center().row();
        root.add(backBtn).colspan(2).padTop(10).center();

        /* ---------- گوش دادن به ورودی برای تعیین کلید ---------- */
        stage.addListener(new ChangeListener() {
            @Override public void changed(ChangeEvent event, Actor actor) { }
            @Override public boolean handle(Event evt) {
                if (waitingForAction != null && evt instanceof InputEvent) {
                    InputEvent ie = (InputEvent) evt;
                    if (ie.getType() == InputEvent.Type.keyDown) {
                        int keycode = ie.getKeyCode();
                        KeyBindingsManager.setKeyBinding(waitingForAction, keycode);
                        actionButtons.get(waitingForAction)
                            .setText(Input.Keys.toString(keycode));
                        waitingForAction = null;
                        infoLabel.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /* ---------- کمکی‌ها ---------- */

    private CheckBox makeCheck(String text, Runnable toggle, boolean initial) {
        CheckBox cb = new CheckBox(text, skin);
        cb.setChecked(initial);
        cb.addListener(e -> { toggle.run(); return true; });
        return cb;
    }

    private void addKeyButton(Table tbl, String action, String labelTxt) {
        int keycode = KeyBindingsManager.getKeyBinding(action);
        String text = keycode >= 0 ? Input.Keys.toString(keycode) : "UNBOUND";

        Label lbl = new Label(labelTxt, skin);
        TextButton btn = new TextButton(text, skin);

        btn.addListener(new ChangeListener() {
            @Override public void changed(ChangeEvent event, Actor actor) {
                waitingForAction = action;
                infoLabel.setText("Press a key for " + labelTxt + "…");
            }
        });

        actionButtons.put(action, btn);

        tbl.add(lbl).left().width(120);
        tbl.add(btn).width(160).row();
    }

    /* ---------- چرخهٔ گیم‌لوپ ---------- */

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
