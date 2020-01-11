package xyz.tomszir.urpg.player;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.tomszir.urpg.___old.Main;

import java.io.File;

public class PlayerFile {

    // TODO: Write JavaDoc.

    private static final Main plugin = Main.getInstance();
    private static final String dir = plugin.getDataFolder() + File.separator + "players";

    private File file;
    private FileConfiguration data;
    private PluginPlayer player;

    public PlayerFile(PluginPlayer player) {
        this.player = player;
        this.file = new File(dir, player.getUniqueId() + ".yml");
        this.data = new YamlConfiguration();

        if (!file.exists())
            create();
        load();
    }

    public void set(PlayerFileField field, Object value) {
        data.set(field.getKey(), value);
    }

    public int getInt(PlayerFileField field) {
        return getInt(field, 0);
    }

    public int getInt(PlayerFileField field, int def) {
        return data.getInt(field.getKey(), def);
    }

    public double getDouble(PlayerFileField field) {
        return data.getDouble(field.getKey(), 0);
    }

    public double getDouble(PlayerFileField field, double def) {
        return data.getDouble(field.getKey(), def);
    }

    public void setLevel(int level) {
        set(PlayerFileField.LEVEL, level);
    }

    public int getLevel() {
        return getInt(PlayerFileField.LEVEL, 1);
    }

    public void setExperience(int experience) {
        set(PlayerFileField.EXPERIENCE, experience);
    }

    public int getExperience() {
        return getInt(PlayerFileField.EXPERIENCE);
    }

    public int getStrength() {
        return getInt(PlayerFileField.STRENGTH);
    }

    public void setStrength(int strength) {
        set(PlayerFileField.STRENGTH, strength);
    }

    public int getIntelligence() {
        return getInt(PlayerFileField.INTELLIGENCE);
    }

    public void setIntelligence(int intelligence) {
        set(PlayerFileField.INTELLIGENCE, intelligence);
    }

    public int getDexterity() {
        return getInt(PlayerFileField.DEXTERITY);
    }

    public void setDexterity(int dexterity) {
        set(PlayerFileField.DEXTERITY, dexterity);
    }

    public int getConstitution() {
        return getInt(PlayerFileField.CONSTITUTION);
    }

    public void setConstitution(int constituion) {
        set(PlayerFileField.CONSTITUTION, constituion);
    }

    public double getSavedHealth() {
        return getDouble(PlayerFileField.SAVED_HEALTH, PlayerDefault.MAX_HEALTH);
    }

    @SuppressWarnings("ALL")
    public void create() {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        set(PlayerFileField.SAVED_HEALTH, player.getStats().getHealth());

        try {
            data.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
