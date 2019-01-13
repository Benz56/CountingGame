package com.benzoft.countinggame.commands;

import com.benzoft.countinggame.CGPerm;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSubCommand {

    private final String name;
    private final Set<String> aliases;
    private final CGPerm permission;
    private final boolean playerOnly;

    protected AbstractSubCommand(final String name, final CGPerm permission, final boolean playerOnly, final String... aliases) {
        this.name = name.toLowerCase();
        this.permission = permission;
        this.playerOnly = playerOnly;
        this.aliases = new HashSet<>(Arrays.asList(aliases));
    }

    public abstract void onCommand(final Player player, final String[] args);

    public final String getName() {
        return name;
    }

    final Set<String> getAliases() {
        return aliases;
    }

    public CGPerm getPermission() {
        return permission;
    }

    public boolean playerOnly() {
        return playerOnly;
    }
}
