package xyz.tomszir.urpg.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import xyz.tomszir.urpg.managers.player.PlayerFile;
import xyz.tomszir.urpg.uRPG;
import xyz.tomszir.urpg.util.FormulaUtil;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            PlayerFile file = uRPG.getInstance().getPlayerManager().getPlayerFile(player.getUniqueId());

            double defense = file.getDefense();
            double health = file.getHealth();
            double maxHealth = file.getMaxHealth();

            double damage = event.getDamage();
            double finalDamage = damage * FormulaUtil.getDamageReductionFromDefense(defense);

            health = health - finalDamage;

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Math.ceil(health) + " / " + maxHealth + " HP"));

            if (health > 0) {
                if (health < maxHealth) {
                    player.setHealth(20 * (health / maxHealth));
                    player.setNoDamageTicks(20);

                    event.setCancelled(true);
                } else {
                    event.setDamage(0);
                }

                file.setHealth(health);
                file.save();
            } else {
                player.setHealth(0);
            }
        }
    }
}
