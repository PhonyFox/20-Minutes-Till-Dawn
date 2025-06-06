package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderManager {
    private static ShaderProgram grayscaleShader;

    public static void load() {
        if (grayscaleShader == null) {
            String vertexShader = Gdx.files.internal("default.vert").readString();
            String fragmentShader = Gdx.files.internal("grayscale.frag").readString();

            ShaderProgram.pedantic = false;
            grayscaleShader = new ShaderProgram(vertexShader, fragmentShader);
            if (!grayscaleShader.isCompiled()) {
                throw new RuntimeException("Shader compile error: " + grayscaleShader.getLog());
            }
        }
    }

    public static ShaderProgram getGrayscaleShader() {
        if (grayscaleShader == null) load();
        return grayscaleShader;
    }

    public static void dispose() {
        if (grayscaleShader != null) grayscaleShader.dispose();
    }
}
