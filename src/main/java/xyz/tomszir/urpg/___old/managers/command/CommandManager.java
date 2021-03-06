package xyz.tomszir.urpg.___old.managers.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import xyz.tomszir.urpg.___old.util.ColorUtil;
import xyz.tomszir.urpg.___old.util.MessageUtil;

import java.util.Arrays;
import java.util.HashMap;

public class CommandManager implements CommandExecutor {

    private HashMap<String, Command> commands;

    public CommandManager() {
        this.commands = new HashMap<>();

        register();
    }

    public Command getCommand(String identifier) {
        return commands.get(identifier);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public void addCommand(Command command) {
        commands.put(command.getLabel(), command);
    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String... args) {
        if (!(sender instanceof Player)) return false;

        MessageUtil message = new MessageUtil(sender);

        if (args.length == 0) {
            message.sendInfo(ColorUtil.RED + "Not enough arguments! Please use '/urpg help' for more info.");
            return false;
        }

        // Get the command name and try to get the command;
        String commandLabel = args[0];
        Command issuedCommand = getCommand(commandLabel);

        // Issued command could not be found;
        if (issuedCommand == null) {
            message.sendInfo(ColorUtil.RED + "Command not found! Please use '/urpg help' for more info.");
            return false;
        }

        // Issued command was found, attempt to execute;
        try {
            issuedCommand.execute(sender, message, Arrays.copyOfRange(args, 1, args.length));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void register() {
        Reflections commands = new Reflections("xyz.tomszir.urpg.old.commands");

        try {
            for (Class<? extends Command> cls : commands.getSubTypesOf(Command.class))
                addCommand(cls.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
