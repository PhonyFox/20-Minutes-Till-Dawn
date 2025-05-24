package com.tilldawn.model.character.player;

import com.tilldawn.model.User;
import com.tilldawn.model.ability.TimedAbility;
import com.tilldawn.model.character.Character;
import com.tilldawn.model.weapon.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character {
    private final User user;
    private Hero hero;
    private float aimAngle;
    private int currentLevelNumber;
    private float currentLevelProgress;
    private int requiredExpToFinishLevel;
    private Weapon weapon;
    private final List<TimedAbility> timedAbilities;

    public Player(User user) {
        this.user = user;
        timedAbilities = new ArrayList<TimedAbility>();
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
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
}
