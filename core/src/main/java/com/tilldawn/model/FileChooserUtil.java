package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooserUtil {

    public static void openImageFileChooser() {
        // اجرا روی ترد Swing
        SwingUtilities.invokeLater(() -> {
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle(GamaText.MSG_SELECT_IMAGE.get());

                // فیلتر فقط برای فایل‌های تصویری
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image files", "png", "jpg", "jpeg");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false); // فقط فیلتر بالا

                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();

                    System.out.println("✅ File selected: " + path);

                    // فراخوانی در ترد LibGDX
                    Gdx.app.postRunnable(() -> {
                        Main.getMain().setDraggedAvatar(path);
                        Main.getMain().setHasDraggedAvatar(true);
                        Main.getMain().getRepository().getCurrentUser().setAvatarPath(path);
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

