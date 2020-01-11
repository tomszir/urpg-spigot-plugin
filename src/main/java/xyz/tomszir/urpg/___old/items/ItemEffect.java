package xyz.tomszir.urpg.___old.items;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.tomszir.urpg.___old.interfaces.PlayerEventListener;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;
import xyz.tomszir.urpg.___old.util.ColorUtil;

public class ItemEffect implements PlayerEventListener {

    private String name;
    private String description;

    public ItemEffect(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        String string =
                "&6Effect: &6e" + getName() + ":" +
                "&7" + getDescription();
        return ColorUtil.parse(string);
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event, CustomPlayer player) {
        // ...
    }

    @Override
    public void onDamageReceived(EntityDamageEvent event, CustomPlayer player) {
        // ...
    }

    @Override
    public void onPassiveEffect(CustomPlayer player) {
        // ...
    }

    @Override
    public void onRapidPassiveEffect(CustomPlayer player) {
        // ...
    }
}
