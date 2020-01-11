package xyz.tomszir.urpg.___old.managers.player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.util.ColorUtil;
import xyz.tomszir.urpg.___old.util.FormulaUtil;

import java.util.UUID;

@SuppressWarnings("ALL")
public class CustomPlayer {

    public static final double VANILLA_BASE_HEALTH = 20.0;
    public static final String HEALTH_INDICATOR = "&c%HEALTH% / %MAX_HEALTH% ❤";

    private Main plugin = Main.getInstance();

    private Player player;
    private PlayerFile file;
    private PlayerStats stats;
    private PlayerEquipment equipment;

    public CustomPlayer(UUID uuid, PlayerFile file) {
        this.file = file;
        this.player = Bukkit.getServer().getPlayer(uuid);

        this.stats = new PlayerStats(this);
        this.equipment = new PlayerEquipment(this);

        file.load();
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

    public PlayerEquipment getEquipment() {
        return equipment;
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
        if (!Main.getInstance().getConfig().getBoolean("enable-health-action-bar", true)) return;
        if (player.getGameMode() == GameMode.SURVIVAL) {
            String message = HEALTH_INDICATOR
                .replaceAll("%HEALTH%", String.format("%d", (int) Math.ceil(stats.getCurrentHealth())))
                .replaceAll("%MAX_HEALTH%", String.format("%d", (int) Math.ceil(stats.getMaxHealth())));

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(ColorUtil.parse(message)));
        }
    }

    public void updateArmorBar() {
        plugin.debug("ARMOR_BAR: " + 20.0 + " * " + (1 - getDamageReduction()));
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(20.0 * (1 - getDamageReduction()));
    }
}
