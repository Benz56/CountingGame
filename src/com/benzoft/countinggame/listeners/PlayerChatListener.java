package com.benzoft.countinggame.listeners;

import com.benzoft.countinggame.CGReward;
import com.benzoft.countinggame.CountingGame;
import com.benzoft.countinggame.files.ConfigFile;
import com.benzoft.countinggame.files.DataFile;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.files.RewardsFile;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class PlayerChatListener implements Listener {

    private final CountingGame countingGame;
    private UUID lastCounter;

    public PlayerChatListener(final CountingGame countingGame) {
        this.countingGame = countingGame;
    }

    @EventHandler
    public void chatListener(final AsyncPlayerChatEvent event) {
        if (event == null || event.getPlayer() == null) return;
        final Player sender = event.getPlayer();
        countingGame.getBroadcaster().getTask().setCanBroadcast(true);

        if (!ConfigFile.getInstance().isCGWorld(sender.getWorld())) return;

        final long number;
        try {
            number = Long.parseLong(event.getMessage().replaceAll(",", "").replaceAll("\\.", "").replaceAll(" ", ""));
        } catch (final NumberFormatException ignored) {
            return;
        }
        if (number != DataFile.getInstance().getCount() + 1) return;

        event.setCancelled(true);

        if (sender.getUniqueId().equals(lastCounter)) {
            StringUtil.msgSend(sender, MessagesFile.getInstance().getLastCounter());
            return;
        }
        lastCounter = sender.getUniqueId();

        DataFile.getInstance().incrementCount();
        countingGame.getBroadcaster().getTask().resetIdleTime();

        final String message = MessagesFile.getInstance().getCountSent().replaceAll("%player%", sender.getName()).replaceAll("%number%", String.valueOf(number)).replaceAll("%nextNumber%", String.valueOf(number + 1));
        DataFile.getInstance().getCGPlayers().forEach(player -> StringUtil.msgSend(player, message));

        final CGReward reward = RewardsFile.getInstance().getReward(DataFile.getInstance().getCount());
        if (reward != null) Bukkit.getScheduler().runTask(countingGame, () -> reward.execute(sender));
    }
}
