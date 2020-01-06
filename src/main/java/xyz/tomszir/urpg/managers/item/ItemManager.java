package xyz.tomszir.urpg.managers.item;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import xyz.tomszir.urpg.items.BaseItem;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.uRPG;

import java.util.HashMap;
import java.util.List;

public class ItemManager {

    private HashMap<String, BaseItem> items;

    public ItemManager() {
        this.items = new HashMap<>();

        registerItems();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(uRPG.getInstance(), () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                uRPGPlayer player = uRPG.getInstance().getPlayerManager().getPlayer(onlinePlayer.getUniqueId());
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

        /*
        For if something doesn't work.

        Reflections equippables = new Reflections("xyz.tomszir.urpg.items.equippables");
        Set<Class<? extends EquippableItem>> classes = equippables.getSubTypesOf(EquippableItem.class);

        for (Class<? extends EquippableItem> cls : classes) {
            try {
                addItem(cls.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}
