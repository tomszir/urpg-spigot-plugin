package xyz.tomszir.urpg.___old.events;

import org.bukkit.entity.Player;

public class MenuItemClickEvent {

    private Player player;

    private boolean closing;
    private boolean backing;

    public MenuItemClickEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void close() {
        closing = true;
    }

    public boolean isClosing() {
        return closing;
    }

    public void back() {
        backing = true;
    }

    public boolean isBacking() {
        return backing;
    }
}
