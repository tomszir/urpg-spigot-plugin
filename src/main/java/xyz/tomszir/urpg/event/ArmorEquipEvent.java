package xyz.tomszir.urpg.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import xyz.tomszir.urpg.Main;

public class ArmorEquipEvent extends Event implements Cancellable {

    // TODO: Add JavaDoc.

    public static final HandlerList HANDLERS = new HandlerList();

    private static Main plugin = Main.getInstance();

    private boolean cancelled;

    private ItemStack item;

    private Player bukkitPlayer;

    public ArmorEquipEvent(Player bukkitPlayer, ItemStack item) {
        this.item = item;
        this.bukkitPlayer = bukkitPlayer;
    }

    public ItemStack getEquippedItem() {
        return item;
    }

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
