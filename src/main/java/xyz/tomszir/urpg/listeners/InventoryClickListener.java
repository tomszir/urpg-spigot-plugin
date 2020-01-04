package xyz.tomszir.urpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.tomszir.urpg.menu.MenuHolder;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (!(event.getInventory().getHolder() instanceof MenuHolder)) return;

        event.setCancelled(true);

        MenuHolder holder = (MenuHolder) event.getInventory().getHolder();
        holder.getMenu().onInventoryClick(event);
    }
}
