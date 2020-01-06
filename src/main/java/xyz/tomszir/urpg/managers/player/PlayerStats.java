package xyz.tomszir.urpg.managers.player;

import xyz.tomszir.urpg.items.EquippableItem;

import java.util.List;

public class PlayerStats {
    // Read-Only: Calculates final player stats.

    private uRPGPlayer player;
    private PlayerFile file;

    public PlayerStats(uRPGPlayer player) {
        this.player = player;
        this.file = player.getFile();
    }

    public double getCurrentHealth() {
        return file.getCurrentHealth();
    }

    public double getMaxHealth() {
        List<EquippableItem> equippables = player.getEquippedList();

        // TODO: Add health scaling onEquip & onUnequip

        double maxHealth = file.getMaxHealth();

        for (EquippableItem equip : equippables)
            if (equip != null)
                maxHealth += equip.getHealth();
        for (EquippableItem equip : equippables)
            if (equip != null)
                maxHealth = equip.onHealthCalculation(maxHealth);

        return maxHealth;
    }

    public double getDefense() {
        List<EquippableItem> equippables = player.getEquippedList();

        double defense = file.getDefense();

        for (EquippableItem equip : equippables)
            if (equip != null)
                defense += equip.getDefense();
        for (EquippableItem equip : equippables)
            if (equip != null)
                defense = equip.onDefenseCalculation(defense);

        return defense;
    }
}

