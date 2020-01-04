package xyz.tomszir.urpg.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.tomszir.urpg.events.MenuItemClickEvent;

public class MenuItem {

    private String name;
    private Material material;

    public MenuItem(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public ItemStack getItemStack(Player player) {
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
