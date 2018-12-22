package com.benzoft.countinggame;

import org.bukkit.entity.Player;

public enum CGPerm {
    COMMANDS("countinggame.commands"),
    COMMANDS_HELP("countinggame.commands.help"),
    COMMANDS_UNMUTE("countinggame.commands.unmute"),
    COMMANDS_MUTE("countinggame.commands.mute"),
    COMMANDS_REWARDS("countinggame.commands.rewards"),
    COMMANDS_NEXT("countinggame.commands.next"),
    CG_ADMIN("countinggame.admin");

    private final String permission;

    CGPerm(final String permission) {
        this.permission = permission;
    }

    public boolean checkPermission(final Player player) {
        return player == null || player.isOp() || player.hasPermission(this.permission);
    }
}
