package com.tilldawn.model;

import com.tilldawn.Main;

public enum GamaText {
    // Power-ups
    POWER_VITALITY("🩸 VITALITY:\n  Increases max HP by 1 point permanently.",
        "🩸 VITALITY:\n  Erhöht dauerhaft die maximale HP um 1 Punkt."),
    POWER_DAMAGER("💥 DAMAGER:\n  Boosts weapon damage by 25% for 10 seconds.",
        "💥 DAMAGER:\n  Erhöht den Waffenschaden für 10 Sekunden um 25 %."),
    POWER_PROCREASE("🎯 PROCREASE:\n  Adds 1 extra projectile to your weapon.",
        "🎯 PROCREASE:\n  Fügt deiner Waffe 1 zusätzliches Projektil hinzu."),
    POWER_AMOCREATE("🔫 AMOCREASE:\n  Increases max ammo by 5.",
        "🔫 AMOCREASE:\n  Erhöht die maximale Munition um 5."),
    POWER_SPEEDY("⚡ SPEEDY:\n  Doubles player movement speed for 10 seconds.",
        "⚡ SPEEDY:\n  Verdoppelt die Bewegungsgeschwindigkeit des Spielers für 10 Sekunden."),

    // Authentication
    ERROR_USERNAME_TAKEN("Username already taken!",
        "Benutzername bereits vergeben!"),
    ERROR_WEAK_PASSWORD("Weak password! It must have 8+ chars, 1 number, 1 uppercase, 1 symbol.",
        "Schwaches Passwort! Es muss mindestens 8 Zeichen, 1 Zahl, 1 Großbuchstaben und 1 Symbol enthalten."),
    REGISTER_QUESTION_PET("What is your pet",
        "Wie heißt dein Haustier"),
    REGISTER_QUESTION_FAVORITE("What is your favorite",
        "Was ist dein Lieblings"),
    REGISTER_QUESTION_CITY("What city were you born",
        "In welcher Stadt bist du geboren"),
    REGISTER_CHOOSE_QUESTION("Choose a Security Question!",
        "Wähle eine Sicherheitsfrage!"),
    REGISTER_ANSWER_QUESTION("Answer the Security Question!",
        "Beantworte die Sicherheitsfrage!"),
    REGISTER_SUCCESS("User successfully registered!",
        "Benutzer erfolgreich registriert!"),
    RESET_SUCCESS("Password reset successful!",
        "Passwort erfolgreich zurückgesetzt!"),
    ERROR_WRONG_ANSWER("Incorrect security answer!",
        "Falsche Sicherheitsantwort!"),
    ERROR_USER_NOT_FOUND("Username does not exist!",
        "Benutzername existiert nicht!"),
    LOGIN_SUCCESS("Login successfully!",
        "Erfolgreich eingeloggt!"),
    ERROR_INCORRECT_PASSWORD("Incorrect password!",
        "Falsches Passwort!"),

    // Hero descriptions
    HERO_SHANA("Shana is a balanced fighter with equal speed and HP.\nShe excels in duels and close-range combat.\nPerfect for beginners who want stability.",
        "Shana ist eine ausgewogene Kämpferin mit gleicher Geschwindigkeit und HP.\nSie glänzt in Duellen und im Nahkampf.\nPerfekt für Anfänger, die Stabilität möchten."),
    HERO_DIAMOND("Diamond is a tank with very high HP.\nThough slow, she can endure massive damage.\nIdeal for defensive play styles and support roles.",
        "Diamond ist ein Tank mit sehr hoher HP.\nObwohl langsam, kann sie enormen Schaden einstecken.\nIdeal für defensive Spielstile und Support-Rollen."),
    HERO_SCARLET("Scarlet is extremely fast but fragile.\nShe relies on hit-and-run tactics and agility.\nBest used by skilled players with quick reflexes.",
        "Scarlet ist extrem schnell, aber zerbrechlich.\nSie setzt auf Hit-and-Run-Taktiken und Agilität.\nAm besten für geübte Spieler mit schnellen Reflexen."),
    HERO_LILITH("Lilith is well-balanced with a focus on attack.\nHer moderate stats allow for strategic versatility.\nGood for mid-range combat and adaptive roles.",
        "Lilith ist gut ausbalanciert mit Schwerpunkt auf Angriff.\nIhre mittleren Werte erlauben strategische Vielseitigkeit.\nGut für Mittelstreckenkämpfe und adaptive Rollen."),
    HERO_DASHER("Dasher is lightning-fast but very vulnerable.\nHe is excellent at flanking and escaping danger.\nPerfect for advanced players seeking high risk-reward.",
        "Dasher ist blitzschnell, aber sehr verletzlich.\nEr eignet sich hervorragend zum Flankieren und Entkommen aus Gefahr.\nPerfekt für fortgeschrittene Spieler, die hohes Risiko gegen hohe Belohnung tauschen."),

