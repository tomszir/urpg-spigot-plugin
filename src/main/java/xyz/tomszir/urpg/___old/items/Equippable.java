package xyz.tomszir.urpg.___old.items;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.ChatPaginator;
import xyz.tomszir.urpg.___old.interfaces.PlayerEventListener;
import xyz.tomszir.urpg.___old.interfaces.PlayerStatModifier;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;
import xyz.tomszir.urpg.___old.util.ColorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Equippable extends Item implements PlayerStatModifier, PlayerEventListener {

    // TODO: Move this somewhere else.
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

    // TODO: Make it easier & cleaner to create new stats.
    private double defense = 0;
    private double health = 0;
    private double strength = 0;
    private double dexterity = 0;
    private double intelligence = 0;

    private List<String> statLore;
    private List<ItemEffect> effects;

    private ItemSet itemSet;

    public Equippable(String identifier, Material material) {
        super("EQUIPPABLE_" + identifier, material);

        this.statLore = new ArrayList<>();
        this.effects = new ArrayList<>();
    }

    public ItemSet getItemSet() {
        return itemSet;
    }

    public void setItemSet(ItemSet itemSet) {
        this.itemSet = itemSet;
    }

    public List<ItemEffect> getEffects() {
        return effects;
    }

    public void addEffect(ItemEffect effect) {
        effects.add(effect);
    }

    public List<String> getStatLore() {
        return statLore;
    }

    public void addStatLore(String name, String value) {
        statLore.add(ColorUtil.parse("&7" + name + ": &a+" + value));
    }

    public List<String> getLore(CustomPlayer player) {
        List<String> lore = new ArrayList<>(getStatLore());

        if (effects.size() != 0)
            for (ItemEffect effect : effects) {
                lore.addAll(Arrays.asList(ChatPaginator.wordWrap(effect.toString(), 36)));
                lore.add("");
            }

        lore.addAll(super.getLore(player));

        return lore;
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

    public ItemStack getItemStack(CustomPlayer player, int amount) {
        ItemStack stack = super.getItemStack(player, amount);
        ItemMeta meta = stack.getItemMeta();

        if (meta == null)
            return stack;

        Attribute attribute = Attribute.GENERIC_ARMOR_TOUGHNESS;
        AttributeModifier modifier = new AttributeModifier(attribute.name(), -100.0, AttributeModifier.Operation.ADD_NUMBER);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addAttributeModifier(attribute, modifier);
        stack.setItemMeta(meta);

        return stack;
    }

    public boolean canBeEquipped(int slot) {
        return slot == Equippable.getEquipSlot(getMaterial());
    }

    public double onPlayerDefenseCalculation(double defense) {
        return defense;
    }

    public double onPlayerMaxHealthCalculation(double health) {
        return health;
    }

    public double onPlayerStrengthCalculation(double strength) {
        return strength;
    }

    public double onPlayerDexterityCalculation(double dexterity) {
        return dexterity;
    }

    public void onEquip(CustomPlayer player) {
        // TODO: Implement player health scaling on equip...
        // ...
    }

    public void onUnequip(CustomPlayer player) {
        // TODO: Implement player health scaling on unequip...
        // ...
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event, CustomPlayer player) {
        for (ItemEffect effect : effects)
            effect.onPlayerMove(event, player);
        itemSet.onPlayerMove(event, player);
    }

    @Override
    public void onDamageReceived(EntityDamageEvent event, CustomPlayer player) {
        for (ItemEffect effect : effects)
            effect.onDamageReceived(event, player);
        itemSet.onDamageReceived(event, player);
    }

    @Override
    public void onPassiveEffect(CustomPlayer player) {
        for (ItemEffect effect : effects)
            effect.onPassiveEffect(player);
        itemSet.onPassiveEffect(player);
    }

    @Override
    public void onRapidPassiveEffect(CustomPlayer player) {
        for (ItemEffect effect : effects)
            effect.onRapidPassiveEffect(player);
        itemSet.onRapidPassiveEffect(player);
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
