package com.benzoft.countinggame.files;

import com.benzoft.countinggame.CountingGame;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public final class ConfigFile {

    private static ConfigFile file;
    private final boolean invert;
    private final List<String> blacklist;
    private final long idleBroadcast;
    private final boolean updateCheckerPermissionOnly;

    private ConfigFile() {
        CountingGame.getPlugin(CountingGame.class).saveDefaultConfig();
        final FileConfiguration config = CountingGame.getPlugin(CountingGame.class).getConfig();
        idleBroadcast = config.getLong("IdleBroadcast", 900);
        invert = config.getBoolean("InvertBlacklist", false);
        blacklist = config.getStringList("Blacklist");
        updateCheckerPermissionOnly = config.getBoolean("UpdateCheckerPermissionOnly", false);
    }

    public boolean isCGWorld(final World world) {
        return invert == blacklist.contains(world.getName());
    }

    public long getIdleBroadcast() {
        return idleBroadcast;
    }

    public boolean isUpdateCheckerPermissionOnly() {
        return updateCheckerPermissionOnly;
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
