package xyz.tomszir.urpg.commands;

import org.bukkit.command.CommandSender;
import xyz.tomszir.urpg.managers.command.Command;
import xyz.tomszir.urpg.util.FormulaUtil;
import xyz.tomszir.urpg.util.MessageUtil;

public class DefenseCommand extends Command {

    public DefenseCommand() {
        super("defense");
    }

    @Override
    public void execute(CommandSender sender, MessageUtil message, String... args) throws Exception {
        int defense = Integer.parseInt(args[0]);
        double result = FormulaUtil.getDamageReductionFromDefense(defense);

        message.send("With &b" + defense + "&r &aDefense&r you have &b" + String.format("%.2f", result) + "%&r damage reduction!");
    }
}
