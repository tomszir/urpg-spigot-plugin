package xyz.tomszir.urpg.item;

import org.bukkit.ChatColor;

/**
 * The rarity of an item.
 * <li>{@link #COMMON}</li>
 * <li>{@link #UNCOMMON}</li>
 * <li>{@link #UNFAMILAIR}</li>
 * <li>{@link #RARE}</li>
 * <li>{@link #LEGENDARY}</li>
 * <li>{@link #MYTHIC}</li>
 */
public enum ItemRarity {
    /**
     * Common item rarity.
     */
    COMMON("Common", ChatColor.WHITE),

    /**
     * Uncommon item rarity.
     */
    UNCOMMON("Uncommon", ChatColor.GREEN),

    /**
     * Unfamiliar item rarity.
     */
    UNFAMILAIR("Unfamiliar", ChatColor.BLUE),

    /**
     * Rare item rarity.
     */
    RARE("Rare", ChatColor.AQUA),

    /**
     * Legendary item rarity.
     */
    LEGENDARY("Legendary", ChatColor.GOLD),

    /**
     * Mythic item rarity.
     */
    MYTHIC("Mythic", ChatColor.LIGHT_PURPLE);

    /**
     * The label of this item rarity, shows up in org.bukkit.ItemStack lore of an item.
     */
    private String label;

    /**
     * The color of this item rarity. Colors the name of an item & the item rarity's label.
     */
    private ChatColor color;

    /**
     * Enum constructor.
     *
     * @param label the label you want this item rarity to have.
     * @param color the color you want this item rarity to have.
     */
    ItemRarity(String label, ChatColor color) {
        this.label = label;
        this.color = color;
    }

    /**
     * Gets the item rarity as a string. The label is colored and put into uppercase.
     *
     * @return the item rarity as a string.
     */
    public String toString() {
        return color + "" + ChatColor.BOLD + "" + label.toUpperCase();
    }

    /**
     * Gets the label of this item rarity.
     *
     * @return this item rarity's label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the color of this item rarity.
     *
     * @return this item rarity's color.
     */
    public ChatColor getColor() {
        return color;
    }
}
