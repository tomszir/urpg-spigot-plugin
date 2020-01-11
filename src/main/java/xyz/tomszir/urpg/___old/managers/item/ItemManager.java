package xyz.tomszir.urpg.___old.managers.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.reflections.Reflections;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.items.Equippable;
import xyz.tomszir.urpg.___old.items.Item;
import xyz.tomszir.urpg.___old.items.ItemData;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;

import java.util.HashMap;
import java.util.List;

public class ItemManager {

    // Update Passive Effects every X seconds
    public static int PASSIVE_EFFECT_UPDATE_TIME = 5;

    private Main plugin = Main.getInstance();

    private int schedulerTime = 1;
    private HashMap<String, Item> items;

    public ItemManager() {
        this.items = new HashMap<>();

        register();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                CustomPlayer player = plugin.getPlayerManager().getPlayer(onlinePlayer.getUniqueId());
                List<Equippable> equipped = player.getEquipment().getAll();

                for (Equippable item : equipped) {
                    if (item == null) return;
                    if (schedulerTime == PASSIVE_EFFECT_UPDATE_TIME)
                        item.onPassiveEffect(player);
                    item.onRapidPassiveEffect(player);
                }

                schedulerTime = schedulerTime < PASSIVE_EFFECT_UPDATE_TIME ? schedulerTime + 1 : 1;
            }
        }, 0L, 20L);
    }

    public void register() {
        Reflections equippables = new Reflections("xyz.tomszir.urpg.old.items.equippables");

        try {
            for (Class<? extends Equippable> cls : equippables.getSubTypesOf(Equippable.class))
                addItem(cls.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addItem(Item item) {
        items.put(item.getIdentifier(), item);
    }

    public Item getItem(String identifier) {
        return items.get(identifier);
    }

    public Item getItemWithoutIdentifier(ItemStack stack) {
        if (stack == null || stack.getType() == Material.AIR)
            return null;

        ItemMeta meta = stack.getItemMeta();

        if (meta == null)
            return null;

        ItemData data = ItemData.fromEncodedLore(meta.getLore());
        Item item = getItem(data.getIdentifier());

        item.setItemData(data);

        return item;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }
}
