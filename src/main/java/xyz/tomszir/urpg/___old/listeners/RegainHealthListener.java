package xyz.tomszir.urpg.___old.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;

public class RegainHealthListener implements Listener {

    private Main plugin = Main.getInstance();

    @EventHandler
    public void onRegainHealthEvent(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            CustomPlayer player = plugin.getPlayerManager().getPlayer(event.getEntity().getUniqueId());

            double health = player.getStats().getCurrentHealth();
            double maxHealth = player.getStats().getMaxHealth();
            double regainedHealth = maxHealth + event.getAmount() * 2;

            player.setHealth(Math.min(health + regainedHealth, maxHealth));
        }
    }
}
