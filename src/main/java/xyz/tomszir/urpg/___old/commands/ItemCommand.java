package xyz.tomszir.urpg.___old.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.enums.ItemMenuSize;
import xyz.tomszir.urpg.___old.items.Item;
import xyz.tomszir.urpg.___old.managers.command.Command;
import xyz.tomszir.urpg.___old.menu.ItemMenu;
import xyz.tomszir.urpg.___old.menu.items.AddMenuItem;
import xyz.tomszir.urpg.___old.util.MessageUtil;

public class ItemCommand extends Command {
    public ItemCommand() {
        super("items");
    }

    @Override
    public void execute(CommandSender sender, MessageUtil message, String... args) throws Exception {
        ItemMenu menu = new ItemMenu("Items", ItemMenuSize.SIX_ROWS);
        Object[] items = Main.getInstance().getItemManager().getItems().values().toArray();

        for (int i = 0; i < items.length; i++) {
            menu.setItem(i, new AddMenuItem(((Item) items[i]).getItemStack()));
        }

        menu.openInventory((Player) sender);
    }
}
