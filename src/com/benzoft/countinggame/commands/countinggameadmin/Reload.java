package com.benzoft.countinggame.commands.countinggameadmin;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.CountingGame;
import com.benzoft.countinggame.commands.ASubCommand;
import com.benzoft.countinggame.files.ConfigFile;
import com.benzoft.countinggame.files.DataFile;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.entity.Player;

public class Reload extends ASubCommand {

    private final CountingGame countingGame;

    public Reload(final CountingGame countingGame, final String name, final CGPerm permission, final boolean playerOnly, final String... aliases) {
        super(name, permission, playerOnly, aliases);
        this.countingGame = countingGame;
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        this.countingGame.getBroadcaster().stop();
        this.countingGame.getBroadcaster().start();
        ConfigFile.reload(this.countingGame);
        MessagesFile.reload();
        DataFile.getInstance().save();
        DataFile.reload();
        StringUtil.msgSend(player, MessagesFile.getInstance().getConfigReload());
    }
}
