package com.tilldawn.controller;

import com.tilldawn.model.Repository;
import com.tilldawn.model.User;
import com.tilldawn.model.character.player.Hero;
import com.tilldawn.model.enums.HeroType;
import com.tilldawn.model.weapon.weapon.Revolver;
import com.tilldawn.model.weapon.weapon.SMGsDual;
import com.tilldawn.model.weapon.weapon.Shotgun;
import com.tilldawn.model.weapon.weapon.Weapon;

public class PreGameMenuController {
    private final Repository repo;
    private final User user;

    public PreGameMenuController(Repository repo) {
        this.repo = repo;
        this.user = repo.getCurrentUser();
    }
//    SHANA(4, 4, null),
//    DIAMOND(7, 1, null),
//    SCARLET(3, 5, null),
//    LILITH(5, 3, null),
//    DASHER(2, 10, null),;
    public void selectHero(String heroName) {
        Hero hero;
        switch (heroName.toLowerCase().trim()) {
            case "shana": hero = new Hero(HeroType.SHANA); break;
            case "diamond": hero = new Hero(HeroType.DIAMOND); break;
            case "scarlet": hero = new Hero(HeroType.SCARLET); break;
            case "lilith": hero = new Hero(HeroType.LILITH); break;
            case "dasher": hero = new Hero(HeroType.DASHER); break;
            default: hero = null; break;
        }
        repo.getCurrentUser().getPlayer().setHero(hero);
    }

    public void selectWeapon(String weaponName) {
        Weapon weapon;
        switch (weaponName.toLowerCase().trim()) {
            case "shotgun": weapon = new Shotgun(); break;
            case "smgsdual": weapon = new SMGsDual(); break;
            case "revolver": weapon = new Revolver(); break;
            default: weapon = null; break;
        }
        repo.getCurrentUser().getPlayer().setWeapon(weapon);
    }

    public void selectDuration(int duration) {
        repo.getCurrentUser().setDuration(duration);
    }

    public boolean isReadyToStart() {
        return repo.getCurrentUser().getPlayer() != null
            && repo.getCurrentUser().getPlayer().getHero() != null
            && repo.getCurrentUser().getDuration() > 0;
    }
}
