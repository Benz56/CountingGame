package com.benzoft.countinggame.broadcaster;

import com.benzoft.countinggame.CountingGame;
import com.benzoft.countinggame.files.ConfigFile;
import lombok.Getter;
import org.bukkit.Bukkit;

public class Broadcaster {

    private final CountingGame countingGame;

    private Integer taskId;
    @Getter
    private BroadcasterTask task;

    public Broadcaster(final CountingGame countingGame) {
        this.countingGame = countingGame;
    }

    public void start() {
        task = new BroadcasterTask(ConfigFile.getInstance().getIdleBroadcast());
        if (ConfigFile.getInstance().getIdleBroadcast() > 0) {
            taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(countingGame, task, 0, 20);
        }
    }

    public void stop() {
        if (taskId != null) Bukkit.getScheduler().cancelTask(taskId);
        taskId = null;
    }
}
