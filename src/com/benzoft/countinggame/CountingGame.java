package com.benzoft.countinggame;

import com.benzoft.countinggame.broadcaster.Broadcaster;
import com.benzoft.countinggame.commands.CommandRegistry;
import com.benzoft.countinggame.files.ConfigFile;
import com.benzoft.countinggame.files.DataFile;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.files.RewardsFile;
import com.benzoft.countinggame.listeners.PlayerChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CountingGame extends JavaPlugin {

    private Broadcaster broadcaster;

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        ConfigFile.getInstance();
        DataFile.getInstance().setDefaults();
        RewardsFile.getInstance().setDefaults();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> DataFile.getInstance().save(), 6000, 6000); // Save data file every five minutes.
        MessagesFile.getInstance().setDefaults();
        broadcaster = new Broadcaster(this);
        broadcaster.start();
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(this), this);
        CommandRegistry.registerCommands(this);
    }

    @Override
    public void onDisable() {
        broadcaster.stop();
        DataFile.getInstance().save();
    }

    public Broadcaster getBroadcaster() {
        return broadcaster;
    }
}
