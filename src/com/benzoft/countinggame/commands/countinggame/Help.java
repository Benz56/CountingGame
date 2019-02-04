package com.benzoft.countinggame.commands.countinggame;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.commands.AbstractSubCommand;
import com.benzoft.countinggame.utils.JsonUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.entity.Player;

public class Help extends AbstractSubCommand {

    public Help(final String name, final CGPerm permission, final boolean playerOnly, final String... aliases) {
        super(name, permission, playerOnly, aliases);
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        JsonUtil.sendJSON(player, "&9&m&l----------------------------------");
        JsonUtil.sendJSON(player, " &7&oBelow is a list of all Counting Game commands:");
        JsonUtil.sendJSON(player, " &7&l● &e/cg [help]", "&eOpens this help page.\n\n&7Click to run.", "/countinggame help", ClickEvent.Action.RUN_COMMAND);
        JsonUtil.sendJSON(player, " &7&l● &e/cg next", "&eShows which number is next.\n\n&7Click to run.", "/countinggame next", ClickEvent.Action.RUN_COMMAND);
        JsonUtil.sendJSON(player, " &7&l● &e/cg rewards [page]", "&eShows a list of reward targets.\n\n&7Click to run.", "/countinggame rewards", ClickEvent.Action.RUN_COMMAND);
        JsonUtil.sendJSON(player, " &7&l● &e/cg mute [player]", "&eMute all Counting Game messages.\n\n&7Click to suggest.", "/countinggame mute ", ClickEvent.Action.SUGGEST_COMMAND);
        JsonUtil.sendJSON(player, " &7&l● &e/cg unmute [player]", "&eUnmute all Counting Game messages.\n\n&7Click to suggest.", "/countinggame unmute ", ClickEvent.Action.SUGGEST_COMMAND);
        JsonUtil.sendJSON(player, "&9&m&l----------------------------------");
    }
}
