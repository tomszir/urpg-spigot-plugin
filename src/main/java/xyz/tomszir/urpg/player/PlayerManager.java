package xyz.tomszir.urpg.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.tomszir.urpg.Main;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    // TODO: Add JavaDocs on this class.

    private static Main plugin = Main.getInstance();
    private static HashMap<UUID, PluginPlayer> players;

    public static void load() {
        players = new HashMap<>();

        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers())
            addPluginPlayer(onlinePlayer);
    }

    public static void addPluginPlayer(UUID uuid) {
        Player player = Bukkit.getServer().getPlayer(uuid);

        if (player == null)
            return;

        addPluginPlayer(player);
    }

    public static void addPluginPlayer(Player player) {
        players.put(player.getUniqueId(), new PluginPlayer(player));
    }

    public static PluginPlayer getPluginPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public static PluginPlayer getPluginPlayer(Player player) {
        return getPluginPlayer(player.getUniqueId());
    }

    public static void remove(UUID uuid) {
        players.remove(uuid);
        PlayerHealth.remove(uuid);
    }
}
