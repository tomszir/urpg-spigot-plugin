package xyz.tomszir.urpg.item;

public class StatModifier {

    // TODO: Write JavaDocs.
    // TODO: Implement all stats.

    /**
     * Stats:
     *  Defense - Reduces incoming damage;
     *  Damage - The amount of damage you deal to an enemy;
     *  Attack Speed - The rate at which you deal damage;
     *  Dodge Chance - The % chance of you dodging an attack;
     *  Critical Strike Chance - The % chance of you dealing 150% damage;
     *  Health - The maximum health of a player;
     *  Life Steal - The % of which damage gets turned into health.
     *  Life Regeneration - The % of max. health that gets regenerated every 5s.
     */

    private double defense;

    // private double damage;
    // private double dodgeChance;
    // private double attackSpeed;
    // private double criticalChance;
    // private double maximumHealth;
    // private double lifeSteal;
    // private double lifeRegeneration;

    public String toString() {
        return "";
    }

    public StatModifier setDefense(double defense) {
        this.defense = defense;
        return this;
    }

    public double getDefense() {
        return defense;
    }

    public double onDefenseCalculation(double defense) {
        return defense;
    }
}
