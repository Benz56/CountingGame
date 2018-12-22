package com.benzoft.countinggame.commands;

import com.benzoft.countinggame.CountingGame;
import com.benzoft.countinggame.commands.countinggame.CGCommand;
import com.benzoft.countinggame.commands.countinggameadmin.CGACommand;

import java.util.Arrays;

public final class CommandRegistry {

    public static void registerCommands(final CountingGame countingGame) {
        for (final ACommand command : Arrays.asList(
                new CGCommand(countingGame, "countinggame"),
                new CGACommand(countingGame, "countinggameadmin"))) {
            countingGame.getCommand(command.getCommandName()).setExecutor(command);
        }
    }
}
