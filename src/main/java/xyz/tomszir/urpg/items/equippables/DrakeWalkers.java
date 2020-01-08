package xyz.tomszir.urpg.items.equippables;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.tomszir.urpg.enums.ItemRarity;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.util.ItemUtil;

public class DrakeWalkers extends EquippableItem {
    public DrakeWalkers() {
        super("DRAKE_WALKERS", Material.LEATHER_BOOTS);

        setName("Drake Walkers");
        setRarity(ItemRarity.LEGENDARY);

        setDefense(25);

        addEffect("Dragon Flight", "Grants you the ability to &dFly&7.");
    }

    public ItemStack getItemStack(int amount) {
        return ItemUtil.colorLeatherItemStack(super.getItemStack(amount), Color.fromRGB(20, 20, 20));
    }

    @Override
    public void onEquip(uRPGPlayer player) {
        player.getBukkitPlayer().setAllowFlight(true);
    }

    @Override
    public void onUnequip(uRPGPlayer player) {
        player.getBukkitPlayer().setAllowFlight(false);
    }
}
