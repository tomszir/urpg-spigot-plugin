package xyz.tomszir.urpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import xyz.tomszir.urpg.managers.player.PlayerFile;
import xyz.tomszir.urpg.uRPG;

public class RegainHealthListener implements Listener {
    @EventHandler
    public void onRegainHealthEvent(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            PlayerFile file = uRPG.getInstance().getPlayerManager().getPlayerFile(player.getUniqueId());

            double health = file.getHealth();
            double maxHealth = file.getMaxHealth();

            health += maxHealth * (event.getAmount() / 20.0);
            health = Math.min(health, maxHealth);

            player.setHealth(health / 20.0);
            file.setHealth(health);
            file.save();
        }
    }
}
