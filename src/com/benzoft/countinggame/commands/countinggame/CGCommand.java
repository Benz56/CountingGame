package com.benzoft.countinggame.commands.countinggame;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.CountingGame;
import com.benzoft.countinggame.commands.AbstractCommand;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.entity.Player;

public class CGCommand extends AbstractCommand {

    public CGCommand(final CountingGame countingGame, final String commandName) {
        super(countingGame, commandName,
                new Help("help", CGPerm.COMMANDS_HELP, false, "h"),
                new Mute("mute", CGPerm.COMMANDS_MUTE, true, "m"),
                new Unmute("unmute", CGPerm.COMMANDS_MUTE, true, "umute", "unm", "um"),
                new Rewards("rewards", CGPerm.COMMANDS_REWARDS, false, "rew", "r"),
                new Next("next", CGPerm.COMMANDS_NEXT, false, "n")
        );
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        if (CGPerm.COMMANDS_HELP.checkPermission(player)) {
            getSubCommands().stream().filter(subCommand -> subCommand.getName().equalsIgnoreCase("help")).findFirst().ifPresent(subCommand -> subCommand.onCommand(player, args));
        } else StringUtil.msgSend(player, MessagesFile.getInstance().getInvalidPermission());
    }
}
