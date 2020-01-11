package xyz.tomszir.urpg.___old.items;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.tomszir.urpg.___old.interfaces.PlayerEventListener;
import xyz.tomszir.urpg.___old.interfaces.PlayerStatModifier;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ItemSet implements PlayerStatModifier, PlayerEventListener {

    public static HashMap<String, List<Equippable>> items = new HashMap<>();

    private String name;
    private ItemEffect effect;

    public ItemSet(Equippable equippable, String name, ItemEffect effect) {
        this.name = name;
        this.effect = effect;

        addEquippable(equippable);
    }

    public ItemEffect getEffect() {
        return effect;
    }

    public void setEffect(ItemEffect effect) {
        this.effect = effect;
    }

    public List<Equippable> getRequired() {
        return items.get(getName());
    }

    public void addEquippable(Equippable equippable) {
        List<Equippable> equippables = getRequired();

        if (equippables == null) {
            items.put(getName(), Collections.singletonList(equippable));
        } else {
            equippables.add(equippable);
            items.put(getName(), equippables);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return getName();
    }

    public boolean isActive(CustomPlayer player) {
        for (Equippable item : player.getEquipment().getAll())
            if (!getRequired().contains(item))
                return false;
        return true;
    }

    @Override
    public double onPlayerStrengthCalculation(double value) {
        return value;
    }

    @Override
    public double onPlayerDefenseCalculation(double value) {
        return value;
    }

    @Override
    public double onPlayerMaxHealthCalculation(double value) {
        return value;
    }

    @Override
    public double onPlayerDexterityCalculation(double value) {
        return value;
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event, CustomPlayer player) {
        getEffect().onPlayerMove(event, player);
    }

    @Override
    public void onDamageReceived(EntityDamageEvent event, CustomPlayer player) {
        getEffect().onDamageReceived(event, player);
    }

    @Override
    public void onPassiveEffect(CustomPlayer player) {
        getEffect().onPassiveEffect(player);
    }

    @Override
    public void onRapidPassiveEffect(CustomPlayer player) {
        getEffect().onRapidPassiveEffect(player);
    }
}
