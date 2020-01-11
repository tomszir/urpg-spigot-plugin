package xyz.tomszir.urpg.___old.managers.player;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.items.Equippable;
import xyz.tomszir.urpg.___old.items.Item;
import xyz.tomszir.urpg.___old.items.ItemData;
import xyz.tomszir.urpg.___old.util.LoreEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerEquipment extends CustomPlayerExtension {

    private Main plugin = Main.getInstance();

    public PlayerEquipment(CustomPlayer player) {
        super(player);
    }

    public Equippable getFromItemStack(ItemStack item) {
        if (item == null || item.getType() == Material.AIR)
            return null;

        ItemMeta meta = item.getItemMeta();

        if (meta == null) return null;

        List<String> lore = item.getItemMeta().getLore();

        if (lore == null) return null;

        String encodedString = lore.get(lore.size() - 1).split(" ")[1];
        String identifier = LoreEncoder.decode(encodedString);

        ItemData data = ItemData.fromEncodedLore(lore);
        Item equipped = plugin.getItemManager().getItem(identifier);

        if (equipped == null) return null;
        if (!(equipped instanceof Equippable)) return null;

        return (Equippable) equipped;
    }

    public Equippable getHelmet() {
        return getFromItemStack(getPlayer().getBukkitPlayer().getInventory().getHelmet());
    }

    public Equippable getChestplate() {
        return getFromItemStack(getPlayer().getBukkitPlayer().getInventory().getChestplate());
    }

    public Equippable getLeggings() {
        return getFromItemStack(getPlayer().getBukkitPlayer().getInventory().getLeggings());
    }

    public Equippable getBoots() {
        return getFromItemStack(getPlayer().getBukkitPlayer().getInventory().getBoots());
    }

    public List<Equippable> getAll() {
        return Arrays.asList(getHelmet(), getChestplate(), getLeggings(), getBoots())
            .parallelStream()
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }
}
