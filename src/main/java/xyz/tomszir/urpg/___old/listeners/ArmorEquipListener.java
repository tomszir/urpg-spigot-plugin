package xyz.tomszir.urpg.___old.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.enums.ArmorEquipType;
import xyz.tomszir.urpg.___old.events.ArmorEquipEvent;
import xyz.tomszir.urpg.___old.items.Equippable;

public class ArmorEquipListener implements Listener {

    private Main plugin = Main.getInstance();

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent event) {
        Equippable equip = event.getEquippableItem();

        event.getBukkitPlayer().playSound(event.getBukkitPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 1, 1);

        if (equip == null)
            return;

        if (equip.canBeEquipped(event.getSlot())) {
            if (event.getEquipType() == ArmorEquipType.EQUIPPED)
                equip.onEquip(event.getPlayer());
            if (event.getEquipType() == ArmorEquipType.UNEQUIPPED)
                equip.onUnequip(event.getPlayer());
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            event.getPlayer().updateArmorBar();
        }, 5L);
    }
}
