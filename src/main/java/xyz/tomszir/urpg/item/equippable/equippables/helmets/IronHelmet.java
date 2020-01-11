package xyz.tomszir.urpg.item.equippable.equippables.helmets;

import org.bukkit.Material;
import xyz.tomszir.urpg.item.StatModifier;
import xyz.tomszir.urpg.item.equippable.EquippableHelmet;

public class IronHelmet extends EquippableHelmet {
    public IronHelmet() {
        super("IRON_HELMET");

        setName("Iron Helmet");
        setMaterial(Material.IRON_HELMET);

        StatModifier statModifier = new StatModifier()
            .setDefense(10);

        setStatModifier(statModifier);
    }

    @Override
    public String getEffectDescription() {
        return "Effect: <name>\n<description>";
    }
}
