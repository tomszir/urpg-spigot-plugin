package xyz.tomszir.urpg.___old.managers.player;

import xyz.tomszir.urpg.___old.items.Equippable;

import java.util.List;

public class PlayerStats extends CustomPlayerExtension {

    public PlayerStats(CustomPlayer player) {
        super(player);
    }

    public double getCurrentHealth() {
        return getPlayerFile().getCurrentHealth();
    }

    public double getMaxHealth() {
        List<Equippable> equippables = getPlayer().getEquippedList();

        double maxHealth = getPlayerFile().getMaxHealth();

        for (Equippable equip : equippables)
            if (equip != null)
                maxHealth += equip.getHealth();
        for (Equippable equip : equippables)
            if (equip != null)
                maxHealth = equip.onPlayerMaxHealthCalculation(maxHealth);

        return maxHealth;
    }

    public double getDefense() {
        List<Equippable> equippables = getPlayer().getEquippedList();

        double defense = getPlayerFile().getDefense();

        for (Equippable equip : equippables)
            if (equip != null)
                defense += equip.getDefense();
        for (Equippable equip : equippables)
            if (equip != null)
                defense = equip.onPlayerDefenseCalculation(defense);

        return defense;
    }
}

