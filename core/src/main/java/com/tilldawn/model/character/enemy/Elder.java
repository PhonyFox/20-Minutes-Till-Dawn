package com.tilldawn.model.character.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.CollisionRect;
import com.tilldawn.model.character.player.Player;

public class Elder extends Enemy {
    private final Animation<TextureRegion> walkingAnimation;
    private final Animation<TextureRegion> dashingAnimation;
    private final float walkingSpeed = 60;
    private final float dashingSpeed = 800;
    private boolean walking = true;
    private Vector2 dashingDirection = new Vector2(0, 0);
    private long lastDashingTime;

    public Elder() {
        super();
        walkingAnimation = AssetManager.getAssetManager().getElderWalkingAnimation();
        dashingAnimation = AssetManager.getAssetManager().getElderDashingAnimation();
        x = spawnX();
        y = spawnY();
        position = new Vector2(x, y);
        lastDashingTime = System.currentTimeMillis();
        collisionRect = new CollisionRect(x, y, getCurrentFrame().getRegionWidth(), getCurrentFrame().getRegionHeight());
        hp = 25f;
    }

    public TextureRegion getCurrentFrame() {
        return walking ? walkingAnimation.getKeyFrame(stateTime, true) :
            dashingAnimation.getKeyFrame(stateTime, true);
    }

    @Override
    public void handleMovement(float delta, Player player) {
        //System.out.println("x: " + x + " y: " + y);
        if (System.currentTimeMillis() - lastDashingTime > 5000) {
            lastDashingTime = System.currentTimeMillis();
            dashingDirection = new Vector2(new Vector2(player.getX(), player.getY())).sub(position).nor();
            walking = false;
            stateTime = 0f;
        }
        if (walking) {
            facingRight = player.getX() - x + 5 > 0;
            Vector2 direction = new Vector2(new Vector2(player.getX(), player.getY())).sub(position).nor();
            position.add(direction.scl(walkingSpeed * delta));
            x = position.x;
            y = position.y;
            collisionRect.move(x, y);
        } else {
            if (dashingAnimation.getKeyFrameIndex(stateTime) >= 14) {
                position.add(new Vector2(dashingDirection).scl(dashingSpeed * delta));
                x = position.x;
                y = position.y;
                collisionRect.move(x, y);
            }
            if (dashingAnimation.getKeyFrameIndex(stateTime) >= 22) {
                walking = true;
                stateTime = 0f;
            }
        }
    }

}
