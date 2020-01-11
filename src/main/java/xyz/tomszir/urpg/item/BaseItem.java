package xyz.tomszir.urpg.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.JSONObject;
import xyz.tomszir.urpg.LoreEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * The base class of any item of this plugin.
 */
public class BaseItem {

    /**
     * The unique identifier of this item.
     */
    private String identifier;

    /**
     * The name of this item.
     */
    private String name;

    /**
     * The description of this item. Only describes the story behind the item.
     */
    private String description;

    /**
     * The org.bukkit.Material of this item.
     */
    private Material material = Material.DEAD_BUSH;

    /**
     * The item rarity of this item.
     */
    private ItemRarity rarity = ItemRarity.COMMON;

    /**
     * The item meta data of this item. Stores non-template data, that can differ from item to item.
     * Ex. modifiers, prefixes, suffixes, enchantments, etc.
     */
    private ItemMetaData itemMetaData;

    /**
     * Class constructor.
     *
     * @param identifier the unique identifier you want this item to have.
     */
    public BaseItem(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the unique identifier of this item.
     *
     * @return this item's unique identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the name of this item. It is set as the name of the org.bukkit.ItemStack of this item.
     *
     * @param name the name you want this item to have.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of this item.
     *
     * @return this item's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the description of this item. Only describes the story behind the item. Doesn't include it's stats, effects, etc.
     *
     * @param description the description you want this item to have.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the story description of this item.
     *
     * @return this item's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the item rarity of this item.
     *
     * @param rarity the item rarity you want this item to have.
     */
    public void setItemRarity(ItemRarity rarity) {
        this.rarity = rarity;
    }

    /**
     * Gets the item rarity of this item.
     *
     * @return this item's item rarity.
     */
    public ItemRarity getItemRarity() {
        return rarity;
    }

    /**
     * Sets the org.bukkit.Material of this item. It is used whenever creating a new org.bukkit.ItemStack.
     *
     * @param material the org.bukkit.Material you want this item to have.
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Gets the org.bukkit.Material of this item.
     * @return this item's org.bukkit.Material.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Creates the org.bukkit.ItemStack lore. Stores the ItemMetaData as ChatColor bytes in the lore.
     * Sets the item rarity label as the last line.
     *
     * @return the created lore.
     */
    public List<String> createItemStackLore() {
        List<String> lore = new ArrayList<>();

        if (description != null) {
            lore.add(description);
            lore.add("");
        }

        String rarityString = rarity.toString();
        String encodedItemMetaData = LoreEncoder.encode(getItemMetaData().toString());

        lore.add(rarityString + " " + encodedItemMetaData);

        return lore;
    }

    /**
     * Creates and returns the org.bukkit.ItemStack based on this item, with the default amount of 1.
     *
     * @return the created org.bukkit.ItemStack with the amount of 1.
     */
    public ItemStack createItemStack() {
        return createItemStack(1);
    }

    /**
     * Creates and returns the org.bukkit.ItemStack based on this item.
     *
     * @param amount the amount of items you want in the org.bukkit.ItemStack.
     * @return the created org.bukkit.ItemStack.
     */
    public ItemStack createItemStack(int amount) {
        ItemStack stack = new ItemStack(material, amount);
        ItemMeta meta = stack.getItemMeta();

        if (meta == null)
            return stack;

        meta.setLore(createItemStackLore());
        stack.setItemMeta(meta);

        return stack;
    }

    /**
     * Sets the item meta data of this item.
     *
     * @param itemMetaData the item meta data you want this item to have.
     */
    public void setItemMetaData(ItemMetaData itemMetaData) {
        this.itemMetaData = itemMetaData;
    }

    /**
     * Gets the item meta data of this item. It includes information about the item, like - identifier, modifiers, etc.
     *
     * @return this item's item meta data.
     */
    public ItemMetaData getItemMetaData() {
        if (itemMetaData == null)
            return new ItemMetaData(
                new JSONObject().put("identifier", getIdentifier())
            );
        return itemMetaData;
    }
}
