package com.benzoft.countinggame.commands;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.CountingGame;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class ACommand implements CommandExecutor {

    private final CountingGame countingGame;
    private final String commandName;
    private final Set<ASubCommand> subCommands;

    public ACommand(final CountingGame countingGame, final String commandName, final ASubCommand... subCommands) {
        this.countingGame = countingGame;
        this.commandName = commandName;
        this.subCommands = new HashSet<>(Arrays.asList(subCommands));
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String label, final String[] args) {
        final Player player = commandSender instanceof Player ? (Player) commandSender : null;

        if (player != null && !CGPerm.COMMANDS.checkPermission(player) && !player.isOp()) {
            StringUtil.msgSend(player, MessagesFile.getInstance().getNoCommands());
            return true;
        }

        if (args.length != 0) {
            for (final ASubCommand subCommand : this.subCommands) {
                if (!subCommand.getName().equalsIgnoreCase(args[0]) && subCommand.getAliases().stream().noneMatch(alias -> alias.equalsIgnoreCase(args[0]))) continue;
                if (!this.checkPermission(player, subCommand.getPermission())) return true;
                if (player == null && subCommand.playerOnly()) {
                    StringUtil.msgSend(null, MessagesFile.getInstance().getPlayerOnly());
                } else subCommand.onCommand(player, args);
                return true;
            }
        }

        this.onCommand(player, args);
        return true;
    }

    public abstract void onCommand(final Player player, final String[] args);

    public boolean checkPermission(final Player player, final CGPerm permission) {
        if (permission != null && !permission.checkPermission(player)) {
            StringUtil.msgSend(player, MessagesFile.getInstance().getInvalidPermission());
            return false;
        } else return true;
    }

    public CountingGame getCountingGame() {
        return this.countingGame;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public Set<ASubCommand> getSubCommands() {
        return this.subCommands;
    }
}
