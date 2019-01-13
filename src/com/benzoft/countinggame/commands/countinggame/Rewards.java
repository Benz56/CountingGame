package com.benzoft.countinggame.commands.countinggame;

import com.benzoft.countinggame.CGPerm;
import com.benzoft.countinggame.commands.AbstractSubCommand;
import com.benzoft.countinggame.files.MessagesFile;
import com.benzoft.countinggame.files.RewardsFile;
import com.benzoft.countinggame.utils.JsonUtil;
import com.benzoft.countinggame.utils.StringUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class Rewards extends AbstractSubCommand {

    private static final int ENTRIES_PER_PAGE = 8;

    public Rewards(final String name, final CGPerm permission, final boolean playerOnly, final String... aliases) {
        super(name, permission, playerOnly, aliases);
    }

    @Override
    public void onCommand(final Player player, final String[] args) {
        int pages = (int) Math.ceil(((double) RewardsFile.getInstance().getRewards().size()) / ((double) ENTRIES_PER_PAGE));
        pages = pages == 0 ? 1 : pages;
        int page = 1;
        if (args.length == 2) {
            try {
                page = Integer.parseInt(args[1]);
                if (page > pages) throw new NumberFormatException();
            } catch (final NumberFormatException e) {
                StringUtil.msgSend(player, MessagesFile.getInstance().getInvalidArguments());
                return;
            }
        }

        StringUtil.msgSend(player, "&9&m&l-----&9 Page: &c" + page + "/" + pages + " &9&m&l-----");
        StringUtil.msgSend(player, "&7Hover over any of the\nentries to see the rewards.");
        final int startIndex = (page - 1) * ENTRIES_PER_PAGE;
        final int endIndex = startIndex + 8 > RewardsFile.getInstance().getRewards().size() ? RewardsFile.getInstance().getRewards().size() : startIndex + 8;
        RewardsFile.getInstance().getRewards().subList(startIndex, endIndex).forEach(reward -> new JsonUtil(player, reward.toString(), "&eRewards:\n" + reward.getRewards().stream().map(command -> "&7&l ● &r" + (command.startsWith("/") ? command : "/" + command)).collect(Collectors.joining("\n"))));
        if (page != pages) {
            new JsonUtil(player, "&eUse &c/cg rewards " + (page + 1) + "&e to go\n&eto the next page.", "&7Click to go to the next page.", "/cg rewards " + (page + 1), ClickEvent.Action.RUN_COMMAND);
        }
    }
}
