package xyz.tomszir.urpg.___old.items.equippables;

import org.bukkit.Material;
import org.bukkit.Particle;
import xyz.tomszir.urpg.___old.enums.ItemRarity;
import xyz.tomszir.urpg.___old.items.Equippable;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;
import xyz.tomszir.urpg.___old.util.ParticleUtil;

public class BrumbleVest extends Equippable {
    public BrumbleVest() {
        super("BRUMBLE_VEST", Material.LEATHER_CHESTPLATE);

        setName("Brumble Vest");
        setRarity(ItemRarity.LEGENDARY);

        // setFlatDefense(300);
        // setHealth(2000);
        // setDefense(500);
        // addEffect("Will of The Forest", "Your health is increased by &a25%&7 & you regenerate &c5%&7 of your &dmissing health&7 per second. You now deal &a50%&7 reduced damage from attacks.");
        // setPercentHealth(0.5);
    }

    @Override
    public double onPlayerMaxHealthCalculation(double value) {
        return value * 1.25;
    }

    @Override
    public void onRapidPassiveEffect(CustomPlayer player) {
        super.onRapidPassiveEffect(player);

        // TODO: Make this into an ItemEffect.

        double health = player.getStats().getCurrentHealth();
        double maxHealth = player.getStats().getMaxHealth();

        ParticleUtil.scatterParticlesOnPlayer(player.getBukkitPlayer(), Particle.VILLAGER_HAPPY, 6, 4);

        if (health < maxHealth)
            player.setHealth(Math.min(health + Math.round((maxHealth - health) * 0.05), maxHealth));
    }
}
