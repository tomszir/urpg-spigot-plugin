package xyz.tomszir.urpg.___old.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.tomszir.urpg.___old.enums.ArmorEquipType;
import xyz.tomszir.urpg.___old.events.ArmorEquipEvent;
import xyz.tomszir.urpg.___old.items.Equippable;
import xyz.tomszir.urpg.___old.util.ArmorEquipUtil;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            Material itemInHandMaterial = itemInHand.getType();

            if (Equippable.EQUIPPABLES.contains(itemInHandMaterial)) {
                int slot = Equippable.getEquipSlot(itemInHandMaterial);
                ItemStack currentlyEquipped = player.getInventory().getItem(slot);

                if (currentlyEquipped == null || currentlyEquipped.getType() == Material.AIR) {
                    ArmorEquipEvent armorEvent = ArmorEquipUtil.runEvent(
                            new ArmorEquipEvent(player, itemInHand, ArmorEquipType.EQUIPPED), slot);

                    if (armorEvent.isCancelled()) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}