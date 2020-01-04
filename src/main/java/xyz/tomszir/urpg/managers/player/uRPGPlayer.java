package xyz.tomszir.urpg.managers.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class uRPGPlayer {

    private Player player;
    private PlayerFile file;

    public uRPGPlayer(UUID uuid, PlayerFile file) {
        this.file = file;
        this.player = Bukkit.getServer().getPlayer(uuid);
    }

    public void setHealth(double health) {
        if (health > 0) {
            file.setHealth(health);
            file.save();
        } else {
            file.setHealth(file.getMaxHealth());
        }

        // TODO: Finish this method.

        updateHealth();
    }

    public void updateHealth() {
        double health = file.getHealth();
        double maxHealth = file.getMaxHealth();

        // Sets "REAL" health between 0 and 20, based on "FAKE" health;
        player.setHealth(20.0 * Math.max(Math.min(health / maxHealth, 1), 0));
    }
}
