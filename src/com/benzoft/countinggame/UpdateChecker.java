package com.benzoft.countinggame;

import com.benzoft.countinggame.files.ConfigFile;
import com.benzoft.countinggame.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class UpdateChecker implements Listener {

    private static final int ID = 64011;
    private static final Permission UPDATE_PERM = new Permission(CGPerm.UPDATE.getPermissionString(), PermissionDefault.FALSE);

    private final JavaPlugin javaPlugin;
    private final String localPluginVersion;
    private String spigotPluginVersion;

    UpdateChecker(final JavaPlugin main) {
        javaPlugin = main;
        localPluginVersion = main.getDescription().getVersion();
    }

    void checkForUpdate() {
        new BukkitRunnable() {
            @Override
            public void run() {
                //The request is executed asynchronously as to not block the main thread.
                Bukkit.getScheduler().runTaskAsynchronously(javaPlugin, () -> {
                    if (!ConfigFile.getInstance().isUpdateCheckerEnabled()) return;
                    //Request the current version of your plugin on SpigotMC.
                    try {
                        final HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + ID).openConnection();
                        connection.setRequestMethod("GET");
                        spigotPluginVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                    } catch (final IOException e) {
                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUpdate checker failed! Disabling."));
                        e.printStackTrace();
                        cancel();
                        return;
                    }

                    //Check if the requested version is the same as the one in your plugin.yml.
                    if (localPluginVersion.equals(spigotPluginVersion)) return;

                    StringUtil.msgSend(null, "&7[&9Counting Game&7] &fA new update is available at:");
                    StringUtil.msgSend(null, "&bhttps://www.spigotmc.org/resources/" + ID + "/updates");

                    //Register the PlayerJoinEvent
                    Bukkit.getScheduler().runTask(javaPlugin, () -> Bukkit.getPluginManager().registerEvents(new Listener() {
                        @EventHandler(priority = EventPriority.MONITOR)
                        public void onPlayerJoin(final PlayerJoinEvent event) {
                            final Player player = event.getPlayer();
                            if (player.hasPermission(UPDATE_PERM) || (player.isOp() && !ConfigFile.getInstance().isUpdateCheckerPermissionOnly())) {
                                StringUtil.msgSend(event.getPlayer(), "&7[&9Counting Game&7] &fA new update is available at:");
                                StringUtil.msgSend(event.getPlayer(), "&bhttps://www.spigotmc.org/resources/" + ID + "/updates");
                            }
                        }
                    }, javaPlugin));

                    //Cancel the runnable as an update is found.
                    cancel();
                });
            }
        }.runTaskTimer(javaPlugin, 0, 12_000);
    }
}
