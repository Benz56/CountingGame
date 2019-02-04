package com.benzoft.countinggame.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class JsonUtil {

    public static void sendJSON(final Player player, final String message) {
        sendJSON(player, message, null);
    }

    public static void sendJSON(final Player player, final String message, final String hoverMessage) {
        sendJSON(player, message, hoverMessage, null);
    }

    public static void sendJSON(final Player player, final String message, final String hoverMessage, final String clickEvent) {
        sendJSON(player, message, hoverMessage, clickEvent, ClickEvent.Action.RUN_COMMAND);
    }

    public static void sendJSON(final Player player, final String message, final String hoverMessage, final String clickEvent, final ClickEvent.Action action) {
        if (player != null) {
            final TextComponent tc = new TextComponent(StringUtil.translate(message));
            if (hoverMessage != null)
                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(StringUtil.translate(hoverMessage)).create()));
            if (clickEvent != null) tc.setClickEvent(new ClickEvent(action, clickEvent));
            player.spigot().sendMessage(tc);
        } else Bukkit.getServer().getConsoleSender().sendMessage(StringUtil.translate(message.replaceAll("●", "-")));
    }
}
