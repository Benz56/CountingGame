package com.benzoft.countinggame.commands.countinggameadmin;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.CountingGame;
import com.benzoft.countinggame.commands.AbstractSubCommand;
import com.benzoft.countinggame.utils.JsonUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Info extends AbstractSubCommand {

    private final CountingGame countingGame;

    Info(final CountingGame countingGame, final String name, final CGPerm permission, final boolean playerOnly, final String... aliases) {
        super(name, permission, playerOnly, aliases);
        this.countingGame = countingGame;
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        JsonUtil.sendJSON(player, "&9&m&l----------------------------------");
        JsonUtil.sendJSON(player, " &7&l● &eAuthor: &aBenz56", "&eClick to see all plugins by Benz56!", "https://www.spigotmc.org/resources/authors/benz56.171209/", ClickEvent.Action.OPEN_URL);
        JsonUtil.sendJSON(player, " &7&l● &eServer version: &a" + Bukkit.getVersion());
        JsonUtil.sendJSON(player, " &7&l● &ePlugin version: &a" + countingGame.getUpdateChecker().getLocalPluginVersion());
        JsonUtil.sendJSON(player, " &7&l● &eLatest version: &a" + (countingGame.getUpdateChecker().getSpigotPluginVersion() == null ? "&cunknown" : countingGame.getUpdateChecker().getSpigotPluginVersion()));
        if (player != null) {
            if (countingGame.getUpdateChecker().getSpigotPluginVersion() != null && !countingGame.getUpdateChecker().getLocalPluginVersion().equals(countingGame.getUpdateChecker().getSpigotPluginVersion())) {
                JsonUtil.sendJSON(player, " &7&l● &eClick here to Update", "&eOpens the plugin page on Spigot!", "https://www.spigotmc.org/resources/64011/updates", ClickEvent.Action.OPEN_URL);
            }
            JsonUtil.sendJSON(player, " &7&l● &eClick for Support", "&eClick to join the Benzoft Discord server!", "https://discordapp.com/invite/8YVeFHe", ClickEvent.Action.OPEN_URL);
        }
        JsonUtil.sendJSON(player, "&9&m&l----------------------------------", null, null, null);
    }
}
