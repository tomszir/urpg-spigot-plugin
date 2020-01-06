package xyz.tomszir.urpg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.uRPG;

public class PlayerRespawnListener implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        uRPGPlayer player = uRPG.getInstance().getPlayerManager().getPlayer(event.getPlayer().getUniqueId());
        player.setHealth(player.getFile().getMaxHealth());
    }
}
