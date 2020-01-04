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

    private File file;
    private FileConfiguration data;

    private double health;

    public PlayerFile(UUID uuid) {
        // Path to PlayerFile "/plugins/uRPG/players/<uuid>.yml";
        this.file = new File(uRPG.getInstance().getDataFolder() + File.separator + "players", uuid + ".yml");
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
        return data.getInt(LEVEL_KEY, 1);
    }

    public void setLevel(int level) {
        data.set(LEVEL_KEY, level);
    }

    public int getExperience() {
        return data.getInt(EXP_KEY, 0);
    }

    public void setExperience(int experience) {
        data.set(EXP_KEY, experience);
    }

    public void setDefense(int defense) {
        data.set(DEFENSE_KEY, defense);
    }

    public double getDefense() {
        return data.getDouble(DEFENSE_KEY, 0);
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

    public void setMaxHealth(double maxHealth) {
        data.set(MAX_HEALTH_KEY, maxHealth);
    }

    public double getMaxHealth() {
        return data.getDouble(MAX_HEALTH_KEY, BASE_MAX_HEALTH);
    }

    @Override
    public void save() {
        setLevel(getLevel());
        setExperience(getExperience());

        // Health gets changed a lot, so store it in memory and only save it when leaving or something;
        data.set(CURR_HEALTH_KEY, health);

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

        health = data.getDouble(CURR_HEALTH_KEY, getMaxHealth());
    }

    public void migrate() {
        // TODO: Create a way to migrate OLD keys to NEW keys.
    }
}
