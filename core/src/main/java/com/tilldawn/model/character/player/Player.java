package com.tilldawn.model.character.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.model.AssetManager;
import com.tilldawn.model.CollisionRect;
import com.tilldawn.model.Seed;
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
    private Animation<TextureRegion> heartAnimation;
    private int currentLevelNumber;
    private float currentLevelProgress;
    private int requiredExpToFinishLevel;
    private Weapon weapon;
    private final List<String> acquiredAbilities = new ArrayList<String>();
    private float stateTime = 0f;
    private boolean walking = false;
    private boolean hasAutoAim = true;
    private boolean hasSpeedy = false;
    private boolean hasDamager = false;
    private long speedyStartTime;
    private long damagerStartTime;
    private int hp = 3;
    private int maxHp = 3;
    private int xp = 0;
    private final List<Seed> seeds = new ArrayList<>();
    private long lastDamagedTime = 0;
    private int currentLevel = 1;
    private int expToFinishLevel = 20;
    private boolean isWaitedToChooseAbility = false;
    private Animation<TextureRegion> levelUpAnimation;


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
        levelUpAnimation = AssetManager.getAssetManager().getLevelUpAnimation();
        this.heartAnimation = AssetManager.getAssetManager().getHeartAnimation();
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

    public TextureRegion getLevelUpFrame() {
        return levelUpAnimation.getKeyFrame(stateTime, true);
    }

    public int getLevelUpFrameIndex() {
        return levelUpAnimation.getKeyFrameIndex(stateTime);
    }

    public TextureRegion getHeartFrame() {
        return heartAnimation.getKeyFrame(stateTime, true);
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
        if (System.currentTimeMillis() - lastDamagedTime > 1000) {
            this.hp -= hp;
            lastDamagedTime = System.currentTimeMillis();
        }
    }
    public int getMaxHp() { return maxHp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }
    public void increaseMaxHp(int hp) {
        this.maxHp += hp;
    }
    public void decreaseMaxHp(int hp) {
        this.maxHp -= hp;
    }
    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
    public void increaseLevelNumber() {
        currentLevel++;
        expToFinishLevel = currentLevel * 20;
    }
    public void increaseXp(int xp) {
        this.xp += xp;
//        if (this.xp >= expToFinishLevel) {
//            currentLevel++;
//            expToFinishLevel = currentLevel * 20;
//            this.xp = 0;
//        }
    }
    public void addSeed(Seed seed) {
        seeds.add(seed);
        System.out.println("############\n#######3\n###########\n");
    }
    public List<Seed> getSeeds() {
        return seeds;
    }
    public void removeSeed(Seed seed) {
        seeds.remove(seed);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getExpToFinishLevel() { return expToFinishLevel; }
    public void increaseMaxHP() {
        maxHp++;
    }

    public boolean isWaitedToChooseAbility() {
        return isWaitedToChooseAbility;
    }

    public void setWaitedToChooseAbility(boolean waitedToChooseAbility) {
        isWaitedToChooseAbility = waitedToChooseAbility;
    }

    public void resetStateTime() {
        stateTime = 0f;
    }
}
