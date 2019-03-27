package com.benzoft.countinggame.commands.countinggameadmin;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.CountingGame;
import com.benzoft.countinggame.commands.AbstractCommand;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.entity.Player;

public class CGACommand extends AbstractCommand {

    public CGACommand(final CountingGame countingGame, final String commandName) {
        super(countingGame, commandName,
                new Help("help", CGPerm.ADMIN, false, "h"),
                new SetCount("setcount", CGPerm.ADMIN, false, "setc", "scount", "sc"),
                new Reload(countingGame, "reload", CGPerm.ADMIN, false, "rel", "r")
        );
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        if (CGPerm.ADMIN.checkPermission(player)) {
            getSubCommands().stream().filter(subCommand -> subCommand.getName().equalsIgnoreCase("help")).findFirst().ifPresent(subCommand -> subCommand.onCommand(player, args));
        } else StringUtil.msgSend(player, MessagesFile.getInstance().getNoCommands());
    }
}
