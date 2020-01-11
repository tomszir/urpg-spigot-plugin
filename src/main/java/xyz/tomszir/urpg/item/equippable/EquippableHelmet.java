package xyz.tomszir.urpg.item.equippable;

import xyz.tomszir.urpg.item.BaseEquippable;
import xyz.tomszir.urpg.item.EquippableType;

public class EquippableHelmet extends BaseEquippable {
    /**
     * Class constructor.
     *
     * @param identifier the unique identifier you want this equippable to have.
     */
    public EquippableHelmet(String identifier) {
        super(identifier, EquippableType.HELMET);
    }
}
