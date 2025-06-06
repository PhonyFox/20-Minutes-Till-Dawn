package com.tilldawn.model;

import com.badlogic.gdx.Input;

import java.util.HashMap;
import java.util.Map;

public class KeyBindingsManager {

    private static final Map<String, Integer> keyBindings = new HashMap<>();

    static {
        reset();
    }

    public static int getKeyBinding(String action) {
        return keyBindings.getOrDefault(action, -1);
    }

    public static void setKeyBinding(String action, int keycode) {
        keyBindings.put(action, keycode);
    }

    public static Map<String, Integer> getAllBindings() {
        return keyBindings;
    }

    public static void reset() {
        keyBindings.clear();
        keyBindings.put("jump", Input.Keys.SPACE);
        keyBindings.put("left", Input.Keys.A);
        keyBindings.put("right", Input.Keys.D);
        keyBindings.put("up", Input.Keys.W);
        keyBindings.put("down", Input.Keys.S);
        keyBindings.put("reload", Input.Keys.R);
        keyBindings.put("shoot", Input.Keys.ENTER);
        keyBindings.put("cheat1", Input.Keys.NUM_1);
        keyBindings.put("cheat2", Input.Keys.NUM_2);
        keyBindings.put("cheat3", Input.Keys.NUM_3);
        keyBindings.put("cheat4", Input.Keys.NUM_4);
        keyBindings.put("cheat5", Input.Keys.NUM_5);
    }
}

