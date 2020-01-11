package xyz.tomszir.urpg.___old.enums;

import xyz.tomszir.urpg.___old.util.ColorUtil;

public enum ItemRarity {
    COMMON("Common", ColorUtil.WHITE),
    UNCOMMON("Uncommon", ColorUtil.GREEN),
    RARE("Rare", ColorUtil.AQUA),
    EPIC("Epic", ColorUtil.PURPLE),
    LEGENDARY("Legendary", ColorUtil.DARK_YELLOW);

    private String label;
    private String color;

    ItemRarity(String label, String color) {
        this.label = label;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getLabel() {
        return label;
    }

    public String toString() {
        return  getColor() + ColorUtil.BOLD + getLabel();
    }
}
