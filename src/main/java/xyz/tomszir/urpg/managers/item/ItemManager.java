package xyz.tomszir.urpg.managers.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.reflections.Reflections;
import xyz.tomszir.urpg.items.BaseItem;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.uRPG;
import xyz.tomszir.urpg.util.LoreEncoderUtil;

import java.util.HashMap;
import java.util.List;

public class ItemManager {

    private uRPG plugin = uRPG.getInstance();

    private HashMap<String, BaseItem> items;

    public ItemManager() {
        this.items = new HashMap<>();

        registerItems();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                uRPGPlayer player = plugin.getPlayerManager().getPlayer(onlinePlayer.getUniqueId());
                List<EquippableItem> equipped = player.getEquippedList();

                for (EquippableItem item : equipped)
                    if (item != null)
                        item.onRapidPassiveEffect(player);
            }
        }, 0L, 20L);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                uRPGPlayer player = plugin.getPlayerManager().getPlayer(onlinePlayer.getUniqueId());
                List<EquippableItem> equipped = player.getEquippedList();

                for (EquippableItem item : equipped)
                    if (item != null)
                        item.onPassiveEffect(player);
            }
        }, 0L, 20L * 5);
    }

    public void addItem(BaseItem item) {
        items.put(item.getIdentifier(), item);
    }

    public BaseItem getItem(String identifier) {
        return items.get(identifier);
    }

    @SuppressWarnings("ALL")
    public BaseItem getItemWithoutIdentifier(ItemStack item) {
        if (item == null || item.getType() == Material.AIR)
            return null;

        List<String> lore = item.getItemMeta().getLore();

        if (lore == null)
            return null;

        String identifier = LoreEncoderUtil.decode(lore.get(lore.size() - 1).split(" ")[1]);

        return getItem(identifier);
    }

    public HashMap<String, BaseItem> getItems() {
        return items;
    }

    @SuppressWarnings("ALL")
    public void registerItems() {
        Reflections equippables = new Reflections("xyz.tomszir.urpg.items.equippables");

        try {
            for (Class<? extends EquippableItem> cls : equippables.getSubTypesOf(EquippableItem.class))
                addItem(cls.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
