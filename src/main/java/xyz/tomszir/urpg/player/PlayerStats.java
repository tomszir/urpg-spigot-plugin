package xyz.tomszir.urpg.player;

public class PlayerStats {

    // TODO: Write JavaDoc.

    private PluginPlayer player;

    public PlayerStats(PluginPlayer player) {
        this.player = player;

        // Initialize the player health store.
        PlayerHealth.setHealth(player.getUniqueId(), player.getFile().getSavedHealth());
    }

    public void setHealth(double health) {
        PlayerHealth.setHealth(player.getUniqueId(), health);
    }

    public double getHealth() {
        return PlayerHealth.getHealth(player.getUniqueId());
    }

    public double getMaxHealth() {
        return PlayerDefault.MAX_HEALTH;
    }

    public double getDamage() {
        return PlayerDefault.DAMAGE;
    }

    public double getAttackSpeed() {
        return PlayerDefault.ATTACK_SPEED;
    }

    public double getCriticalStrikeChance() {
        return PlayerDefault.CRITICAL_STRIKE_CHANCE;
    }

    public double getLifeSteal() {
        return PlayerDefault.LIFE_STEAL;
    }

    public double getLifeRegeneration() {
        return PlayerDefault.LIFE_REGENERATION;
    }
}
