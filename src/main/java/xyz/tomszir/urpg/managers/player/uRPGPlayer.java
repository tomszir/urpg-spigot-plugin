package xyz.tomszir.urpg.managers.player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.tomszir.urpg.items.BaseItem;
import xyz.tomszir.urpg.items.EquippableItem;
import xyz.tomszir.urpg.uRPG;
import xyz.tomszir.urpg.util.ColorUtil;
import xyz.tomszir.urpg.util.FormulaUtil;
import xyz.tomszir.urpg.util.LoreEncoderUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
public class uRPGPlayer {

    public static final double VANILLA_BASE_HEALTH = 20.0;
    public static final String HEALTH_INDICATOR = "&c%HEALTH% / %MAX_HEALTH% ❤";

    private uRPG plugin = uRPG.getInstance();

    private Player player;
    private PlayerFile file;
    private PlayerStats stats;
    private HashMap<String, EquippableItem> currentlyEquipped;

    public uRPGPlayer(UUID uuid, PlayerFile file) {
        this.file = file;
        this.player = Bukkit.getServer().getPlayer(uuid);
        this.stats = new PlayerStats(this);

        this.currentlyEquipped = new HashMap<>();

        file.load();
    }

    public void updateEquipped() {
        /*
        TODO: Make this work.
        EquippableItem helmet = getHelmet();
        EquippableItem equipped = currentlyEquipped.get("HELMET");

        currentlyEquipped.put("HELMET", helmet);

        // Player has no helmet now, but he had one, so trigger unequip.
        //if (helmet == null) {
        //    if (equipped != null) {
        //        equipped.onUnequip(this);
        //    }
        //}

        // Player has a helmet now, and had one equipped so trigget unequip & equip.
        uRPG.getInstance().debug("HELMET NOW: " + (helmet == null ? "NULL" : helmet.getIdentifier()));
        uRPG.getInstance().debug("HELMET THEN: " + (equipped == null ? "NULL" : equipped.getIdentifier()));
        uRPG.getInstance().debug("");

        if (helmet == null && equipped != null) {
                equipped.onUnequip(this);
        }

        if (helmet != null && equipped != null) {
            helmet.onEquip(this);
            equipped.onUnequip(this);
        }

        if (helmet != null && equipped == null) {
            helmet.onEquip(this);
        }*/
    }

    public Player getBukkitPlayer() {
        return player;
    }

    public PlayerFile getFile() {
        return file;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public double getDamageReduction() {
        return FormulaUtil.getDamageReductionFromDefense(stats.getDefense());
    }

    public void damage(double damage) {
        if (!player.isInvulnerable()) {
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_HURT, 1f, 1f);
            setHealth(stats.getCurrentHealth() - damage);
        }
    }

    public void setHealth(double health) {
        if (health > 0) {
            file.setCurrentHealth(health);
        } else {
            file.setCurrentHealth(0);
        }

        showCurrentHealth();
        updateVanillaHealth();
    }

    public void updateVanillaHealth() {
        double health = stats.getCurrentHealth();
        double maxHealth = stats.getMaxHealth();

        if (health > maxHealth) {
            file.setCurrentHealth(maxHealth);
            file.save();
        }

        player.setHealth(VANILLA_BASE_HEALTH * Math.max(Math.min(health / maxHealth, 1), 0));
    }

    public void showCurrentHealth() {
        if (!uRPG.getInstance().getConfig().getBoolean("enable-health-action-bar", true)) return;
        if (player.getGameMode() == GameMode.SURVIVAL) {
            String message = HEALTH_INDICATOR
                .replaceAll("%HEALTH%", String.format("%d", (int) Math.ceil(stats.getCurrentHealth())))
                .replaceAll("%MAX_HEALTH%", String.format("%d", (int) Math.ceil(stats.getMaxHealth())));

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(ColorUtil.formatColor(message)));
        }
    }

    public EquippableItem getEquipped(ItemStack equipped) {
        if (equipped == null) return null;

        List<String> lore = equipped.getItemMeta().getLore();
        String identifier = LoreEncoderUtil.decode(lore.get(lore.size() - 1).split(" ")[1]);

        BaseItem item = plugin.getItemManager().getItem(identifier);

        if (item == null) return null;
        if (!(item instanceof EquippableItem)) return null;

        return (EquippableItem) item;
    }

    public EquippableItem getHelmet() {
        return getEquipped(player.getInventory().getHelmet());
    }

    public EquippableItem getChestplate() {
        return getEquipped(player.getInventory().getChestplate());
    }

    public EquippableItem getLeggings() {
        return getEquipped(player.getInventory().getLeggings());
    }

    public EquippableItem getBoots() {
        return getEquipped(player.getInventory().getBoots());
    }

    public List<EquippableItem> getEquippedList() {
        return Arrays.asList(getHelmet(), getChestplate(), getLeggings(), getBoots());
    }
}
