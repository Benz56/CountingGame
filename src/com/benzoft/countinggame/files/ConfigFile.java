package com.benzoft.countinggame.files;

import com.benzoft.countinggame.CountingGame;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ConfigFile {

    private static ConfigFile file;
    private final boolean invert;
    private final List<String> blacklist;
    private final List<CGReward> rewards;

    private ConfigFile() {
        final FileConfiguration config = CountingGame.getPlugin(CountingGame.class).getConfig();
        this.invert = config.getBoolean("InvertBlacklist", false);
        this.blacklist = config.getStringList("Blacklist");
        this.rewards = config.getConfigurationSection("Rewards").getKeys(false).stream().flatMap(key -> {
            try {
                if (config.get("Rewards." + key) instanceof String) {
                    return Stream.of(new CGReward(Integer.parseInt(key), config.getString("Rewards." + key)));
                } else return Stream.of(new CGReward(Integer.parseInt(key), config.getStringList("Rewards." + key)));
            } catch (final NumberFormatException e) {
                StringUtil.msgSend(null, "&cKey is not a number in section: Rewards." + key);
                return Stream.empty();
            }
        }).sorted().collect(Collectors.toList());
    }

    public boolean isCGWorld(final World world) {
        return this.invert == this.blacklist.contains(world.getName());
    }

    public CGReward getReward(final int count) {
        return this.rewards.stream().filter(reward -> reward.getTargetCount() == count).findFirst().orElse(null);
    }

    public List<CGReward> getRewards() {
        return this.rewards;
    }

    public static ConfigFile getInstance() {
        file = file == null ? new ConfigFile() : file;
        return file;
    }

    public static void reload(final CountingGame countingGame) {
        countingGame.reloadConfig();
        countingGame.saveDefaultConfig();
        file = new ConfigFile();
    }
}
