package xyz.tomszir.urpg.managers.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    public static final long FILE_UPDATE_INTERVAL = 20L * 5; // 5s

    private HashMap<UUID, PlayerFile> playerFiles;

    public PlayerManager() {
        this.playerFiles = new HashMap<UUID, PlayerFile>();

        loadOnlinePlayerFiles();
    }

    public void loadPlayerFile(UUID uuid) {
        if (playerFiles.containsKey(uuid)) return;

        PlayerFile file = new PlayerFile(uuid);
        file.save();

        playerFiles.put(uuid, file);
    }

    public void loadOnlinePlayerFiles() {
        for (Player player : Bukkit.getServer().getOnlinePlayers())
            loadPlayerFile(player.getUniqueId());
    }

    public void removePlayerFile(UUID uuid) {
        playerFiles.remove(uuid);
    }

    public uRPGPlayer getPlayer(UUID uuid) {
        if (!playerFiles.containsKey(uuid))
            loadPlayerFile(uuid);
        return new uRPGPlayer(uuid, getPlayerFile(uuid));
    }

    public PlayerFile getPlayerFile(UUID uuid) {
        return playerFiles.get(uuid);
    }

    public HashMap<UUID, PlayerFile> getPlayerFiles() {
        return playerFiles;
    }
}
