package xyz.tomszir.urpg.___old.interfaces;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;

public interface PlayerEventListener {
    void onPlayerMove(PlayerMoveEvent event, CustomPlayer player);
    void onDamageReceived(EntityDamageEvent event, CustomPlayer player);
    void onPassiveEffect(CustomPlayer player);
    void onRapidPassiveEffect(CustomPlayer player);
}
