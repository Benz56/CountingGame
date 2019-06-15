package com.benzoft.countinggame.files;

import com.benzoft.countinggame.CGReward;
import com.benzoft.countinggame.utils.StringUtil;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class RewardsFile extends AbstractFile {

    private static RewardsFile file;
    @Getter
    private List<CGReward> rewards;

    private RewardsFile() {
        super("rewards.yml");
    }

    public static RewardsFile getInstance() {
        file = file == null ? new RewardsFile() : file;
        return file;
    }

    public static void reload() {
        file = new RewardsFile();
        file.setDefaults();
    }

    @Override
    public void setDefaults() {
        setHeader("This is the rewards configuration file for The Counting Game.",
                "The structure is Rewards.'x' where x is the count that will trigger the rewards.",
                "You can add as many commands as you wish for a certain count. Take a look at the defaults for reference.",
                "",
                "Placeholders:",
                "  - %player% is the players name.",
                "  - %count% is the current count/number.",
                "  - %number% is the current count/number.");

        final boolean hasRewards = !containsSection("Rewards");
        conditionalAdd("Rewards.20", "/broadcast &e%player% just reached %count%!", hasRewards);
        conditionalAdd("Rewards.50", Arrays.asList(
                "/broadcast &e%player% just reached %count% and was rewarded 50$!",
                "/eco give %player% 50"), hasRewards);
        conditionalAdd("Rewards.100", Arrays.asList(
                "/broadcast &e%player% just reached %count% and was rewarded 100$!",
                "/eco give %player% 100"), hasRewards);
        save();
        rewards = getConfig().getConfigurationSection("Rewards").getKeys(false).stream().flatMap(key -> {
            try {
                if (getConfig().get("Rewards." + key) instanceof String) {
                    return Stream.of(new CGReward(Integer.parseInt(key), getConfig().getString("Rewards." + key)));
                } else return Stream.of(new CGReward(Integer.parseInt(key), getConfig().getStringList("Rewards." + key)));
            } catch (final NumberFormatException e) {
                StringUtil.msgSend(null, "&cKey is not a number in section: Rewards." + key);
                return Stream.empty();
            }
        }).sorted().collect(Collectors.toList());
    }

    public CGReward getReward(final int count) {
        return rewards.stream().filter(reward -> reward.getTargetCount() == count).findFirst().orElse(null);
    }
}
