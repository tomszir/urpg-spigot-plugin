package xyz.tomszir.urpg.___old.items.itemsets;

import xyz.tomszir.urpg.___old.items.Equippable;
import xyz.tomszir.urpg.___old.items.ItemEffect;
import xyz.tomszir.urpg.___old.items.ItemSet;

public class MinerSet extends ItemSet {
    public MinerSet(Equippable equippable) {
        super(equippable, "Miner's Set", new ItemEffect("Haste", "Grants you permanent Haste IV."));
    }
}
