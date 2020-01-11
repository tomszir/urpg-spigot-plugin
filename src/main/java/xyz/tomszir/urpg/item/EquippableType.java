package xyz.tomszir.urpg.item;

import org.bukkit.Material;

import java.util.List;

public enum EquippableType {

    // TODO: Write JavaDoc.

    HELMET(0, EquippableItems.HELMETS),

    CHESTPLATE(0, EquippableItems.CHESTPLATES),

    LEGGINGS(0, EquippableItems.LEGGINGS),

    BOOTS(0, EquippableItems.BOOTS);

    private int slot;

    private List<Material> allowedMaterials;

    EquippableType(int slot, List<Material> allowedMaterials) {
        this.slot = slot;
        this.allowedMaterials = allowedMaterials;
    }

    public int getEquipSlot() {
        return slot;
    }

    public boolean isAllowed(Material material) {
        return allowedMaterials.contains(material);
    }
}
