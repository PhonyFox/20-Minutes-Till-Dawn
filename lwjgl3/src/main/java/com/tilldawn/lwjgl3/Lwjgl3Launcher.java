package com.tilldawn.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.*;
import com.tilldawn.Main;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWDropCallback;

public class Lwjgl3Launcher {

    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return;
        new Lwjgl3Application(new Main(), getDefaultConfiguration());
    }

    /** پیکربندی پنجره و ثبت Listenerِ ساخت‌شدن پنجره */
    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("untildawn");
        config.useVsync(true);
        config.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        config.setWindowedMode(1920, 1080);
        config.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");

        /* ⬇️ Listener: وقتی پنجره ساخته شد، فایل‌دراپ را فعال کن */
        config.setWindowListener(new Lwjgl3WindowAdapter() {
            @Override
            public void created(Lwjgl3Window window) {
                long handle = window.getWindowHandle();
                GLFW.glfwSetDropCallback(handle, new GLFWDropCallback() {
                    @Override
                    public void invoke(long win, int count, long names) {
                        for (int i = 0; i < count; i++) {
                            String path = GLFWDropCallback.getName(names, i);
                            /* روی ترد رندر LibGDX اجرا شود */
                            Gdx.app.postRunnable(() -> {
                                System.out.println("📂 Dropped file: " + path);
                                Main.getMain().setDraggedAvatar(path);
                                Main.getMain().setHasDraggedAvatar(true);// متدی که در Main می‌سازی
                            });
                        }
                    }
                });
                System.out.println("✅ Drag & Drop listener attached");
            }
        });

        return config;
    }
}