    // Game results
    BACK_MAIN_MENU("Back to Main Menu", "Zurück zum Hauptmenü"),
    SCORE_LABEL("Score:", "Punkte:"),
    KILLS_LABEL("Kills:", "Kills:"),
    TIME_SURVIVED("Time Survived:", "Überlebenszeit:"),
    LABEL_USERNAME("Username:", "Benutzername:"),
    STATUS_WIN("WIN!", "SIEG!"),
    STATUS_DEAD("DEAD!", "TOT!"),
    TITLE_MAIN("title", "Titel"),

    // Register page
    REGISTER_SUCCESS_TOAST("registered successfully", "erfolgreich registriert"),
    REGISTER_QUESTION_PET_FULL("What is your pet's name?", "Wie heißt dein Haustier?"),
    REGISTER_QUESTION_COLOR("What is your favorite color?", "Was ist deine Lieblingsfarbe?"),
    REGISTER_QUESTION_CITY_FULL("what city were you born in?", "In welcher Stadt bist du geboren?"),

    // Login/Register UI
    BUTTON_REGISTER("Register", "Registrieren"),
    BUTTON_GUEST("Guest", "Gast"),
    INPUT_USERNAME("Username", "Benutzername"),
    INPUT_PASSWORD("Password", "Passwort"),
    INPUT_SECURITY("Security", "Sicherheit"),
    INPUT_ANSWER("Answer", "Antwort"),

    // Settings
    LABEL_MUSIC_VOLUME("Music Volume", "Musiklautstärke"),
    LABEL_CURRENT_MUSIC("Current Music", "Aktuelle Musik"),
    MUSIC_DEFAULT("default", "Standard"),
    MUSIC_BATTLE("battle", "Kampf"),
    MUSIC_CALM("calm", "ruhig"),
    MUSIC_MENU("menu", "Menü"),
    SETTING_SFX("Enable SFX", "SFX aktivieren"),
    SETTING_AUTORELOAD("Auto Reload", "Automatisches Nachladen"),
    SETTING_GRAYSCALE("Grayscale Mode", "Graustufenmodus"),
    BUTTON_BACK("Back", "Zurück"),

    // Scoreboard
    SCOREBOARD_TITLE("Scoreboard", "Bestenliste"),
    SCOREBOARD_RANK("Rank", "Rang"),
    SCOREBOARD_USERNAME("Username", "Benutzername"),
    SCOREBOARD_SCORE("Score", "Punktzahl"),
    SCOREBOARD_KILLS("Kills", "Kills"),
    SCOREBOARD_TIME("Survival Time", "Überlebenszeit"),
    SCOREBOARD_SORT_SCORE("Sort by Score", "Nach Punktzahl sortieren"),
    SCOREBOARD_SORT_USERNAME("Sort by Username", "Nach Benutzername sortieren"),
    SCOREBOARD_SORT_KILLS("Sort by Kills", "Nach Kills sortieren"),
    SCOREBOARD_SORT_TIME("Sort by Survival Time", "Nach Überlebenszeit sortieren"),
    BUTTON_CHANGE("Change", "Ändern"),
    MSG_USERNAME_TAKEN("Username already taken!", "Benutzername bereits vergeben!"),
    MSG_USERNAME_UPDATED("Username updated!", "Benutzername aktualisiert!"),
    INPUT_NEW_PASSWORD("New Password", "Neues Passwort"),
    MSG_PASSWORD_WEAK("Password too weak!", "Passwort zu schwach!"),
    MSG_PASSWORD_CHANGED("Password changed!", "Passwort geändert!"),
    BUTTON_DELETE_ACCOUNT("Delete Account", "Konto löschen"),
    MSG_ACCOUNT_DELETED("Account deleted.", "Konto gelöscht."),
    LABEL_AVATAR("Avatar", "Avatar"),
    BUTTON_CHOOSE_AVATAR("Choose Avatar", "Avatar wählen"),
    OPTION_LOCAL_FILES("From Local Files", "Aus lokalen Dateien"),
    MSG_AVATAR_CHANGED("Avatar changed!", "Avatar geändert!"),

