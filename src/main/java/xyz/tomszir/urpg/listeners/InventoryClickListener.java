package xyz.tomszir.urpg.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import xyz.tomszir.urpg.enums.ArmorEquipType;
import xyz.tomszir.urpg.events.ArmorEquipEvent;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.menu.MenuHolder;
import xyz.tomszir.urpg.uRPG;
import xyz.tomszir.urpg.util.ArmorEquipUtil;

import java.util.List;

@SuppressWarnings("ALL")
public class InventoryClickListener implements Listener {

    private uRPG plugin = uRPG.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player))
            return;

        handleArmorEquip(event);

        if (event.getInventory().getHolder() instanceof MenuHolder)
            openItemMenu(event);
    }

    private void openItemMenu(InventoryClickEvent event) {
        event.setCancelled(true);

        MenuHolder holder = (MenuHolder) event.getInventory().getHolder();
        holder.getMenu().onInventoryClick(event);
    }

    private void handleArmorEquip(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        ItemStack cursor = event.getCursor();
        ItemStack current = event.getCurrentItem();

        ClickType click = event.getClick();
        InventoryType.SlotType type = event.getSlotType();

        // Shift click from inventory;
        if (List.of(ClickType.SHIFT_LEFT, ClickType.SHIFT_RIGHT).contains(click)) {
            if (List.of(InventoryType.SlotType.CONTAINER, InventoryType.SlotType.QUICKBAR).contains(type)) {
                int slot = EquippableItem.getEquipSlot(current.getType());

                if (slot > -1) {
                    ItemStack item = player.getInventory().getItem(slot);

                    if (item == null || item.getType() == Material.AIR) {
                        ArmorEquipEvent armorEvent = ArmorEquipUtil.runEvent(
                            new ArmorEquipEvent(player, current, ArmorEquipType.EQUIPPED), slot);

                        if (armorEvent.isCancelled()) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }

        if (type == InventoryType.SlotType.ARMOR) {
            // Stop glitching by spamming the ArmorSlot click
            if (click == ClickType.DOUBLE_CLICK) {
                event.setCancelled(true);
            }
            // Unequipping (by dropping with Q)
            else if (List.of(ClickType.DROP, ClickType.CONTROL_DROP).contains(click)) {
                ArmorEquipEvent armorEvent = ArmorEquipUtil.runEvent(
                    new ArmorEquipEvent(player, current, ArmorEquipType.UNEQUIPPED), event.getSlot());

                if (armorEvent.isCancelled()) {
                    event.setCancelled(true);
                }
            }
            // Regular click in an ArmorSlot
            else {
                // Equipping;
                if (cursor != null) {
                    if (cursor.getType() != Material.AIR) {
                        ArmorEquipEvent armorEvent = ArmorEquipUtil.runEvent(
                                new ArmorEquipEvent(player, cursor, ArmorEquipType.EQUIPPED), event.getSlot());

                        if (armorEvent.isCancelled()) {
                            event.setCancelled(true);
                        }
                    }
                }

                // Unequipping;
                if (current != null) {
                    if (current.getType() != Material.AIR) {
                        ArmorEquipEvent armorEvent = ArmorEquipUtil.runEvent(
                            new ArmorEquipEvent(player, current, ArmorEquipType.UNEQUIPPED), event.getSlot());

                        if (armorEvent.isCancelled()) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
