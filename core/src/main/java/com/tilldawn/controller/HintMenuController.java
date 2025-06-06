package com.tilldawn.controller;

import com.tilldawn.model.GamaText;
import com.tilldawn.model.Repository;
import com.tilldawn.model.User;

public class HintMenuController {
    private final Repository repo;
    private final User user;

    public HintMenuController(Repository repo) {
        this.repo = repo;
        this.user = repo.getCurrentUser();
    }

    public String[] getHeroNames() {
        return new String[]{"Shana", "Diamond", "Scarlet", "Lilith", "Dasher"};
    }

    public String getHeroDescription(String heroName) {
        String description = "";
        switch (heroName.toLowerCase().trim()) {
            case "shana": description = GamaText.HERO_SHANA.get(); break;
            case "diamond": description = GamaText.HERO_DIAMOND.get(); break;
            case "scarlet": description = GamaText.HERO_SCARLET.get(); break;
            case "lilith": description = GamaText.HERO_LILITH.get(); break;
            case "dasher": description = GamaText.HERO_DASHER.get(); break;
        }
        return description;
    }

    public String getAbilityDescription() {
        return
            GamaText.POWER_VITALITY.get() +
                GamaText.POWER_DAMAGER.get() +
                GamaText.POWER_PROCREASE.get() +
                GamaText.POWER_AMOCREATE.get() +
                GamaText.POWER_SPEEDY.get();

    }

    public String getCheatCodes() {
        return
            "🎮 Cheat Codes:\n\n" +
                "1️⃣  NUM_1:\n" +
                GamaText.CHEAT_MINUS_ONE_MINUTE.get() +
                "2️⃣  NUM_2:\n" +
                GamaText.CHEAT_INSTANT_XP.get() +
                "3️⃣  NUM_3:\n" +
                GamaText.CHEAT_HEAL_ONE.get() +
                "4️⃣  NUM_4:\n" +
                GamaText.CHEAT_MATCH_DURATION.get() +
                "5️⃣  NUM_5:\n" +
                GamaText.CHEAT_FULL_HEAL.get();

    }

    public String getKeyBindings() {
        return "- Move Up: W\n" + "- Move Down: W\n" + "- Move Left: W\n" + "- Move Right: W\n";
    }
}
