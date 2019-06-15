package com.benzoft.countinggame.broadcaster;

import com.benzoft.countinggame.files.DataFile;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.utils.StringUtil;
import lombok.Setter;

public class BroadcasterTask implements Runnable {

    private final long idleTime;

    @Setter
    private boolean canBroadcast;
    private long currentIdleTime;

    BroadcasterTask(final long idleTime) {
        this.idleTime = idleTime;
    }

    @Override
    public void run() {
        if (canBroadcast && currentIdleTime > idleTime) {
            final String message = MessagesFile.getInstance().getIdleBroadcast().replaceAll("%nextNumber%", String.valueOf(DataFile.getInstance().getCount() + 1));
            DataFile.getInstance().getCGPlayers().forEach(player -> StringUtil.msgSend(player, message));
            canBroadcast = false;
        }
        currentIdleTime = currentIdleTime > idleTime ? 0 : currentIdleTime + 1;
    }

    public void resetIdleTime() {
        currentIdleTime = 0;
    }
}
