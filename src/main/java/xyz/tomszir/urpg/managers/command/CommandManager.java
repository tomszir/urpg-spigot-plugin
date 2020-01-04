package xyz.tomszir.urpg.managers.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import xyz.tomszir.urpg.uRPG;
import xyz.tomszir.urpg.util.ColorUtil;
import xyz.tomszir.urpg.util.MessageUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class CommandManager implements CommandExecutor {

    private HashMap<String, Command> commands;

    public CommandManager() {
        this.commands = new HashMap<String, Command>();

        registerAllCommands();
    }

    public void addCommand(Command command) {
        commands.put(command.getLabel(), command);
    }

    public Command getCommand(String identifier) {
        return commands.get(identifier);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
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

    // Adds every Command class to the CommandManager from the "xyz.tomszir.urpg.commands" package;
    public void registerAllCommands() {
        Reflections reflections = new Reflections("xyz.tomszir.urpg.commands");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

        // Add all found commands to the command manager;
        for (Class<? extends Command> cls : classes) {
            try {
                Command command = cls.newInstance();

                uRPG.getInstance().debug("Loaded command - " + command.getLabel());

                addCommand(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
