package xyz.tomszir.urpg;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.tomszir.urpg.player.PlayerManager;

/**
 * The Main class of this plugin.
 */
public class Main extends JavaPlugin {

    /**
     * The instance of this class.
     */
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        // Load online players into the player manager.
        PlayerManager.load();
    }

    @Override
    public void onDisable() {
        // ...
    }

    /**
     * Gets the Main instance.
     *
     * @return the instance of the Main class.
     */
    public static Main getInstance() {
        return instance;
    }
}
