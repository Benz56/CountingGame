package com.benzoft.countinggame.utils;

import com.benzoft.countinggame.files.MessagesFile;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public final class StringUtil {

    static String translate(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void msgSend(final Player player, String message) {
        message = message.replaceAll("%prefix%", MessagesFile.getInstance().getPrefix());
        if (!message.isEmpty()) {
            message = translate(message);
            final boolean actionbar = message.toLowerCase().startsWith("<actionbar>");
            if (actionbar) message = message.substring(11);
            if (player != null && actionbar) {
                ActionbarUtil.sendMessage(player, message);
            } else if (!message.contains("[\"") && !message.contains("\"]")) {
                if (player != null) {
                    player.sendMessage(message);
                } else Bukkit.getServer().getConsoleSender().sendMessage(message);
            } else {
                final BaseComponent mainComponent = new CustomJSONParser(message).parseMessage();
                if (player != null) {
                    player.spigot().sendMessage(mainComponent);
                } else Bukkit.getServer().getConsoleSender().sendMessage(mainComponent.toLegacyText());
            }
        }
    }

    public static void debug(final Object... o) {
        Bukkit.getServer().getLogger().info(Arrays.toString(o));
    }
}