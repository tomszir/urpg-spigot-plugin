package xyz.tomszir.urpg.items.equippables;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.tomszir.urpg.enums.ItemRarity;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;

public class StonePlate extends EquippableItem {
    public StonePlate() {
        super("STONE_PLATE", Material.IRON_CHESTPLATE);

        setName("Stone Plate");
        setRarity(ItemRarity.EPIC);

        setDefense(250);

        addEffect("Like a Stone", "Increases your defense &a50%&7, but slows your movement speed by &c20%&7.");
    }

    @Override
    public double onDefenseCalculation(double defense) {
        return defense * 1.5;
    }

    @Override
    public void onPassiveEffect(uRPGPlayer player) {
        player.getBukkitPlayer().removePotionEffect(PotionEffectType.SLOW);
        player.getBukkitPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 5, 0));
    }
}
