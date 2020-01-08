package xyz.tomszir.urpg.items.equippables;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import xyz.tomszir.urpg.enums.ItemRarity;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.util.ItemUtil;

public class BrumbleVest extends EquippableItem {
    public BrumbleVest() {
        super("BRUMBLE_VEST", Material.LEATHER_CHESTPLATE);

        setName("Brumble Vest");
        setRarity(ItemRarity.LEGENDARY);
        // setFlatDefense(300);

        setHealth(2000);
        // setDefense(500);

        addEffect("Will of The Forest", "Your health is increased by &a25%&7 & you regenerate &c5%&7 of your &dmissing health&7 per second. You now deal &a50%&7 reduced damage from attacks.");
        // setPercentHealth(0.5);
    }

    public ItemStack getItemStack(int amount) {
        return ItemUtil.colorLeatherItemStack(super.getItemStack(amount), Color.fromRGB(34, 139, 34));
    }

    @Override
    public double onHealthCalculation(double maxHealth) {
        return maxHealth * 1.25;
    }

    @Override
    public void onRapidPassiveEffect(uRPGPlayer player) {
        double health = player.getStats().getCurrentHealth();
        double maxHealth = player.getStats().getMaxHealth();

        for (int i = 0; i < 6 + Math.floor(Math.random() * 4); i++) {
            Location location = player.getBukkitPlayer().getLocation();

            double x = location.getX() + Math.random() * 2 - 1;
            double y = location.getY() + 0.15 + (Math.random() / 2);
            double z = location.getZ() + Math.random() * 2 - 1;

            player.getBukkitPlayer().spawnParticle(Particle.VILLAGER_HAPPY, x, y, z, 1);
        }

        if (health < maxHealth)
            player.setHealth(Math.min(health + Math.round((maxHealth - health) * 0.05), maxHealth));
    }
}
