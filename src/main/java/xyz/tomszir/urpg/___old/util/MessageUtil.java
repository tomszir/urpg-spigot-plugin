package xyz.tomszir.urpg.___old.util;

import org.bukkit.command.CommandSender;

public class MessageUtil {

    public static final String INFO_MESSAGE_PREFIX = ColorUtil.AQUA + ColorUtil.BOLD + "[uRPG]";

    private CommandSender sender;

    public MessageUtil(CommandSender sender) {
        this.sender = sender;
    }

    public void send(String message) {
       sender.sendMessage(ColorUtil.parse(message));
    }

    public void sendInfo(String message) {
        send(INFO_MESSAGE_PREFIX + " " + ColorUtil.RESET + message);
    }
}
