package xyz.tomszir.urpg.___old.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MenuHolder implements InventoryHolder {

    private ItemMenu menu;
    private Inventory inventory;

    public MenuHolder(ItemMenu menu, Inventory inventory) {
        this.menu = menu;
        this.inventory = inventory;
    }

    public ItemMenu getMenu() {
        return menu;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
