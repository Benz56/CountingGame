package com.benzoft.countinggame.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class JsonUtil {

    private final Player player;
    private final String message;
    private final String hoverMessage;
    private final String clickEvent;
    private final ClickEvent.Action action;

    public JsonUtil(final Player player, final String message) {
        this(player, message, null, null, null);
    }

    public JsonUtil(final Player player, final String message, final String hoverMessage) {
        this(player, message, hoverMessage, null, null);
    }

    public JsonUtil(final Player player, final String message, final String hoverMessage, final String clickEvent, final ClickEvent.Action action) {
        this.player = player;
        this.message = message;
        this.hoverMessage = hoverMessage;
        this.clickEvent = clickEvent;
        this.action = action;
        this.sendMessage();
    }

    private void sendMessage() {
        if (this.player != null) {
            final TextComponent tc = new TextComponent(StringUtil.translate(this.message));
            if (this.hoverMessage != null)
                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(StringUtil.translate(this.hoverMessage)).create()));
            if (this.clickEvent != null) tc.setClickEvent(new ClickEvent(this.action, this.clickEvent));
            this.player.spigot().sendMessage(tc);
        } else Bukkit.getServer().getConsoleSender().sendMessage(StringUtil.translate(this.message.replaceAll("●", "-")));
    }
}
