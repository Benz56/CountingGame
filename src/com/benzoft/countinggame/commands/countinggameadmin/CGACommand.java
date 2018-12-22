package com.benzoft.countinggame.commands.countinggameadmin;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.CountingGame;
import com.benzoft.countinggame.commands.ACommand;
import org.bukkit.entity.Player;

public class CGACommand extends ACommand {

    public CGACommand(final CountingGame countingGame, final String commandName) {
        super(countingGame, commandName,
                new Help("help", CGPerm.CG_ADMIN, false, "h"),
                new Reload(countingGame, "reload", CGPerm.CG_ADMIN, false, "rel", "r")
        );
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        this.getSubCommands().stream().filter(subCommand -> subCommand.getName().equalsIgnoreCase("help")).findFirst().ifPresent(subCommand -> subCommand.onCommand(player, args));
    }
}
