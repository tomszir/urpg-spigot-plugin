package xyz.tomszir.urpg.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.tomszir.urpg.enums.ArmorEquipType;
import xyz.tomszir.urpg.events.ArmorEquipEvent;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.util.ArmorEquipUtil;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            Material itemInHandMaterial = itemInHand.getType();

            if (EquippableItem.EQUIPPABLES.contains(itemInHandMaterial)) {
                int slot = EquippableItem.getEquipSlot(itemInHandMaterial);
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