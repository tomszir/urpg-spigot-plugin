package xyz.tomszir.urpg.items.equippables;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.tomszir.urpg.enums.ItemRarity;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.util.ItemUtil;

public class DragonscaleChestplate extends EquippableItem {
    public DragonscaleChestplate() {
        super("DRAGONSCALE_CHESTPLATE", Material.LEATHER_CHESTPLATE);

        setName("Dragonscale Chestplate");
        setRarity(ItemRarity.RARE);
        setDefense(100);

        addEffect("Way of the Dragon", "Grants permanent &dFire Resistance&7.");
    }

    public ItemStack getItemStack(int amount) {
        return ItemUtil.colorLeatherItemStack(super.getItemStack(amount), Color.fromRGB(255, 69, 0));
    }

    @Override
    public void onEquip(uRPGPlayer player) {
        onPassiveEffect(player);
    }

    @Override
    public void onUnequip(uRPGPlayer player) {
        player.getBukkitPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
    }

    public void onPassiveEffect(uRPGPlayer player) {
        player.getBukkitPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        player.getBukkitPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 5, 0));
    }
}
