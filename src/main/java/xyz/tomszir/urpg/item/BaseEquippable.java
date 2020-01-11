package xyz.tomszir.urpg.item;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.tomszir.urpg.event.ArmorEquipEvent;
import xyz.tomszir.urpg.player.PluginPlayer;

/**
 * The base class of any equippable item.
 */
public class BaseEquippable extends BaseItem implements EquippableListener {

    /**
     * The player stat modifier. The modifier contains values on how to modify the stats of the player when equipped.
     */
    private StatModifier statModifier;

    /**
     * The player attribute modifier. The modifier contains values on how to modify the attributes of the player when equipped.
     */
    private AttributeModifier attributeModifier;

    /**
     * The equip type of this equippable. Ex. boots, helmet, chestplate, etc.
     */
    private EquippableType type;

    /**
     * Class constructor.
     *
     * @param identifier the unique identifier you want this equippable to have.
     */
    public BaseEquippable(String identifier, EquippableType type) {
        super("EQUIPPABLE_" + identifier);

        this.type = type;
    }

    /**
     * Gets the item effect's description.
     *
     * @return the item effect's description.
     */
    public String getEffectDescription() {
        return null;
    }

    /**
     * Sets the stat modifier for this item. It gets called whenever calculating player stats.
     *
     * @param statModifier the stat modifier you want this item to have.
     */
    public void setStatModifier(StatModifier statModifier) {
        this.statModifier = statModifier;
    }

    /**
     * Gets the stat modifier from this item.
     *
     * @return this item's stat modifier.
     */
    public StatModifier getStatModifier() {
        return statModifier;
    }

    /**
     * Sets the attribute modifier for this item. It gets called whenever calculating player attributes.
     *
     * @param attributeModifier the attribute modifier you want this item to have.
     */
    public void setAttributeModifier(AttributeModifier attributeModifier) {
        this.attributeModifier = attributeModifier;
    }

    /**
     * Gets the attribute modifier from this item.
     *
     * @return this item's attribute modifier.
     */
    public AttributeModifier getAttributeModifier() {
        return attributeModifier;
    }

    /**
     * Gets the equip type of this equippable.
     *
     * @return this equippable's equippable type.
     */
    public EquippableType getType() {
        return type;
    }

    /**
     * Sets the material of this equippable, only allows materials that correspond to the equppable's type.
     *
     * @param material the org.bukkit.Material you want this item to have.
     */
    @Override
    public void setMaterial(Material material) {
       assert type.isAllowed(material) : " Material is not allowed.";
       super.setMaterial(material);
    }

    @Override
    public void onEquip(ArmorEquipEvent event) {
        // ...
    }

    @Override
    public void onUnequip(ArmorEquipEvent event) {
        // ...
    }

    @Override
    public void onPlayerHurt(EntityDamageEvent event) {
        // ...
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {
        // ...
    }

    @Override
    public void onPassiveEffect(PluginPlayer player) {
        // ...
    }

    @Override
    public void onRapidPassiveEffect(PluginPlayer player) {
        // ...
    }
}
