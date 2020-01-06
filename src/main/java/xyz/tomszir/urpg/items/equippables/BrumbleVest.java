package xyz.tomszir.urpg.items.equippables;

import org.bukkit.Material;
import xyz.tomszir.urpg.enums.ItemRarity;
import xyz.tomszir.urpg.items.EquippableItem;

public class BrumbleVest extends EquippableItem {
    public BrumbleVest() {
        super("BRUMBLE_VEST", Material.LEATHER_CHESTPLATE);

        setName("Brumble Vest");
        setRarity(ItemRarity.LEGENDARY);
        // setFlatDefense(300);

        setHealth(1000);
        setDefense(500);

        addEffect("Will of The Forest", "Your health is increased by &a30%&7 & you regenerate &c5%&7 of your max. health per second. You now deal &a50%&7 reduced damage from attacks.");
        // setPercentHealth(0.5);
    }

    @Override
    public double onHealthCalculation(double maxHealth) {
        return maxHealth * 1.3;
    }
}