    // Game start
    MENU_SELECT_HERO("Select Hero", "Held auswählen"),
    MENU_SELECT_WEAPON("Select Weapon", "Waffe auswählen"),
    MENU_SELECT_DURATION("Select Duration", "Dauer auswählen"),
    BUTTON_START_GAME("Start Game", "Spiel starten"),
    MSG_STARTING_GAME("Starting game with hero:", "Spiel wird gestartet mit Held:"),
    MSG_SELECT_ALL("Please select all options", "Bitte alle Optionen auswählen"),
    BUTTON_RESUME("Resume", "Fortsetzen"),
    BUTTON_CHEATS("Cheat Codes", "Cheat-Codes"),
    BUTTON_ABILITIES("Abilities", "Fähigkeiten"),
    TOGGLE_GRAYSCALE("Toggle Grayscale", "Graustufen wechseln"),
    BUTTON_GIVEUP("Give Up", "Aufgeben"),
    BUTTON_SAVE_EXIT("Save & Exit", "Speichern & Beenden"),

    // Cheat codes
    CHEAT_MINUS_ONE_MINUTE("Subtracts 1 minute from the game timer.",
        "Zieht 1 Minute vom Spieltimer ab."),
    CHEAT_INSTANT_XP("Instantly levels up the player by giving extra XP",
        "Erhöht das Level des Spielers sofort durch zusätzliche XP"),
    CHEAT_HEAL_ONE("Heals the player by 1 HP (only if not at full health)",
        "Heilt den Spieler um 1 HP (nur wenn nicht volle Gesundheit)"),
    CHEAT_MATCH_DURATION("Sets the game timer to match the user's duration purpose.",
        "Setzt den Spieltimer auf die gewünschte Dauer des Benutzers."),
    CHEAT_FULL_HEAL("Fully heals the player to max HP.",
        "Heilt den Spieler vollständig auf maximale HP."),

    // Main Menu
    MENU_OK("OK", "OK"),
    MENU_CONTINUE("Continue", "Fortsetzen"),
    MENU_SETTINGS("Settings", "Einstellungen"),
    MENU_PROFILE("Profile", "Profil"),
    MENU_GAMEPRO("Game Pro", "Spiel Pro"),
    MENU_HINTS("Hints", "Tipps"),
    BUTTON_LOGOUT("Logout", "Abmelden"),
    BUTTON_LOGIN("Login", "Anmelden"),
    LINK_FORGOT_PASSWORD("Forgot password", "Passwort vergessen"),

    // Password reset
    RESET_USERNAME("Username:", "Benutzername:"),
    RESET_PASSWORD("Password:", "Passwort:"),
    RESET_NEW_PASSWORD("New Password:", "Neues Passwort:"),
    RESET_SECURITY_Q("Security Q:", "Sicherheitsfrage:"),
    RESET_ANSWER("Answer:", "Antwort:"),

    // Hints and keys
    HINT_HERO("Hero Hints", "Helden-Tipps"),
    HINT_ABILITIES("Ability Hints", "Fähigkeiten-Tipps"),
    MENU_CHEATS("Cheats", "Cheats"),
    MENU_KEY_BINDING("Key Binding", "Tastenbelegung"),
    LABEL_ABILITIES("Abilities", "Fähigkeiten"),
    LABEL_CHEATS("Cheat Codes", "Cheat-Codes"),
    LABEL_KEYBINDING("Key Binding", "Tastenbelegung"),
    BUTTON_CLOSE("Close", "Schließen"),
    MSG_SELECT_IMAGE("Select an Image File", "Bilddatei auswählen");

    private final String english;
    private final String german;

    GamaText(String english, String german) {
        this.english = english;
        this.german = german;
    }

    private String getEnglish() {
        return english;
    }

    private String getGerman() {
        return german;
    }

    public String get() {
        if (Main.getMain().getIsGerman()) {
            return getGerman();
        } else {
            return getEnglish();
        }
    }
}
