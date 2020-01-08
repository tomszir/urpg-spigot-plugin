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
        addEffect("Torch", "&7Provides a permanent light source around you &8(Light Level: 20)&7.\n\n&6Set Bonus: &cNot Active\n&8Grants you permanent Haste IV.");
        setRarity(ItemRarity.UNCOMMON);

        setDefense(50);
    }

    @Override
    public void onEquip(uRPGPlayer player) {
        onPassiveEffect(player);
    }

    @Override
    public void onUnequip(uRPGPlayer player) {
        Location location = player.getBukkitPlayer().getLocation();

        LightAPI.deleteLight(location, LightType.BLOCK, false);

        for (ChunkInfo info : LightAPI.collectChunks(location, LightType.BLOCK, LIGHT_LEVEL))
            LightAPI.updateChunk(info, LightType.BLOCK);

        player.getBukkitPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event, uRPGPlayer player) {
        Location to = event.getTo();
        Location from = event.getFrom();

        if (to != from)
            Bukkit.getScheduler().scheduleSyncDelayedTask(uRPG.getInstance(), () -> {
                LightAPI.deleteLight(from, LightType.BLOCK, false);
            }, 20L);

        if (to != null) {
            LightAPI.createLight(to, LightType.BLOCK, LIGHT_LEVEL, true);

            for (ChunkInfo info : LightAPI.collectChunks(to, LightType.BLOCK, LIGHT_LEVEL))
                LightAPI.updateChunk(info, LightType.BLOCK);
        }
    }

    @Override
    public void onPassiveEffect(uRPGPlayer player) {
        player.getBukkitPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
        player.getBukkitPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 5, 3));
    }
}
