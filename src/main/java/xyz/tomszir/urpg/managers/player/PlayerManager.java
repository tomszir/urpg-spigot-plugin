package xyz.tomszir.urpg.managers.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.tomszir.urpg.uRPG;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    public static final long PLAYER_UPDATE_INTERVAL = 20L * 2; // 2s

    private HashMap<UUID, PlayerFile> playerFiles;
    private HashMap<UUID, Double> playerHealth;

    public PlayerManager() {
        this.playerFiles = new HashMap<UUID, PlayerFile>();
        this.playerHealth = new HashMap<UUID, Double>();

        loadOnlinePlayerFiles();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(uRPG.getInstance(), () ->  {
            for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                uRPGPlayer player = getPlayer(onlinePlayer.getUniqueId());

                player.updateVanillaHealth();
                player.showCurrentHealth();
                player.updateArmorBar();
            }
        }, 0L, PLAYER_UPDATE_INTERVAL);
    }

    public void loadOnlinePlayerFiles() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            loadPlayerFile(player.getUniqueId());
        }
    }

    public void loadPlayerFile(UUID uuid) {
        if (playerFiles.containsKey(uuid)) return;

        PlayerFile file = new PlayerFile(uuid);

        setPlayerHealth(uuid, file.getLastSavedCurrentHealth());

        playerFiles.put(uuid, file);
    }

    public void removePlayerFile(UUID uuid) {
        savePlayerFile(uuid);
        playerFiles.remove(uuid);
    }

    public void savePlayerFile(UUID uuid) {
        getPlayerFile(uuid).save();
    }

    public PlayerFile getPlayerFile(UUID uuid) {
        PlayerFile file = playerFiles.get(uuid);
        file.load();

        return file;
    }

    public HashMap<UUID, PlayerFile> getPlayerFiles() {
        return playerFiles;
    }

    public void setPlayerHealth(UUID uuid, double health) {
        playerHealth.put(uuid, health);
    }

    public double getPlayerHealth(UUID uuid) {
        return playerHealth.get(uuid);
    }

    public uRPGPlayer getPlayer(UUID uuid) {
        if (!playerFiles.containsKey(uuid))
            loadPlayerFile(uuid);
        return new uRPGPlayer(uuid, getPlayerFile(uuid));
    }

}
