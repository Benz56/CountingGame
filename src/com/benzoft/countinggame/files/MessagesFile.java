package com.benzoft.countinggame.files;

import lombok.Getter;

import java.io.IOException;

@Getter
public final class MessagesFile extends AbstractFile {

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
    private String countSet;

    private MessagesFile() {
        super("messages.yml");
        setDefaults();
    }

    public static MessagesFile getInstance() {
        file = file == null ? new MessagesFile() : file;
        return file;
    }

    public static void reload() {
        file = new MessagesFile();
        file.setDefaults();
    }

    @Override
    public void setDefaults() {
        setHeader(
                "This is the Message file for all Counting Game messages.",
                "",
                "All messages are fully customizable and support color codes, formatting and ASCII symbols.",
                "Set the Prefix and use %prefix% to add the corresponding prefix to a message.",
                "Prepend any message with <ActionBar> to send it as an ActionBar message.",
                "Leave a message blank ('') to disable it.",
                "",
                "You can also create messages with Hover and Click events. Syntax options: (Space between comma and quote is NOT allowed)",
                " - [\"Message\",\"/Command\"]",
                " - [\"Message\",\"Hover\"]",
                " - [\"Message\",\"/Command\",\"Hover\"]",
                " - [\"Message\",\"/Command\",\"Suggest\"]",
                " - [\"Message\",\"/Command\",\"Hover\",\"Suggest\"]",
                "You can add as many events to a message as you want. Example:",
                "'%prefix% &cInvalid arguments! [\"&c&n&oHelp\",\"/countinggame help\",\"&aClick to get help!\"]'",
                "The \"Suggest\" tag is used if the click event should suggest the command. Default is Run.");

        prefix = (String) add("Prefix", "&7[&9Counting Game&7]");
        invalidPermission = (String) add("Messages.General.InvalidPermission", "%prefix% &cYou do not have permission to do this!");
        noCommands = (String) add("Messages.General.NoCommands", "Unknown command. Type \"/help\" for help.");
        unknownPlayer = (String) add("Messages.General.UnknownPlayer", "%prefix% &cUnknown Player!");
        playerOnly = (String) add("Messages.General.PlayerOnly", "%prefix% &cCommand can only be used as a Player!");
        invalidArguments = (String) add("Messages.General.InvalidArguments", "%prefix% &cInvalid arguments! [\"&c&n&oHelp\",\"/countinggame help\",\"&aClick to get help!\"]");

        countSent = (String) add("Messages.CountingGame.CountSent", "%prefix% &a%player%&e counted &a%number%! &eThe next number is &a%nextNumber%!");
        lastCounter = (String) add("Messages.CountingGame.LastCounter", "%prefix% &cAnother player has to count before you can again!");
        idleBroadcast = (String) add("Messages.CountingGame.IdleBroadcast", "%prefix% [\"&eGet counting! The next number is &a%nextNumber%!\",\"&eType &a%nextNumber% &ein chat!\"]");
        muted = (String) add("Messages.CountingGame.Muted", "%prefix% &aCounting Game muted!");
        mutedPlayer = (String) add("Messages.CountingGame.MutedPlayer", "%prefix% &aMuted Counting Game for %player%!");
        alreadyMuted = (String) add("Messages.CountingGame.AlreadyMuted", "%prefix% &cYou're already muted!");
        alreadyMutedPlayer = (String) add("Messages.CountingGame.AlreadyMutedPlayer", "%prefix% &c%player% is already muted!");
        unmuted = (String) add("Messages.CountingGame.Unmuted", "%prefix% &aCounting Game unmuted!");
        unmutedPlayer = (String) add("Messages.CountingGame.UnmutedPlayer", "%prefix% &aUnmuted Counting Game for %player%!");
        notMuted = (String) add("Messages.CountingGame.NotMuted", "%prefix% &cYou're not muted!");
        notMutedPlayer = (String) add("Messages.CountingGame.NotMutedPlayer", "%prefix% &c%player% is not muted!");
        nextNumber = (String) add("Messages.CountingGame.NextNumber", "%prefix% &eThe next number is &a%nextNumber%!");

        configReload = (String) add("Messages.Admin.ConfigurationsReloaded", "%prefix% &aConfiguration files successfully reloaded!");
        countSet = (String) add("Messages.Admin.CountSet", "%prefix% &aThe count has been set to %number%!");
        save();
    }

    @Override
    void save() {
        try {
            getConfig().save(getFile());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
