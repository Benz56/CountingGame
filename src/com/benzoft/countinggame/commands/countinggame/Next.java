package com.benzoft.countinggame.commands.countinggame;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.commands.AbstractSubCommand;
import com.benzoft.countinggame.files.DataFile;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.entity.Player;

public class Next extends AbstractSubCommand {

    Next(final String name, final CGPerm permission, final boolean playerOnly, final String... aliases) {
        super(name, permission, playerOnly, aliases);
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        StringUtil.msgSend(player, MessagesFile.getInstance().getNextNumber().replaceAll("%nextNumber%", String.valueOf(DataFile.getInstance().getCount() + 1)));
    }
}
