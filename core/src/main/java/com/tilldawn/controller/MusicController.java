package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicController {

    private static MusicController instance;

    private Music music;
    private float volume = 1.0f;

    private MusicController() {
    }

    public static MusicController getInstance() {
        if (instance == null) {
            instance = new MusicController();
        }
        return instance;
    }

    public void loadMusic(String fileName, boolean looping) {
        if (music != null) {
            music.dispose();
        }
        music = Gdx.audio.newMusic(Gdx.files.internal(fileName));
        music.setLooping(looping);
        music.setVolume(volume);
    }

    public void play() {
        if (music != null && !music.isPlaying()) {
            music.play();
        }
    }

    public void pause() {
        if (music != null && music.isPlaying()) {
            music.pause();
        }
    }

    public void stop() {
        if (music != null) {
            music.stop();
        }
    }

    public void setVolume(float volume) {
        this.volume = volume;
        if (music != null) {
            music.setVolume(volume);
        }
    }

    public boolean isPlaying() {
        return music != null && music.isPlaying();
    }

    public void dispose() {
        if (music != null) {
            music.dispose();
            music = null;
        }
    }
}

