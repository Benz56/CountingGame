package com.benzoft.countinggame;

import com.benzoft.countinggame.files.DataFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class CGReward implements Comparable<CGReward> {

    private final int targetCount;
    private final List<String> rewards;

    public CGReward(final int targetCount, final String reward) {
        this(targetCount, Collections.singletonList(reward));
    }

    public CGReward(final int targetCount, final List<String> rewards) {
        this.targetCount = targetCount;
        this.rewards = rewards;
    }

    public void execute(final Player player) {
        final String count = String.valueOf(DataFile.getInstance().getCount());
        rewards.forEach(reward -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reward.substring(reward.startsWith("/") ? 1 : 0).replaceAll("%player%", player.getName()).replaceAll("%number%", count).replaceAll("%count%", count)));

    }

    public int getTargetCount() {
        return targetCount;
    }

    public List<String> getRewards() {
        return rewards;
    }

    @Override
    public String toString() {
        return "&7&l● &e" + targetCount;
    }

    @Override
    public int compareTo(@Nonnull final CGReward o) {
        return targetCount - o.targetCount;
    }
}
