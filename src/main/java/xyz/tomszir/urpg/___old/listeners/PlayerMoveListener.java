package xyz.tomszir.urpg.___old.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.items.Equippable;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;

import java.util.List;

public class PlayerMoveListener implements Listener {

    private Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player eventPlayer = event.getPlayer();
        CustomPlayer player = plugin.getPlayerManager().getPlayer(eventPlayer.getUniqueId());

        List<Equippable> equipped = player.getEquippedList();

        for (Equippable item : equipped)
            if (item != null)
                item.onPlayerMove(event, player);

        player.updateEquipped();
    }
}
