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
        return "- Fireball: is beautiful\n" + "- Heal: is lovely\n" + "- Shield: is shielding\n";
    }

    public String getCheatCodes() {
        return "- GODMODE: press G\n" + "- KILL ALL: press K";
    }

    public String getKeyBindings() {
        return "- Move Up: W\n" + "- Move Down: W\n" + "- Move Left: W\n" + "- Move Right: W\n";
    }
}
