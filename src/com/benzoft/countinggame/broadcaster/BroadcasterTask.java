package com.benzoft.countinggame.broadcaster;

import com.benzoft.countinggame.files.DataFile;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.utils.StringUtil;

public class BroadcasterTask implements Runnable {

    private final long idleTime;

    private long currentIdleTime;

    public BroadcasterTask(final long idleTime) {
        this.idleTime = idleTime;
    }

    @Override
    public void run() {
        if (this.currentIdleTime > this.idleTime) {
            final String message = MessagesFile.getInstance().getIdleBroadcast().replaceAll("%nextNumber%", String.valueOf(DataFile.getInstance().getCount() + 1));
            DataFile.getInstance().getCGPlayers().forEach(player -> StringUtil.msgSend(player, message));
        }
        this.currentIdleTime = this.currentIdleTime > this.idleTime ? 0 : this.currentIdleTime + 1;
    }

    public void resetIdleTime() {
        this.currentIdleTime = 0;
    }
}
