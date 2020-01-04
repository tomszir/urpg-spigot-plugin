package xyz.tomszir.urpg.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.tomszir.urpg.enums.ItemMenuSize;
import xyz.tomszir.urpg.managers.command.Command;
import xyz.tomszir.urpg.menu.ItemMenu;
import xyz.tomszir.urpg.menu.MenuItem;
import xyz.tomszir.urpg.util.MessageUtil;

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
