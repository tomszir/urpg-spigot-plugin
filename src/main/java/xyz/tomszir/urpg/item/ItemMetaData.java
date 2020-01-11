package xyz.tomszir.urpg.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.JSONObject;
import xyz.tomszir.urpg.___old.util.LoreEncoder;

import java.util.List;

public class ItemMetaData {

    // TODO: Write JavaDocs.

    /**
     * ItemMetaData:
     *  identifier - The unique identifier of an item.
     *  soulbound - Is the item soulbound.
     *  modifiers[] - The modifiers attached to the item.
     *      identifier - The modifiers's identifier.
     *      values: [min, max] - The rolled values of the modifier.
     *  curses[] - The curses attached to the item.
     */

    private JSONObject meta;

    public ItemMetaData(JSONObject meta) {
        this.meta = meta;
    }

    public static ItemMetaData fromItemStack(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();

        if (meta == null)
            return null;

        List<String> lore = meta.getLore();

        if (lore == null)
            return null;

        return ItemMetaData.fromLore(lore);
    }

    public static ItemMetaData fromLore(List<String> lore) {
        // Get the last line of lore.
        String encodedLine = lore.get(lore.size() - 1).split(" ")[1];

        // Decode the hidden data of the lore.
        JSONObject meta = new JSONObject(LoreEncoder.decode(encodedLine));

        return new ItemMetaData(meta);
    }

    public String toString() {
        return meta.toString();
    }

    public String getIdentifier() {
        return meta.getString("identifier");
    }
}
