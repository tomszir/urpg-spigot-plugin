package xyz.tomszir.urpg.items.equippables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.beykerykt.lightapi.LightAPI;
import ru.beykerykt.lightapi.LightType;
import ru.beykerykt.lightapi.chunks.ChunkInfo;
import xyz.tomszir.urpg.enums.ItemRarity;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.uRPG;

public class MinerHelmet extends EquippableItem {

    public static final int LIGHT_LEVEL = 20;

    public MinerHelmet() {
        super("MINER_HELMET", Material.IRON_HELMET);

        setName("Miner's Helmet");
        addEffect("Miner's Will", "&7Provides a permanent light source around you &8(Light Level: 20) &7also grants you a permanent &6Haste IV &7buff.");
        setRarity(ItemRarity.EPIC);

        setDefense(50);
        // setPercentDefense(0.3); // 30%

        // setHealth(100);
        // setPercentHealth(0.5);
    }

    @Override
    public void onEquip(uRPGPlayer player) {
        player.getBukkitPlayer().sendMessage("Equipped!");
    }

    @Override
    public void onUnequip(uRPGPlayer player) {
        player.getBukkitPlayer().sendMessage("Unequipped!");
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event, uRPGPlayer player) {
        // Creates a light radius around the Player;
        Location to = event.getTo();
        Location from = event.getFrom();

        if (to != null)
            LightAPI.createLight(to, LightType.BLOCK, LIGHT_LEVEL, true);

        if (to != from)
            Bukkit.getScheduler().scheduleSyncDelayedTask(uRPG.getInstance(), new Runnable() {
                @Override
                public void run() {
                    LightAPI.deleteLight(from, LightType.BLOCK, false);
                }
            }, 20L);

        for (ChunkInfo info : LightAPI.collectChunks(to, LightType.BLOCK, LIGHT_LEVEL))
            LightAPI.updateChunk(info, LightType.BLOCK);
    }

    @Override
    public void onPassiveEffect(uRPGPlayer player) {
        // Reset the PotionEffect
        player.getBukkitPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
        player.getBukkitPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 5, 3));
    }
}
