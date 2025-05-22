package com.tilldawn.model.ability;

public class TimedAbility {
    Ability ability;
    long startTime;

    public TimedAbility(Ability ability, long startTime) {
        this.ability = ability;
        this.startTime = startTime;
    }

    public Ability getAbility() {
        return ability;
    }

    public long getStartTime() {
        return startTime;
    }
}
