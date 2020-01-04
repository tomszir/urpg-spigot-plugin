package xyz.tomszir.urpg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import xyz.tomszir.urpg.managers.command.CommandManager;
import xyz.tomszir.urpg.managers.player.PlayerManager;
import xyz.tomszir.urpg.menu.MenuHolder;

import java.util.Set;

public class uRPG extends JavaPlugin {

    public static final boolean DEBUG = true;

    private static uRPG instance;

    private PlayerManager playerManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        uRPG.instance = this;

        this.playerManager = new PlayerManager();
        this.commandManager = new CommandManager();

        // Listen for Bukkit Events;
        registerAllListeners();

        // Register executor for the main command;
        getCommand("urpg").setExecutor(this.commandManager);
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getServer().getOnlinePlayers())
            if (player.getOpenInventory().getTopInventory().getHolder() instanceof MenuHolder)
                player.closeInventory();
    }

    public void debug(String message) {
        if (DEBUG) getLogger().info("[DEBUG] " + message);
    }

    public void registerAllListeners() {
        Reflections reflections = new Reflections("xyz.tomszir.urpg.listeners");
        Set<Class<? extends Listener>> classes = reflections.getSubTypesOf(Listener.class);

        for (Class<? extends Listener> cls : classes) {
            try {
                Bukkit.getPluginManager().registerEvents(cls.newInstance(), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public static uRPG getInstance() {
        return uRPG.instance;
    }
}
