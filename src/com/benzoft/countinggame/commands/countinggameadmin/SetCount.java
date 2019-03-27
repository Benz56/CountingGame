package com.benzoft.countinggame.commands.countinggameadmin;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.commands.AbstractSubCommand;
import com.benzoft.countinggame.files.DataFile;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.entity.Player;

public class SetCount extends AbstractSubCommand {

    SetCount(final String name, final CGPerm permission, final boolean playerOnly, final String... aliases) {
        super(name, permission, playerOnly, aliases);
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        if (args.length != 2) {
            StringUtil.msgSend(player, MessagesFile.getInstance().getInvalidArguments());
            return;
        }
        final int newCount;
        try {
            newCount = Integer.parseInt(args[1]);
            if (newCount < 0) throw new NumberFormatException();
        } catch (final NumberFormatException e) {
            StringUtil.msgSend(player, MessagesFile.getInstance().getInvalidArguments());
            return;
        }
        DataFile.getInstance().setCount(newCount);
        StringUtil.msgSend(player, MessagesFile.getInstance().getCountSet().replaceAll("%count%", String.valueOf(newCount)).replaceAll("%number%", String.valueOf(newCount)));
    }
}
