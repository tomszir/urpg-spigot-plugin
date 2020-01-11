package xyz.tomszir.urpg.___old.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.tomszir.urpg.___old.Main;

public class PlayerJoinListener implements Listener {

    private Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayerManager().loadPlayerFile(player.getUniqueId());
    }
}
