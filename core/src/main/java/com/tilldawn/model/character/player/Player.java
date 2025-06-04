package com.tilldawn.model.character.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.CollisionRect;
import com.tilldawn.model.User;
import com.tilldawn.model.ability.TimedAbility;
import com.tilldawn.model.character.Character;
import com.tilldawn.model.weapon.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character {
    private final User user;
    private float x, y;
    private Hero hero;
    private float aimAngle = 0f;
    private Animation<TextureRegion> walkingAnimation;
    private Animation<TextureRegion> idleAnimation;
    private int currentLevelNumber;
    private float currentLevelProgress;
    private int requiredExpToFinishLevel;
    private Weapon weapon;
    private final List<String> acquiredAbilities = new ArrayList<String>();
    private float stateTime = 0f;
    private boolean walking = false;
    private boolean hasAutoAim = false;
    private boolean hasSpeedy = false;
    private boolean hasDamager = false;
    private long speedyStartTime;
    private long damagerStartTime;
    private int hp = 3;

    public Player(User user) {
        this.user = user;
        this.x = 0;
        this.y = 0;
        collisionRect = new CollisionRect(x, y, 16, 28);
    }
    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        walkingAnimation = AssetManager.getAssetManager().getWalkFrames(hero.getHeroType().getHeroName());
        idleAnimation = AssetManager.getAssetManager().getIdleFrames(hero.getHeroType().getHeroName());
    }

    public float getAimAngle() {
        return aimAngle;
    }

    public void setAimAngle(float aimAngle) {
        this.aimAngle = aimAngle;
    }

    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }

    public void setCurrentLevelNumber(int currentLevelNumber) {
        this.currentLevelNumber = currentLevelNumber;
    }

    public float getCurrentLevelProgress() {
        return currentLevelProgress;
    }

    public void setCurrentLevelProgress(float currentLevelProgress) {
        this.currentLevelProgress = currentLevelProgress;
    }

    public int getRequiredExpToFinishLevel() {
        return requiredExpToFinishLevel;
    }

    public void setRequiredExpToFinishLevel(int requiredExpToFinishLevel) {
        this.requiredExpToFinishLevel = requiredExpToFinishLevel;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public List<String> getAcquiredAbilities() {
        return acquiredAbilities;
    }

    public TextureRegion getCurrentFrame() {
        return walking ? walkingAnimation.getKeyFrame(stateTime, true) :
            idleAnimation.getKeyFrame(stateTime, true);
    }


    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
//        collisionRect.setX(x);
//        collisionRect.setY(y);
    }
    public float getX() { return x; }
    public float getY() { return y; }
    public boolean isFacingRight() { return facingRight; }
    public void setFacingRight(boolean f) { facingRight = f; }
    public void setWalking(boolean w) { walking = w; }
    public boolean isWalking() { return walking; }
    public void updateTime(float delta) { stateTime += delta; }
    public float getStateTime() { return stateTime; }
    public boolean hasAutoAim() { return hasAutoAim; }
    public boolean hasSpeedy() { return hasSpeedy; }
    public boolean hasDamager() { return hasDamager; }
    public void setSpeedy(boolean s) { hasSpeedy = s; }
    public void setDamager(boolean s) { hasDamager = s; }
    public long getSpeedyStartTime() { return speedyStartTime; }
    public void setSpeedyStartTime(long s) { speedyStartTime = s; }
    public long getDamagerStartTime() { return damagerStartTime; }
    public void setDamagerStartTime(long s) { damagerStartTime = s; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
    public void advanceHp(int hp) {
        this.hp += hp;
    }
    public void decreaseHp(int hp) {
        this.hp -= hp;
    }
}
