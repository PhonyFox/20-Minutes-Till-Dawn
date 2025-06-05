package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Cursor;

public class Assets {
    public static Cursor CUSTOM_CURSOR;

    public static void loadCursor() {
        Pixmap pm = new Pixmap(Gdx.files.internal("cursor.png"));
        CUSTOM_CURSOR = Gdx.graphics.newCursor(pm, 0, 0);
        pm.dispose();
    }

    public static void dispose() {
        if (CUSTOM_CURSOR != null) CUSTOM_CURSOR.dispose();
    }
}

