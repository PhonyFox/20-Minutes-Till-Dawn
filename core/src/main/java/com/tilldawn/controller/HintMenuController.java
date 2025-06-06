package com.tilldawn.controller;

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
            case "shana": description = "Shana is a balanced fighter with equal speed and HP.\n" +
                "She excels in duels and close-range combat.\n" +
                "Perfect for beginners who want stability."; break;
            case "diamond": description = "Diamond is a tank with very high HP.\n" +
                "Though slow, she can endure massive damage.\n" +
                "Ideal for defensive play styles and support roles."; break;
            case "scarlet": description = "Scarlet is extremely fast but fragile.\n" +
                "She relies on hit-and-run tactics and agility.\n" +
                "Best used by skilled players with quick reflexes."; break;
            case "lilith": description = "Lilith is well-balanced with a focus on attack.\n" +
                "Her moderate stats allow for strategic versatility.\n" +
                "Good for mid-range combat and adaptive roles."; break;
            case "dasher": description = "Dasher is lightning-fast but very vulnerable.\n" +
                "He is excellent at flanking and escaping danger.\n" +
                "Perfect for advanced players seeking high risk-reward."; break;
        }
        return description;
    }

    public String getAbilityDescription() {
        return
            "🩸 VITALITY:\n  Increases max HP by 1 point permanently.\n\n" +
                "💥 DAMAGER:\n  Boosts weapon damage by 25% for 10 seconds.\n\n" +
                "🎯 PROCREASE:\n  Adds 1 extra projectile to your weapon.\n\n" +
                "🔫 AMOCREASE:\n  Increases max ammo by 5.\n\n" +
                "⚡ SPEEDY:\n  Doubles player movement speed for 10 seconds.";

    }

    public String getCheatCodes() {
        return
            "🎮 Cheat Codes:\n\n" +
                "1️⃣  NUM_1:\n" +
                "   ⏱️ Subtracts 1 minute from the game timer.\n\n" +
                "2️⃣  NUM_2:\n" +
                "   ⭐ Instantly levels up the player by giving extra XP.\n\n" +
                "3️⃣  NUM_3:\n" +
                "   ❤️ Heals the player by 1 HP (only if not at full health).\n\n" +
                "4️⃣  NUM_4:\n" +
                "   🕓 Sets the game timer to match the user’s duration progress.\n\n" +
                "5️⃣  NUM_5:\n" +
                "   💯 Fully heals the player to max HP.";

    }

    public String getKeyBindings() {
        return "- Move Up: W\n" + "- Move Down: W\n" + "- Move Left: W\n" + "- Move Right: W\n";
    }
}
