package xyz.tomszir.urpg.util;

import org.bukkit.Bukkit;
import xyz.tomszir.urpg.events.ArmorEquipEvent;

public class ArmorEquipUtil {
    public static ArmorEquipEvent runEvent(ArmorEquipEvent event, int slot) {
        event.setSlot(slot);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }
}
