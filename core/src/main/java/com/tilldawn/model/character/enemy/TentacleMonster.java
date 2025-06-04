package com.tilldawn.model.character.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.CollisionRect;
import com.tilldawn.model.character.player.Player;

public class TentacleMonster extends Enemy {
    private Animation<TextureRegion> walkingAnimation;
    private float speed = 20f;



    public TentacleMonster() {
        super();
        walkingAnimation = AssetManager.getAssetManager().getTentacleAnimation();
        x = spawnX();
        y = spawnY();
        position = new Vector2(x, y);
        collisionRect = new CollisionRect(x, y, getCurrentFrame().getRegionWidth(), getCurrentFrame().getRegionHeight());
    }



    public TextureRegion getCurrentFrame() {
        return walkingAnimation.getKeyFrame(stateTime, true);
    }

    @Override
    public void handleMovement(float delta, Player player) {
        facingRight = player.getX() - x > 0;
        Vector2 direction = new Vector2(new Vector2(player.getX(), player.getY())).sub(position).nor();
        position.add(direction.scl(speed * delta));
        x = position.x;
        y = position.y;
        collisionRect.move(x, y);
    }
}
