package xyz.tomszir.urpg.___old.managers.player;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.interfaces.FileInterface;

import java.io.File;
import java.util.UUID;

public class PlayerFile implements FileInterface {

    public static final double DEFAULT_MAXIMUM_HEALTH = 100.0;

    public static final String LEVEL_FIELD = "level";
    public static final String EXPERIENCE_FIELD = "experience";

    public static final String CURRENT_HEALTH_FIELD = "stats.health.current";
    public static final String MAXIMUM_HEALTH_FIELD = "stats.health.maximum";

    public static final String STRENGTH_FIELD = "stats.strength";
    public static final String DEXTERITY_FIELD = "stats.dexterity";
    public static final String INTELLIGENCE_FIELD = "stats.intelligence";
    public static final String CONSTITUTION_FIELD = "stats.constitution";

    private static final Main plugin = Main.getInstance();

    private UUID uuid;
    private File file;
    private FileConfiguration data;

    public PlayerFile(UUID uuid) {
        this.uuid = uuid;
        this.file = new File(plugin.getDataFolder() + File.separator + "players", uuid + ".yml");
        this.data = new YamlConfiguration();

        if (!file.exists())
            this.create();
        this.load();
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public void set(String key, Object value) {
        data.set(key, value);
    }

    public void setLevel(int level) {
        set(LEVEL_FIELD, level);
    }

    public int getLevel() {
        return data.getInt(LEVEL_FIELD, 1);
    }

    public void setExperience(int experience) {
        set(EXPERIENCE_FIELD, experience);
    }

    public int getExperience() {
        return data.getInt(EXPERIENCE_FIELD, 0);
    }

    public double getMaxHealth() {
        return data.getDouble(MAXIMUM_HEALTH_FIELD, 100.0);
    }

    public void setMaxHealth(double maxHealth) {
        set(MAXIMUM_HEALTH_FIELD, maxHealth);
    }

    public int getStrength() {
        return data.getInt(STRENGTH_FIELD, 0);
    }

    public void setStrength(int strength) {
        set(STRENGTH_FIELD, strength);
    }

    public int getIntelligence() {
        return data.getInt(INTELLIGENCE_FIELD, 0);
    }

    public void setIntelligence(int intelligence) {
        set(INTELLIGENCE_FIELD, intelligence);
    }

    public int getDexterity() {
        return data.getInt(DEXTERITY_FIELD, 0);
    }

    public void setDexterity(int dexterity) {
        set(DEXTERITY_FIELD, dexterity);
    }

    public int getConstitution() {
        return data.getInt(CONSTITUTION_FIELD, 0);
    }

    public void setConstitution(int constituion) {
        set(CONSTITUTION_FIELD, constituion);
    }

    public void setCurrentHealth(double health) {
        plugin.getPlayerManager().setPlayerHealth(uuid, health);
    }

    public double getCurrentHealth() {
        return plugin.getPlayerManager().getPlayerHealth(uuid);
    }

    public double getLastSavedCurrentHealth() {
        return data.getDouble(CURRENT_HEALTH_FIELD, DEFAULT_MAXIMUM_HEALTH);
    }

    public void create() {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        setLevel(getLevel());
        setExperience(getExperience());

        setMaxHealth(getMaxHealth());

        set(CURRENT_HEALTH_FIELD, getCurrentHealth());

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
