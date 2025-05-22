package com.tilldawn.model.ability;

import com.tilldawn.model.character.player.Player;

public interface Ability {
    void apply(Player player);
    void remove(Player player);
    int getDuration();
}
