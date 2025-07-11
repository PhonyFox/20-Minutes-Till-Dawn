package com.tilldawn.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Random {
    private Random() {
    }

    public static List<String> getRandomPowerUps() {
        List<String> powerUps = new ArrayList<>(Arrays.asList(
            "VITALITY",
            "DAMAGER",
            "PROCREASE",
            "AMOCREASE",
            "SPEEDY"
        ));

        Collections.shuffle(powerUps);
        return powerUps.subList(0, 3);
    }

    public static int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static double rand(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max + Double.MIN_VALUE);
    }

    public static long rand(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static float rand() {
        return ThreadLocalRandom.current().nextFloat(-0.5f, 0.5f);
    }
}
