package xyz.tomszir.urpg.___old.util;

import org.bukkit.Bukkit;
import xyz.tomszir.urpg.___old.events.ArmorEquipEvent;

public class ArmorEquipUtil {
    public static ArmorEquipEvent runEvent(ArmorEquipEvent event, int slot) {
        event.setSlot(slot);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }
}
