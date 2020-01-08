package xyz.tomszir.urpg.items;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.ChatPaginator;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.uRPG;
import xyz.tomszir.urpg.util.ColorUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EquippableItem extends BaseItem {

    // TODO: Set bonuses, auras, onDamage, onHit, modifiers, "growth";

    public static final List<Material> HELMETS = List.of(
            Material.LEATHER_HELMET, Material.IRON_HELMET, Material.GOLDEN_HELMET, Material.DIAMOND_HELMET, Material.CHAINMAIL_HELMET
    );
    public static final List<Material> CHESTPLATES = List.of(
            Material.LEATHER_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE
    );
    public static final List<Material> LEGGINGS = List.of(
            Material.LEATHER_LEGGINGS, Material.IRON_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.CHAINMAIL_LEGGINGS
    );
    public static final List<Material> BOOTS = List.of(
            Material.LEATHER_BOOTS, Material.IRON_BOOTS, Material.GOLDEN_BOOTS, Material.DIAMOND_BOOTS, Material.CHAINMAIL_BOOTS
    );
    public static final List<Material> EQUIPPABLES = Stream.of(HELMETS, CHESTPLATES, LEGGINGS, BOOTS)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    private double defense = 0;
    private double health = 0;
    private double strength = 0;
    private double dexterity = 0;
    private double intelligence = 0;

    private List<String> stats;
    private HashMap<String, String> effects;

    public EquippableItem(String identifier, Material material) {
        super("EQUIPPABLE_" + identifier, material);

        this.stats = new ArrayList<>();
        this.effects = new HashMap<>();
    }

    public void addEffect(String name, String effect) {
        uRPG.getInstance().debug("Adding lore!");
        effects.put(name, effect);
    }

    public void addStatLore(String field, String value) {
        stats.add(ColorUtil.formatColor("&7" + field + ": &a+" + value));
    }

    public void setDefense(double defense) {
        addStatLore("Defense", String.format("%d", (int) defense));
        this.defense = defense;
    }

    public double getDefense() {
        return defense;
    }

    public void setHealth(double health) {
        addStatLore("Health", String.format("%d", (int) health));
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

    public boolean canBeEquipped(int slot) {
        return slot == EquippableItem.getEquipSlot(getMaterial());
    }

    public ItemStack getItemStack(int amount) {
        ItemStack stack = super.getItemStack(amount);
        ItemMeta meta = stack.getItemMeta();

        if (meta == null)
            return stack;

        // TODO: Make the stats on the lore prettier and cleaner.

        List<String> lore = new ArrayList<>(stats);

        lore.add("");

        if (effects.size() > 0) {
            for (Map.Entry<String, String> effect : effects.entrySet()) {
                String name = effect.getKey();
                Object description = effect.getValue();

                lore.add(ColorUtil.formatColor("&6Effect: &e" + name));
                lore.addAll(Arrays.asList(ChatPaginator.wordWrap(ColorUtil.formatColor("&7" + description), 36)));
                lore.add("");
            }
        }

        lore.addAll(meta.getLore());

        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        // This is here just so the custom armor bar can work;
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,
            new AttributeModifier("generic.toughness", -100.0, AttributeModifier.Operation.ADD_NUMBER));
        stack.setItemMeta(meta);

        return stack;
    }

    // These are run when calculating the final stat value;

    public double onDefenseCalculation(double defense) {
        return defense;
    }

    public double onHealthCalculation(double health) {
        return health;
    }

    public double onStrengthCalculation(double strength) {
        return strength;
    }

    public double onDexterityCalculation(double dexterity) {
        return dexterity;
    }

    // TODO: Implement onEquip & onUnequip events.

    public void onEquip(uRPGPlayer player) {
        // This runs when the equipment is equipped
    }

    public void onUnequip(uRPGPlayer player) {
        // This runs when the equipment is unequipped
    }

    public void onPlayerMove(PlayerMoveEvent event, uRPGPlayer player) {
        // This runs whenever the player moves
    }

    public void onPassiveEffect(uRPGPlayer player) {
        // This runs every 5 seconds
    }

    public void onRapidPassiveEffect(uRPGPlayer player) {
        // This runs every second
    }

    public static int getEquipSlot(Material material) {
        if (HELMETS.contains(material))
            return 39;
        if (CHESTPLATES.contains(material))
            return 38;
        if (LEGGINGS.contains(material))
            return 37;
        if (BOOTS.contains(material))
            return 36;
        return -1;
    }
}
