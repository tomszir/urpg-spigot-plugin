package xyz.tomszir.urpg.___old.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.tomszir.urpg.___old.Main;

public class PlayerQuitListener implements Listener {

    private Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayerManager().removePlayerFile(player.getUniqueId());
    }
}
