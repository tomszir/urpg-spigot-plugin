package xyz.tomszir.urpg.item;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.tomszir.urpg.event.ArmorEquipEvent;
import xyz.tomszir.urpg.player.PluginPlayer;

/**
 * An interface for event listener methods, that get fired from equippable items.
 */
public interface EquippableListener {
    /**
     * Method runs when an equippable item gets equipped.
     *
     * @param event the armor equip event.
     */
    void onEquip(ArmorEquipEvent event);

    /**
     * Method runs when an equippable item gets unequipped.
     *
     * @param event the armor equip event.
     */
    void onUnequip(ArmorEquipEvent event);

    /**
     * Method runs when a player takes damage.
     *
     * @param event the entity damage event.
     */
    void onPlayerHurt(EntityDamageEvent event);

    /**
     * Method runs whenever a player moves.
     *
     * @param event the player move event.
     */
    void onPlayerMove(PlayerMoveEvent event);

    /**
     * Method runs every 5 seconds, for every online player.
     *
     * @param player the plugin player this is running for.
     */
    void onPassiveEffect(PluginPlayer player);

    /**
     * Method runs every second, for every online player.
     *
     * @param player the plugin player this is running for.
     */
    void onRapidPassiveEffect(PluginPlayer player);
}
