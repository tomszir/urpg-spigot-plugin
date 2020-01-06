package xyz.tomszir.urpg.menu.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.tomszir.urpg.events.MenuItemClickEvent;
import xyz.tomszir.urpg.menu.MenuItem;

public class AddMenuItem extends MenuItem {
    public AddMenuItem(ItemStack stack) {
        super(stack);
    }

    public AddMenuItem(String name, Material material) {
        super(name, material);
    }

    @Override
    public void onClick(MenuItemClickEvent event) {
        event.getPlayer().getInventory().addItem(getItemStack(event.getPlayer()));
    }
}
