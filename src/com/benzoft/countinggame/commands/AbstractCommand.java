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

public abstract class AbstractCommand implements CommandExecutor {

    private final CountingGame countingGame;
    private final String commandName;
    private final Set<AbstractSubCommand> subCommands;

    protected AbstractCommand(final CountingGame countingGame, final String commandName, final AbstractSubCommand... subCommands) {
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
            for (final AbstractSubCommand subCommand : subCommands) {
                if (!subCommand.getName().equalsIgnoreCase(args[0]) && subCommand.getAliases().stream().noneMatch(alias -> alias.equalsIgnoreCase(args[0]))) continue;
                if (!subCommand.getPermission().checkPermission(player)) {
                    StringUtil.msgSend(player, MessagesFile.getInstance().getInvalidPermission());
                    return true;
                }
                if (player == null && subCommand.playerOnly()) {
                    StringUtil.msgSend(null, MessagesFile.getInstance().getPlayerOnly());
                } else subCommand.onCommand(player, args);
                return true;
            }
        }

        onCommand(player, args);
        return true;
    }

    protected abstract void onCommand(final Player player, final String[] args);

    public CountingGame getCountingGame() {
        return countingGame;
    }

    String getCommandName() {
        return commandName;
    }

    protected Set<AbstractSubCommand> getSubCommands() {
        return subCommands;
    }
}
