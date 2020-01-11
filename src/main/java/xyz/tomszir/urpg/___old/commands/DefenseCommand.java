package xyz.tomszir.urpg.___old.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.tomszir.urpg.___old.Main;
import xyz.tomszir.urpg.___old.managers.command.Command;
import xyz.tomszir.urpg.___old.managers.player.CustomPlayer;
import xyz.tomszir.urpg.___old.util.MessageUtil;

public class DefenseCommand extends Command {

    public DefenseCommand() {
        super("defense");
    }

    @Override
    public void execute(CommandSender sender, MessageUtil message, String... args) throws Exception {
        CustomPlayer player = Main.getInstance().getPlayerManager().getPlayer(((Player) sender).getUniqueId());

        double defense = player.getStats().getDefense();
        double reduction = (1 - player.getDamageReduction()) * 100.0;

        message.send("With &b" + defense + "&r &adefense&r you have &b" + String.format("%.2f", reduction) + "%&r damage reduction!");
    }
}
