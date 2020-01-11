package xyz.tomszir.urpg.item;

import org.bukkit.Material;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stores lists of all equippable item materials.
 */
public class EquippableItems {
    /**
     * A list of all equippable helmet materials.
     */
    public static final List<Material> HELMETS = List.of(
            Material.LEATHER_HELMET,
            Material.IRON_HELMET,
            Material.GOLDEN_HELMET,
            Material.CHAINMAIL_HELMET,
            Material.DIAMOND_HELMET,
            Material.TURTLE_HELMET,
            Material.PUMPKIN
    );

    /**
     * A lsit of all equippable chestplate materials.
     */
    public static final List<Material> CHESTPLATES = List.of(
            Material.LEATHER_CHESTPLATE,
            Material.IRON_CHESTPLATE,
            Material.GOLDEN_CHESTPLATE,
            Material.CHAINMAIL_CHESTPLATE,
            Material.DIAMOND_CHESTPLATE
    );

    /**
     * A list of all equippable leggings materials.
     */
    public static final List<Material> LEGGINGS = List.of(
            Material.LEATHER_LEGGINGS,
            Material.IRON_LEGGINGS,
            Material.GOLDEN_LEGGINGS,
            Material.CHAINMAIL_LEGGINGS,
            Material.DIAMOND_LEGGINGS
    );

    /**
     * A list of all equippable boots materials.
     */
    public static final List<Material> BOOTS = List.of(
            Material.LEATHER_BOOTS,
            Material.IRON_BOOTS,
            Material.GOLDEN_BOOTS,
            Material.CHAINMAIL_BOOTS,
            Material.DIAMOND_BOOTS
    );

    /**
     * A list of all equippable materials.
     */
    public static final List<Material> EQUIPPABLES = Stream.of(HELMETS, CHESTPLATES, LEGGINGS, BOOTS)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
}
