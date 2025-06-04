package com.tilldawn.model.character.enemy;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.CollisionRect;
import com.tilldawn.model.character.player.Player;

public class Tree extends Enemy {
    private final static int SPAWN_X_START = 100;
    private final static int SPAWN_Y_START = 100;
    private final static int SPAWN_X_END = 3600;
    private final static int SPAWN_Y_END = 2500;
    private final TextureRegion texture = AssetManager.getAssetManager().getTreeTexture();

//    public Tree() {
//        super();
//        collisionRect = new CollisionRect(x, y, getCurrentFrame().getRegionWidth(), getCurrentFrame().getRegionHeight());
//    }

    public TextureRegion getCurrentFrame() {
        return texture;
    }

    public static int getSpawnXStart() {
        return SPAWN_X_START;
    }

    public static int getSpawnYStart() {
        return SPAWN_Y_START;
    }

    public static int getSpawnXEnd() {
        return SPAWN_X_END;
    }

    public static int getSpawnYEnd() {
        return SPAWN_Y_END;
    }

    @Override
    public void handleMovement(float delta, Player player) {}
    public void setCollisionRect() {
        collisionRect = new CollisionRect(x, y, getCurrentFrame().getRegionWidth() * 2, getCurrentFrame().getRegionHeight() * 2);
    }
}
