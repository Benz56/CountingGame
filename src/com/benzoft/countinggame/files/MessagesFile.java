package com.benzoft.countinggame.files;

import java.io.IOException;

public final class MessagesFile extends AFile {

    private static MessagesFile file;

    //General
    private String prefix;
    private String invalidPermission;
    private String noCommands;
    private String unknownPlayer;
    private String playerOnly;
    private String invalidArguments;

    //CountingGame
    private String countSent;
    private String lastCounter;
    private String idleBroadcast;
    private String muted;
    private String mutedPlayer;
    private String alreadyMuted;
    private String alreadyMutedPlayer;
    private String unmuted;
    private String unmutedPlayer;
    private String notMuted;
    private String notMutedPlayer;
    private String nextNumber;

    //Admin
    private String configReload;

    private MessagesFile() {
        super("messages.yml");
        this.setDefaults();
    }

    @Override
    public void setDefaults() {
        this.setHeader(
                "This is the Message file for all CountingGame messages.",
                "",
                "All messages are fully customizable and support color codes, formatting and ASCII symbols.",
                "Set the Prefix and use %prefix% to add the corresponding prefix to a message.",
                "Leave a message blank ('') to disable it.",
                "",
                "You can also create messages with Hover and Click events. Syntax options: (Space between comma and quote is NOT allowed)",
                " - [\"Message\",\"/Command\"]",
                " - [\"Message\",\"Hover\"]",
                " - [\"Message\",\"/Command\",\"Hover\"]",
                " - [\"Message\",\"/Command\",\"Suggest\"]",
                " - [\"Message\",\"/Command\",\"Hover\",\"Suggest\"]",
                "You can add as many events to a message as you want. Example:",
                "'%prefix% &cInvalid arguments! [\"&c&n&oHelp\",\"/gps help\",\"&aClick to get help!\"]'",
                "The \"Suggest\" tag is used if the click event should suggest the command. Default is Run.",
                "");

        this.prefix = (String) this.add("Prefix", "&7[&9Counting Game&7]");
        this.invalidPermission = (String) this.add("Messages.General.InvalidPermission", "%prefix% &cYou do not have permission to do this!");
        this.noCommands = (String) this.add("Messages.General.NoCommands", "Unknown command. Type \"/help\" for help.");
        this.unknownPlayer = (String) this.add("Messages.General.UnknownPlayer", "%prefix% &cUnknown Player!");
        this.playerOnly = (String) this.add("Messages.General.PlayerOnly", "%prefix% &cCommand can only be used as a Player!");
        this.invalidArguments = (String) this.add("Messages.General.InvalidArguments", "%prefix% &cInvalid arguments! [\"&c&n&oHelp\",\"/countinggame help\",\"&aClick to get help!\"]");

        this.countSent = (String) this.add("Messages.CountingGame.CountSent", "%prefix% &a%player%&e counted &a%number%! &eNext number is &a%nextNumber%!");
        this.lastCounter = (String) this.add("Messages.CountingGame.LastCounter", "%prefix% &cAnother player has to count before you can again!");
        this.idleBroadcast = (String) this.add("Messages.CountingGame.IdleBroadcast", "%prefix% &eGet counting! The next count is &a%nextNumber%!");
        this.muted = (String) this.add("Messages.CountingGame.Muted", "%prefix% &aCounting Game muted!");
        this.mutedPlayer = (String) this.add("Messages.CountingGame.MutedPlayer", "%prefix% &aMuted Counting Game for %player%!");
        this.alreadyMuted = (String) this.add("Messages.CountingGame.AlreadyMuted", "%prefix% &cYou're already muted!");
        this.alreadyMutedPlayer = (String) this.add("Messages.CountingGame.AlreadyMutedPlayer", "%prefix% &c%player% is already muted!");
        this.unmuted = (String) this.add("Messages.CountingGame.Unmuted", "%prefix% &aCounting Game unmuted!");
        this.unmutedPlayer = (String) this.add("Messages.CountingGame.UnmutedPlayer", "%prefix% &aUnmuted Counting Game for %player%!");
        this.notMuted = (String) this.add("Messages.CountingGame.NotMuted", "%prefix% &cYou're not muted!");
        this.notMutedPlayer = (String) this.add("Messages.CountingGame.NotMutedPlayer", "%prefix% &c%player% is not muted!");
        this.nextNumber = (String) this.add("Messages.CountingGame.NextNumber", "%prefix% &eThe next number is &a%nextNumber%!");

        this.configReload = (String) this.add("Messages.Admin.ConfigurationsReloaded", "%prefix% &aConfiguration files successfully reloaded!");
        this.save();
    }

    @Override
    public void save() {
        try {
            this.getConfig().save(this.getFile());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getInvalidPermission() {
        return this.invalidPermission;
    }

    public String getNoCommands() {
        return this.noCommands;
    }

    public String getUnknownPlayer() {
        return this.unknownPlayer;
    }

    public String getPlayerOnly() {
        return this.playerOnly;
    }

    public String getInvalidArguments() {
        return this.invalidArguments;
    }

    public String getCountSent() {
        return this.countSent;
    }

    public String getLastCounter() {
        return this.lastCounter;
    }

    public String getIdleBroadcast() {
        return this.idleBroadcast;
    }

    public String getMuted() {
        return this.muted;
    }

    public String getMutedPlayer() {
        return this.mutedPlayer;
    }

    public String getAlreadyMuted() {
        return this.alreadyMuted;
    }

    public String getAlreadyMutedPlayer() {
        return this.alreadyMutedPlayer;
    }

    public String getUnmuted() {
        return this.unmuted;
    }

    public String getUnmutedPlayer() {
        return this.unmutedPlayer;
    }

    public String getNotMuted() {
        return this.notMuted;
    }

    public String getNotMutedPlayer() {
        return this.notMutedPlayer;
    }

    public String getNextNumber() {
        return this.nextNumber;
    }

    public String getConfigReload() {
        return this.configReload;
    }

    public static MessagesFile getInstance() {
        file = file == null ? new MessagesFile() : file;
        return file;
    }

    public static void reload() {
        file = new MessagesFile();
        file.setDefaults();
    }
}
