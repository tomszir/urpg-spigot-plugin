package xyz.tomszir.urpg.items;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.ChatPaginator;
import xyz.tomszir.urpg.managers.player.uRPGPlayer;
import xyz.tomszir.urpg.uRPG;
import xyz.tomszir.urpg.util.ColorUtil;

import java.util.*;

public class EquippableItem extends BaseItem {

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

                lore.add(ColorUtil.formatColor("&6Effect: " + name));
                lore.addAll(Arrays.asList(ChatPaginator.wordWrap(ColorUtil.formatColor("&7" + description), 36)));
                lore.add("");
            }
        }

        lore.addAll(meta.getLore());

        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
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
}
