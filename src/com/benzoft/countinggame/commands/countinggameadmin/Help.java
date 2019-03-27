package com.benzoft.countinggame.commands.countinggameadmin;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.commands.AbstractSubCommand;
import com.benzoft.countinggame.utils.JsonUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.entity.Player;

public class Help extends AbstractSubCommand {

    Help(final String name, final CGPerm permission, final boolean playerOnly, final String... aliases) {
        super(name, permission, playerOnly, aliases);
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        JsonUtil.sendJSON(player, "&9&m&l----------------------------------");
        JsonUtil.sendJSON(player, " &7&oBelow is a list of all Counting Game Admin commands:");
        JsonUtil.sendJSON(player, " &7&l● &e/cga [help]", "&eOpens this help page.\n\n&7Click to run.", "/countinggameadmin help", ClickEvent.Action.RUN_COMMAND);
        JsonUtil.sendJSON(player, " &7&l● &e/cga setCount <number>", "&eSet the count to a new value.\n\n&7Click to suggest.", "/countinggameadmin setCount ", ClickEvent.Action.SUGGEST_COMMAND);
        JsonUtil.sendJSON(player, " &7&l● &e/cga reload", "&eReload the configuration files.\n\n&7Click to run.", "/countinggameadmin reload", ClickEvent.Action.RUN_COMMAND);
        JsonUtil.sendJSON(player, "&9&m&l----------------------------------");
    }
}
