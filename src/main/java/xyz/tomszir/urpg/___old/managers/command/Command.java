package xyz.tomszir.urpg.___old.managers.command;

import org.bukkit.command.CommandSender;
import xyz.tomszir.urpg.___old.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    private String label;
    private String description;

    private List<String> aliases;
    private List<String> permissions;

    public Command(String label) {
        this.label = label;

        this.aliases = new ArrayList<>();
        this.permissions = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void addAlias(String alias) {
        if (!hasAlias(alias))
            aliases.add(alias.toLowerCase());
    }

    public List<String> getAliases() {
        return aliases;
    }

    public boolean hasAlias(String alias) {
        return aliases.contains(alias.toLowerCase());
    }

    public void addPermission(String permission) {
        if (!hasPermission(permission))
            permissions.add(permission.toLowerCase());
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission.toLowerCase());
    }

    public abstract void execute(CommandSender sender, MessageUtil message, String... args) throws Exception;
}
