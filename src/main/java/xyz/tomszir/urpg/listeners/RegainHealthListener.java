package xyz.tomszir.urpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.uRPG;

public class RegainHealthListener implements Listener {

    private uRPG plugin = uRPG.getInstance();

    @EventHandler
    public void onRegainHealthEvent(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            uRPGPlayer player = plugin.getPlayerManager().getPlayer(event.getEntity().getUniqueId());

            double health = player.getStats().getCurrentHealth();
            double maxHealth = player.getStats().getMaxHealth();
            double regainedHealth = maxHealth + event.getAmount() * 5;

            player.setHealth(Math.min(health + regainedHealth, maxHealth));
        }
    }
}
