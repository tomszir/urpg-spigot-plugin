package xyz.tomszir.urpg.player;

import org.bukkit.entity.Player;
import xyz.tomszir.urpg.Main;

import java.util.UUID;

public class PluginPlayer {

    // TODO: Write JavaDoc.

    private static Main plugin = Main.getInstance();

    private Player bukkitPlayer;
    private PlayerFile file;
    private PlayerStats stats;
    private PlayerAttributes attributes;
    private PlayerEquipment equipment;

    public PluginPlayer(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;

        // Initializes, creates and loads the player file.
        this.file = new PlayerFile(this);

        // Initializes the player equipment object.
        this.equipment = new PlayerEquipment(this);

        // Initializes the player stat object.
        this.stats = new PlayerStats(this);

        // Initializes the player attribute object.
        this.attributes = new PlayerAttributes(this);
    }

    public UUID getUniqueId() {
        return bukkitPlayer.getUniqueId();
    }

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

    public PlayerFile getFile() {
        return file;
    }

    public PlayerEquipment getEquipment() {
        return equipment;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public PlayerAttributes getAttributes() {
        return attributes;
    }

    public void heal(double amount) {
        // Sets the players health equal to currentHealth + healAmount or to maxHealth (or 0 if the new health < 0).
        stats.setHealth(Math.max(Math.min(stats.getHealth() + amount, stats.getMaxHealth()), 0));

        // Updates the health bar to show the new health.
        updateHealthBar();
    }

    public void healInPrecentage(double percentage) {
        heal(stats.getMaxHealth() * (percentage / 100));
    }

    public void updateHealthBar() {
        // Calculates the amount of hearts to be shown in the health bar.
        double scaledHealth = Math.max(20.0 * (stats.getHealth() / stats.getMaxHealth()), 0);

        // Sets the player health to show the amount of hearts based on the health/maxHealth.
        bukkitPlayer.setHealth(scaledHealth);
    }

    public void displayHealthActionBar() {
        // TODO: Add the action bar.
    }
}
