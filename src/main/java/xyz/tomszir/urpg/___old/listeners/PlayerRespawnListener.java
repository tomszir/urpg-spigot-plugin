package xyz.tomszir.urpg.___old.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        CustomPlayer player = Main.getInstance().getPlayerManager().getPlayer(event.getPlayer().getUniqueId());
        player.setHealth(player.getFile().getMaxHealth());
    }
}
