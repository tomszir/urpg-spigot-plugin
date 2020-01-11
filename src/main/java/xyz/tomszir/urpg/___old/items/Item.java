package xyz.tomszir.urpg.___old.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.JSONObject;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.enums.ItemRarity;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;
import xyz.tomszir.urpg.___old.util.ColorUtil;
import xyz.tomszir.urpg.___old.util.LoreEncoder;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private ItemData _data;
    private String identifier;

    private String name;
    private String description;

    private Material material;
    private ItemRarity rarity = ItemRarity.COMMON;

    public Item(String identifier, Material material) {
        this.identifier = identifier;
        this.material = material;
    }

    public ItemData getItemData() {
        if (_data == null)
            return new ItemData(
                new JSONObject()
                    .put("Identifier", getIdentifier()));
        return _data;
    }

    public void setItemData(ItemData itemData) {
        this._data = itemData;
    }

    public String getIdentifier() {
        return "ITEM_" + identifier;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return getItemData().getNickname();
    }

    public String getDisplayName() {
        String name = getNickname();

        if (name == null)
            name = getName();

        return ColorUtil.parse(getRarity().getColor() + name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemRarity getRarity() {
        return rarity;
    }

    public void setRarity(ItemRarity rarity) {
        this.rarity = rarity;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<String> getLore(CustomPlayer player) {
        List<String> lore = new ArrayList<>();
        String data = LoreEncoder.encode(getItemData().toString());

        if (getDescription() != null)
            lore.add("&7" + getDescription());
        lore.add("");
        lore.add(getRarity().toString() + " " + data);

        return lore;
    }

    public ItemStack getItemStack(CustomPlayer player) {
        return getItemStack(player, 1);
    }

    public ItemStack getItemStack(CustomPlayer player, int amount) {
        ItemStack stack = new ItemStack(getMaterial(), amount);
        ItemMeta meta = stack.getItemMeta();

        if (meta == null)
            return stack;

        meta.setDisplayName(getDisplayName());
        meta.setLore(getLore(player));

        stack.setItemMeta(meta);

        return stack;
    }

    public static ItemStack updateItemStack(CustomPlayer player, ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();

        if (meta == null)
            return null;

        Item item = Main.getInstance().getItemManager().getItemWithoutIdentifier(stack);

        return item.getItemStack(player, stack.getAmount());
    }
}
