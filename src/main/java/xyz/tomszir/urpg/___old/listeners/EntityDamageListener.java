package xyz.tomszir.urpg.___old.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.items.Equippable;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;
import xyz.tomszir.urpg.___old.util.FormulaUtil;

import java.util.List;

public class EntityDamageListener implements Listener {

    private Main plugin = Main.getInstance();

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);

            Player eventPlayer = (Player) event.getEntity();
            CustomPlayer harmedPlayer = plugin.getPlayerManager().getPlayer(eventPlayer.getUniqueId());

            double defense = harmedPlayer.getStats().getDefense();
            // double health = harmedPlayer.getStats().getCurrentHealth();

            double eventDamage = event.getDamage();
            double resultingDamage = Math.round(eventDamage * FormulaUtil.getDamageReductionFromDefense(defense) * 100) / 100.0;

            EntityDamageEvent.DamageCause cause = event.getCause();

            for (Equippable item : harmedPlayer.getEquippedList())
                if (item != null)
                    item.onDamageReceived(event, harmedPlayer);

            if (List.of(EntityDamageEvent.DamageCause.LAVA, EntityDamageEvent.DamageCause.FIRE, EntityDamageEvent.DamageCause.CONTACT).contains(cause)) {
                if (EntityDamageEvent.DamageCause.CONTACT == cause) {
                    eventPlayer.setLastDamage(eventPlayer.getLastDamage() + 0.15);
                } else {
                    eventPlayer.setLastDamage(eventPlayer.getLastDamage() + 0.075);
                }

                if (eventPlayer.getLastDamage() < 1)
                    return;

                if (eventPlayer.getLastDamage() > 1)
                    eventPlayer.setLastDamage(0);
            }

            harmedPlayer.damage(resultingDamage);
        }
    }
}
