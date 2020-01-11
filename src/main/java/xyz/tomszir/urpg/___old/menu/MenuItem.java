package xyz.tomszir.urpg.___old.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.tomszir.urpg.___old.events.MenuItemClickEvent;

public class MenuItem {

    private String name;
    private Material material;
    private ItemStack stack;

    public MenuItem(ItemStack stack) {
        this.stack = stack;
    }

    public MenuItem(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public void setBaseItemStack(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getBaseItemStack() {
        return stack;
    }

    public ItemStack getItemStack(Player player) {
        if (this.stack != null) return this.stack;

        ItemStack stack = new ItemStack(material, 1);
        ItemMeta meta = stack.getItemMeta();

        if (meta == null)
            return stack;

        meta.setDisplayName(name);
        stack.setItemMeta(meta);

        return stack;
    }

    public void onClick(MenuItemClickEvent event) {
        // ...
    }
}
