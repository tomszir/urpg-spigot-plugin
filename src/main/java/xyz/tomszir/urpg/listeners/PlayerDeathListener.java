package xyz.tomszir.urpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.tomszir.urpg.managers.player.PlayerFile;
import xyz.tomszir.urpg.uRPG;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PlayerFile file = uRPG.getInstance().getPlayerManager().getPlayerFile(player.getUniqueId());

        file.setHealth(file.getMaxHealth());
        file.save();
    }
}
