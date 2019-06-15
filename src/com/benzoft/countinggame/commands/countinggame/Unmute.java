package com.benzoft.countinggame.commands.countinggame;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.commands.AbstractSubCommand;
import com.benzoft.countinggame.files.DataFile;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Unmute extends AbstractSubCommand {

    Unmute(final String name, final CGPerm permission, final boolean playerOnly, final String... aliases) {
        super(name, permission, playerOnly, aliases);
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        if (args.length == 1) {
            if (player != null) {
                StringUtil.msgSend(player, !DataFile.getInstance().unmutePlayer(player) ? MessagesFile.getInstance().getNotMuted() : MessagesFile.getInstance().getUnmuted());
            } else StringUtil.msgSend(null, MessagesFile.getInstance().getPlayerOnly());
        } else if (args.length == 2) {
            if (CGPerm.ADMIN.checkPermission(player)) {
                final Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    final boolean unmuted = DataFile.getInstance().unmutePlayer(target);
                    if (unmuted) StringUtil.msgSend(target, MessagesFile.getInstance().getUnmuted());
                    StringUtil.msgSend(player, !unmuted ? MessagesFile.getInstance().getNotMutedPlayer().replaceAll("%player%", target.getName()) : MessagesFile.getInstance().getUnmutedPlayer().replaceAll("%player%", target.getName()));
                } else StringUtil.msgSend(player, MessagesFile.getInstance().getUnknownPlayer());
            } else StringUtil.msgSend(player, MessagesFile.getInstance().getInvalidPermission());
        } else StringUtil.msgSend(player, MessagesFile.getInstance().getInvalidArguments());
    }
}
