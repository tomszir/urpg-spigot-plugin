package xyz.tomszir.urpg.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.tomszir.urpg.enums.ItemRarity;
import xyz.tomszir.urpg.util.ColorUtil;
import xyz.tomszir.urpg.util.LoreEncoderUtil;

import java.util.List;

public class BaseItem {

    private String identifier;

    private String name;
    private String description;

    private Material material;
    private ItemRarity rarity = ItemRarity.COMMON;

    public BaseItem(String identifier, Material material) {
        this.identifier = identifier;
        this.material = material;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
}

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setRarity(ItemRarity rarity) {
        this.rarity = rarity;
    }

    public ItemRarity getRarity() {
        return rarity;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack getItemStack() {
        return getItemStack(1);
    }

    public ItemStack getItemStack(int amount) {
        ItemStack stack = new ItemStack(material, amount);
        ItemMeta meta = stack.getItemMeta();

        if (meta == null)
            return stack;

        String rarityBadge = ColorUtil.formatColor(ColorUtil.RESET + rarity.getColor() + ColorUtil.BOLD + rarity.getLabel() + " ");

        meta.setDisplayName(ColorUtil.formatColor(ColorUtil.RESET + rarity.getColor() + name));
        meta.setLore(List.of(rarityBadge + LoreEncoderUtil.encode(identifier)));
        stack.setItemMeta(meta);

        return stack;
    }
}
