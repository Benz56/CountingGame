package com.benzoft.countinggame.commands;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class ASubCommand {

    private final String name;
    private final Set<String> aliases;
    private final CGPerm permission;
    private final boolean playerOnly;

    public ASubCommand(final String name, final CGPerm permission, final boolean playerOnly, final String... aliases) {
        this.name = name.toLowerCase();
        this.permission = permission;
        this.playerOnly = playerOnly;
        this.aliases = new HashSet<>(Arrays.asList(aliases));
    }

    public abstract void onCommand(final Player player, final String[] args);

    public final String getName() {
        return this.name;
    }

    public final Set<String> getAliases() {
        return this.aliases;
    }

    public CGPerm getPermission() {
        return this.permission;
    }

    public boolean playerOnly() {
        return this.playerOnly;
    }

    public boolean checkPermission(final Player player, final CGPerm permission) {
        if (permission != null && !permission.checkPermission(player)) {
            StringUtil.msgSend(player, MessagesFile.getInstance().getInvalidPermission());
            return false;
        } else return true;
    }
}
