package xyz.tomszir.urpg.___old.util;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemUtil {
    public static ItemStack colorLeatherItemStack(ItemStack stack, Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();
        meta.setColor(color);
        stack.setItemMeta(meta);
        return stack;
    }
}
