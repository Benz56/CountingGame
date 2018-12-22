package com.benzoft.countinggame.utils;

import net.md_5.bungee.api.chat.*;

final class CustomJSONParser {

    private final BaseComponent mainComponent = new TextComponent("");
    private final String message;
    private int stringProgress = 0;

    CustomJSONParser(final String message) {
        this.message = message;
    }

    //Translate message events. ["Message","/Command"] | ["Message","Hover"] | ["Message","Command","Hover"]
    BaseComponent parseMessage() {
        while (true) {
            final int eventStart = this.message.indexOf("[\"", this.stringProgress);
            final int eventEnd = this.message.indexOf("\"]", this.stringProgress + 1);

            if (eventStart == -1 || eventEnd == -1) {
                if (!this.message.substring(this.stringProgress == 0 ? 0 : this.stringProgress + 2).isEmpty()) {
                    this.mainComponent.addExtra(this.message.substring(this.stringProgress == 0 ? 0 : this.stringProgress + 2));
                }
                break;
            }

            this.mainComponent.addExtra(this.message.substring(this.stringProgress == 0 ? 0 : this.stringProgress + 2, eventStart));
            this.stringProgress = eventEnd;

            final String[] split = this.message.substring(eventStart + 2, eventEnd).split("\",\"");
            final TextComponent extra = new TextComponent();
            String command = "";
            boolean suggest = false;
            for (int i = 0; i < split.length; i++) {
                if (i != 0) {
                    if (split[i].contains("/") && command.isEmpty()) {
                        command = split[i];
                    } else if (split[i].equalsIgnoreCase("Suggest")) {
                        suggest = true;
                    } else extra.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(split[i]).create()));
                } else extra.setText(split[i]);
            }
            if (!command.isEmpty()) {
                extra.setClickEvent(new ClickEvent(suggest ? ClickEvent.Action.SUGGEST_COMMAND : ClickEvent.Action.RUN_COMMAND, command));
            }

            this.mainComponent.addExtra(extra);
        }

        return this.mainComponent;
    }
}
