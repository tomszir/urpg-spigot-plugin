package xyz.tomszir.urpg.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.tomszir.urpg.enums.ItemMenuSize;
import xyz.tomszir.urpg.items.BaseItem;
import xyz.tomszir.urpg.managers.command.Command;
import xyz.tomszir.urpg.menu.ItemMenu;
import xyz.tomszir.urpg.menu.items.AddMenuItem;
import xyz.tomszir.urpg.uRPG;
import xyz.tomszir.urpg.util.MessageUtil;

public class ItemCommand extends Command {
    public ItemCommand() {
        super("items");
    }

    @Override
    public void execute(CommandSender sender, MessageUtil message, String... args) throws Exception {
        ItemMenu menu = new ItemMenu("Items", ItemMenuSize.SIX_ROWS);
        Object[] items = uRPG.getInstance().getItemManager().getItems().values().toArray();

        for (int i = 0; i < items.length; i++) {
            menu.setItem(i, new AddMenuItem(((BaseItem) items[i]).getItemStack()));
        }

        menu.openInventory((Player) sender);
    }
}
