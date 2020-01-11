package xyz.tomszir.urpg.___old;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import xyz.tomszir.urpg.___old.items.Equippable;
import xyz.tomszir.urpg.___old.managers.command.CommandManager;
import xyz.tomszir.urpg.___old.managers.item.ItemManager;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;
import xyz.tomszir.urpg.___old.managers.player.PlayerManager;
import xyz.tomszir.urpg.___old.menu.MenuHolder;

public class Main extends JavaPlugin {

    public static final boolean DEBUG = true;

    private static Main instance;

    private PlayerManager playerManager;
    private CommandManager commandManager;
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        Main.instance = this;

        this.playerManager = new PlayerManager();
        this.commandManager = new CommandManager();
        this.itemManager = new ItemManager();

        // Listen for Bukkit Events;
        registerListeners();

        // Register executor for the main command;
        getCommand("urpg").setExecutor(this.commandManager);

        // Get all equipped items and call the equipped function
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            CustomPlayer player = playerManager.getPlayer(onlinePlayer.getUniqueId());

            for (Equippable item : player.getEquipment().getAll())
                if (item != null)
                    item.onEquip(player);
        }

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

            CustomPlayer player = playerManager.getPlayer(onlinePlayer.getUniqueId());

            for (Equippable item : player.getEquipment().getAll())
                if (item != null)
                    item.onUnequip(player);

            getPlayerManager().savePlayerFile(onlinePlayer.getUniqueId());
        }
    }

    public void debug(String message) {
        if (DEBUG) getLogger().info("[DEBUG] " + message);
    }

    @SuppressWarnings("ALL")
    public void registerListeners() {
        Reflections listeners = new Reflections("xyz.tomszir.urpg.old.listeners");

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

    public static Main getInstance() {
        return Main.instance;
    }
}
