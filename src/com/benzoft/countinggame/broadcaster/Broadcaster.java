package com.benzoft.countinggame.broadcaster;

import com.benzoft.countinggame.CountingGame;
import org.bukkit.Bukkit;

public class Broadcaster {

    private final CountingGame countingGame;

    private int taskId;
    private BroadcasterTask task;

    public Broadcaster(final CountingGame countingGame) {
        this.countingGame = countingGame;
    }

    /**
     * Start the broadcaster.
     */
    public void start() {
        this.task = new BroadcasterTask(this.countingGame.getConfig().getLong("IdleBroadcast", 450));
        this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.countingGame, this.task, 0, 20);
    }

    /**
     * Stop the broadcaster.
     */
    public void stop() {
        Bukkit.getScheduler().cancelTask(this.taskId);
    }

    /**
     * Called when the count rises from a player participating.
     */
    public BroadcasterTask getTask() {
        return this.task;
    }
}
