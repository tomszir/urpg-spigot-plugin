package xyz.tomszir.urpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.uRPG;

import java.util.List;

public class PlayerMoveListener implements Listener {

    private uRPG plugin = uRPG.getInstance();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player eventPlayer = event.getPlayer();
        uRPGPlayer player = plugin.getPlayerManager().getPlayer(eventPlayer.getUniqueId());

        List<EquippableItem> equipped = player.getEquippedList();

        for (EquippableItem item : equipped)
            if (item != null)
                item.onPlayerMove(event, player);

        player.updateEquipped();
    }
}
