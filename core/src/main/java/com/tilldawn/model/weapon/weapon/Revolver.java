package com.tilldawn.model.weapon.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.model.character.enemy.BulletType;

public class Revolver implements Weapon {
    private final BulletType bulletType = BulletType.MAGNUM;
    //@
    private final Texture texture = null;
    private final Sprite sprite = null;
    private int magazineCapacity;
    private int damage;
    private int projectile;
}
