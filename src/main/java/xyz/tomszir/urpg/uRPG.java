package xyz.tomszir.urpg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import xyz.tomszir.urpg.managers.command.CommandManager;
import xyz.tomszir.urpg.managers.item.ItemManager;
import xyz.tomszir.urpg.managers.player.PlayerManager;
import xyz.tomszir.urpg.menu.MenuHolder;

public class uRPG extends JavaPlugin {

    public static final boolean DEBUG = true;

    private static uRPG instance;

    private PlayerManager playerManager;
    private CommandManager commandManager;
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        uRPG.instance = this;

        this.playerManager = new PlayerManager();
        this.commandManager = new CommandManager();
        this.itemManager = new ItemManager();

        // Listen for Bukkit Events;
        registerListeners();

        // Register executor for the main command;
        getCommand("urpg").setExecutor(this.commandManager);

        // Create the config.yml file from template;
        saveResource("config.yml", DEBUG);
    }

    @Override
    public void onDisable() {
        // Do some cleanup;
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            // Close all Menus;
            if (onlinePlayer.getOpenInventory().getTopInventory().getHolder() instanceof MenuHolder)
                onlinePlayer.closeInventory();

            // Save all PlayerFiles;
            getPlayerManager().savePlayerFile(onlinePlayer.getUniqueId());
        }
    }

    public void debug(String message) {
        if (DEBUG) getLogger().info("[DEBUG] " + message);
    }

    @SuppressWarnings("ALL")
    public void registerListeners() {
        Reflections listeners = new Reflections("xyz.tomszir.urpg.listeners");

        try {
            for (Class<? extends Listener> cls : listeners.getSubTypesOf(Listener.class))
                Bukkit.getPluginManager().registerEvents(cls.newInstance(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public static uRPG getInstance() {
        return uRPG.instance;
    }
}
