package xyz.tomszir.urpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.uRPG;
import xyz.tomszir.urpg.util.FormulaUtil;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);

            // Uses the NEW health instead of the old one.
            Player eventPlayer = (Player) event.getEntity();
            uRPGPlayer harmedPlayer = uRPG.getInstance().getPlayerManager().getPlayer(eventPlayer.getUniqueId());

            double defense = harmedPlayer.getStats().getDefense();
            double health = harmedPlayer.getStats().getCurrentHealth();

            double eventDamage = event.getDamage();
            double resultingDamage = Math.round(eventDamage * FormulaUtil.getDamageReductionFromDefense(defense) * 100) / 100.0;

            uRPG.getInstance().debug("" + event.getCause());

            // Limit damage rate on Lava & Cactus;
            // TODO: Test more edge cases and add them.
            if (event.getCause() == EntityDamageEvent.DamageCause.LAVA
                || event.getCause() == EntityDamageEvent.DamageCause.FIRE
                || event.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                if (event.getCause() == EntityDamageEvent.DamageCause.LAVA
                    || event.getCause() == EntityDamageEvent.DamageCause.FIRE)
                    eventPlayer.setLastDamage(eventPlayer.getLastDamage() + 0.05);
                if (event.getCause() == EntityDamageEvent.DamageCause.CONTACT)
                    eventPlayer.setLastDamage(eventPlayer.getLastDamage() + 0.1);

                if (eventPlayer.getLastDamage() < 1)
                    return;

                uRPG.getInstance().debug("Last:"  +eventPlayer.getLastDamage());

                if (eventPlayer.getLastDamage() > 1)
                    eventPlayer.setLastDamage(0);
            }

            harmedPlayer.damage(resultingDamage);
        }
    }
}
