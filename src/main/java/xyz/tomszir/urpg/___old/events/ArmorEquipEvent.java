package xyz.tomszir.urpg.___old.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.enums.ArmorEquipType;
import xyz.tomszir.urpg.___old.items.Equippable;
import xyz.tomszir.urpg.___old.items.Item;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;

public class ArmorEquipEvent extends Event implements Cancellable {

    public static final HandlerList HANDLERS = new HandlerList();

    private Main plugin = Main.getInstance();

    private int slot;
    private boolean cancelled;

    private Player bukkitPlayer;
    private ItemStack item;
    private ArmorEquipType type;

    public ArmorEquipEvent(Player bukkitPlayer, ItemStack item, ArmorEquipType type) {
        this.item = item;
        this.type = type;
        this.bukkitPlayer = bukkitPlayer;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public CustomPlayer getPlayer() {
        return plugin.getPlayerManager().getPlayer(bukkitPlayer.getUniqueId());
    }

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

    public ArmorEquipType getEquipType() {
        return type;
    }

    public ItemStack getItem() {
        return item;
    }

    public Equippable getEquippableItem() {
        Item base = plugin.getItemManager().getItemWithoutIdentifier(item);

        if (base == null) return null;
        if (!(base instanceof Equippable)) return null;

        return (Equippable) base;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
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
