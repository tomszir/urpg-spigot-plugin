package xyz.tomszir.urpg.managers.player;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.tomszir.urpg.interfaces.FileInterface;
import xyz.tomszir.urpg.uRPG;

import java.io.File;
import java.util.UUID;

public class PlayerFile implements FileInterface {

    public static final double BASE_MAX_HEALTH = 100.0;

    public static final String[] OLD_LEVEL_KEYS = null;
    public static final String LEVEL_KEY = "level";

    public static final String[] OLD_EXP_KEYS = null;
    public static final String EXP_KEY = "experience";

    public static final String[] DEFENSE_EXP_KEYS = null;
    public static final String DEFENSE_KEY = "stats.defense";

    public static final String[] CURR_HEALTH_EXP_KEYS = null;
    public static final String CURR_HEALTH_KEY = "stats.health.current";

    public static final String[] MAX_HEALTH_EXP_KEYS = null;
    public static final String MAX_HEALTH_KEY = "stats.health.maximum";

    private uRPG plugin = uRPG.getInstance();

    private UUID uuid;
    private File file;
    private FileConfiguration data;

    public PlayerFile(UUID uuid) {
        // Path to PlayerFile "/plugins/uRPG/players/<uuid>.yml";
        this.uuid = uuid;
        this.file = new File(plugin.getDataFolder() + File.separator + "players", uuid + ".yml");
        this.data = new YamlConfiguration();

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.load();
    }

    public int getLevel() {
        return data.getInt(LEVEL_KEY,
            plugin.getConfig().getInt("player-defaults.level", 1));
    }

    public void setLevel(int level) {
        data.set(LEVEL_KEY, level);
    }

    public int getExperience() {
        return data.getInt(EXP_KEY,
            plugin.getConfig().getInt("player-defaults.experience", 0));
    }

    public void setExperience(int experience) {
        data.set(EXP_KEY, experience);
    }

    public void setDefense(double defense) {
        data.set(DEFENSE_KEY, defense);
    }

    public double getDefense() {
        return data.getDouble(DEFENSE_KEY, 0);
    }

    public double getCurrentHealth() {
        return plugin.getPlayerManager().getPlayerHealth(uuid);
    }

    public void setCurrentHealth(double health) {
        plugin.getPlayerManager().setPlayerHealth(uuid, health);
    }

    public double getLastSavedCurrentHealth() {
        return data.getDouble(CURR_HEALTH_KEY, BASE_MAX_HEALTH);
    }

    public void setMaxHealth(double maxHealth) {
        data.set(MAX_HEALTH_KEY, maxHealth);
    }

    public double getMaxHealth() {
        return data.getDouble(MAX_HEALTH_KEY,
            plugin.getConfig().getDouble("player-defaults.max-health", BASE_MAX_HEALTH));
    }

    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public void save() {
        // -- Leveling;
        setLevel(getLevel());
        setExperience(getExperience());

        // -- Health;
        setMaxHealth(getMaxHealth());

        // -- Stats;
        setDefense(getDefense());

        data.set(CURR_HEALTH_KEY, getCurrentHealth());

        try {
            data.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try {
            data.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void migrate() {
        // TODO: Create a way to migrate OLD keys to NEW keys.
    }
}
