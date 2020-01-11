package xyz.tomszir.urpg.player;

public enum PlayerFileField {

    // TODO: Create JavaDoc.

    LEVEL("level"),

    EXPERIENCE("experience"),

    SAVED_HEALTH("saved-health"),

    STRENGTH("attributes.strength"),

    DEXTERITY("attributes.dexterity"),

    INTELLIGENCE("attributes.intelligence"),

    CONSTITUTION("attributes.constitution");

    private String key;

    PlayerFileField(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
