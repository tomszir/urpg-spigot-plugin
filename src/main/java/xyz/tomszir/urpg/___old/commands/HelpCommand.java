package xyz.tomszir.urpg.___old.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.tomszir.urpg.___old.enums.ItemMenuSize;
import xyz.tomszir.urpg.___old.managers.command.Command;
import xyz.tomszir.urpg.___old.menu.ItemMenu;
import xyz.tomszir.urpg.___old.menu.MenuItem;
import xyz.tomszir.urpg.___old.util.MessageUtil;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");

        setDescription("The default help command.");
    }

    @Override
    public void execute(CommandSender sender, MessageUtil message, String... args) throws Exception {
        ItemMenu menu = new ItemMenu("Test Item Menu", ItemMenuSize.ONE_ROW);

        menu.setItem(2, new MenuItem("Some randmo item", Material.FEATHER))
            .openInventory((Player) sender);
    }
}
