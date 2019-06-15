package com.benzoft.countinggame;

import lombok.Getter;
import org.bukkit.entity.Player;

public enum CGPerm {
    COMMANDS("countinggame.commands"),
    COMMANDS_HELP("countinggame.commands.help"),
    COMMANDS_UNMUTE("countinggame.commands.unmute"),
    COMMANDS_MUTE("countinggame.commands.mute"),
    COMMANDS_REWARDS("countinggame.commands.rewards"),
    COMMANDS_NEXT("countinggame.commands.next"),
    ADMIN("countinggame.admin"),
    UPDATE("countinggame.update");

    @Getter
    private final String permissionString;

    CGPerm(final String permissionString) {
        this.permissionString = permissionString;
    }

    public boolean checkPermission(final Player player) {
        return player == null || player.isOp() || player.hasPermission(permissionString);
    }
}
