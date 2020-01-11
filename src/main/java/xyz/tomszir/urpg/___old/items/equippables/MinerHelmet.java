package xyz.tomszir.urpg.___old.items.equippables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.beykerykt.lightapi.LightAPI;
import ru.beykerykt.lightapi.LightType;
import ru.beykerykt.lightapi.chunks.ChunkInfo;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.enums.ItemRarity;
import xyz.tomszir.urpg.___old.items.Equippable;
import xyz.tomszir.urpg.___old.items.ItemEffect;
import xyz.tomszir.urpg.___old.items.itemsets.MinerSet;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;

public class MinerHelmet extends Equippable {

    public static final int LIGHT_LEVEL = 20;

    public MinerHelmet() {
        super("MINER_HELMET", Material.IRON_HELMET);

        setName("Miner's Helmet");
        setRarity(ItemRarity.UNCOMMON);
        setItemSet(new MinerSet(this));

        setDefense(50);

        addEffect(
            new ItemEffect("Torch", "&7Provides a permanent light source around you &8(Light Level: 20)&7."));
    }

    @Override
    public void onEquip(CustomPlayer player) {
        onPassiveEffect(player);
    }

    @Override
    public void onUnequip(CustomPlayer player) {
        Location location = player.getBukkitPlayer().getLocation();

        LightAPI.deleteLight(location, LightType.BLOCK, false);

        for (ChunkInfo info : LightAPI.collectChunks(location, LightType.BLOCK, LIGHT_LEVEL))
            LightAPI.updateChunk(info, LightType.BLOCK);

        player.getBukkitPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event, CustomPlayer player) {
        super.onPlayerMove(event, player);

        // TODO: Make this into an ItemEffect.

        Location to = event.getTo();
        Location from = event.getFrom();

        if (to != from)
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                LightAPI.deleteLight(from, LightType.BLOCK, false);
            }, 20L);

        if (to != null) {
            LightAPI.createLight(to, LightType.BLOCK, LIGHT_LEVEL, true);

            for (ChunkInfo info : LightAPI.collectChunks(to, LightType.BLOCK, LIGHT_LEVEL))
                LightAPI.updateChunk(info, LightType.BLOCK);
        }
    }

    @Override
    public void onPassiveEffect(CustomPlayer player) {
        player.getBukkitPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
        player.getBukkitPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 5, 3));
    }
}
