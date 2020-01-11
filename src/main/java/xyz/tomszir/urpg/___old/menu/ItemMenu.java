package xyz.tomszir.urpg.___old.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import xyz.tomszir.urpg.___old.enums.ItemMenuSize;
import xyz.tomszir.urpg.___old.events.MenuItemClickEvent;

public class ItemMenu {

    public static final Material FILLER_ITEM = null;

    private String title;
    private ItemMenuSize size;
    private MenuItem[] items;

    private ItemMenu parent;

    public ItemMenu(String title, ItemMenuSize size) {
        this.title = title;
        this.size = size;
        this.items = new MenuItem[size.getSize()];
    }

    public String getTitle() {
        return title;
    }

    public ItemMenuSize getSize() {
        return size;
    }

    public void setParent(ItemMenu parent) {
        this.parent = parent;
    }

    public ItemMenu getParent() {
        return parent;
    }

    public boolean hasParent() {
        return false;
    }

    public ItemMenu setItem(int slot, MenuItem item) {
        this.items[slot] = item;
        return this;
    }

    public ItemMenu setItem(int row, int slot, MenuItem item) {
        return setItem(9 * row + slot, item);
    }

    // TODO: Make this cleaner.
    public void openInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(new MenuHolder(this, Bukkit.createInventory(player, size.getSize())), size.getSize(), title);

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                inventory.setItem(i, items[i].getItemStack(player));
            } else {
                inventory.setItem(i, null);
            }
        }

        player.openInventory(inventory);
    }

    public void onInventoryClick(InventoryClickEvent event) {
        int slot = event.getRawSlot();

        if (slot < 0) return;
        if (slot > items.length) return;
        if (items[slot] == null) return;

        Player player = (Player) event.getWhoClicked();
        MenuItemClickEvent itemClickEvent = new MenuItemClickEvent(player);

        items[slot].onClick(itemClickEvent);

        if (itemClickEvent.isBacking() && hasParent()) {
            parent.openInventory(player);
        } else if (itemClickEvent.isClosing()) {
            player.closeInventory();
        }
    }
}
