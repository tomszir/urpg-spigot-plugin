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
    @EventHandler
    @SuppressWarnings("ALL")
    public void onPlayerMove(PlayerMoveEvent event) {
        Player eventPlayer = event.getPlayer();
        uRPGPlayer player = uRPG.getInstance().getPlayerManager().getPlayer(eventPlayer.getUniqueId());

        List<EquippableItem> equipped = player.getEquippedList();

        // Run the event for every equipped item;
        for (EquippableItem item : equipped)
            if (item != null)
                item.onPlayerMove(event, player);

        player.updateEquipped();
    }
}
