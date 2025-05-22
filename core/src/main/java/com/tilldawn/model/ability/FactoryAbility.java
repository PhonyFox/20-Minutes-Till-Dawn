package com.tilldawn.model.ability;

import com.tilldawn.model.enums.AbilityType;

public class FactoryAbility {
    public static Ability createAbility(AbilityType abilityType) {
        switch (abilityType) {
            case VITALITY: return new VitalityAbility();
            case DAMAGER: return new DamagerAbility();
            case PROCREASE: return new ProcIncreaseAbility();
            case AMORCREASE: return new AmmoIncreaseAbility();
            case SPEEDY: return new SpeedyAbility();
            default: return null;
        }
    }
}
