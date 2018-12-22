package com.benzoft.countinggame.files;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public final class DataFile extends AFile {

    private static DataFile file;

    private int count;
    private Set<UUID> mutedPlayers;

    private DataFile() {
        super("data.yml");
    }

    @Override
    public void setDefaults() {
        this.setHeader();
        this.count = (int) this.add("Count", 0);
        this.mutedPlayers = ((List<String>) this.add("MutedPlayers", Collections.emptyList())).stream().map(UUID::fromString).collect(Collectors.toSet());
        this.save();
    }

    @Override
    public void save() {
        this.getConfig().set("Count", this.count);
        this.getConfig().set("MutedPlayers", this.mutedPlayers.stream().map(UUID::toString).collect(Collectors.toList()));
        try {
            this.getConfig().save(this.getFile());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public List<Player> getCGPlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(player -> !this.mutedPlayers.contains(player.getUniqueId()) && ConfigFile.getInstance().isCGWorld(player.getWorld())).collect(Collectors.toList());
    }

    public boolean mutePlayer(final Player player) {
        return this.mutedPlayers.add(player.getUniqueId());
    }

    public boolean unmutePlayer(final Player player) {
        return this.mutedPlayers.remove(player.getUniqueId());
    }

    public int getCount() {
        return this.count;
    }

    public void incrementCount() {
        this.count++;
    }

    public static DataFile getInstance() {
        file = file == null ? new DataFile() : file;
        return file;
    }

    public static void reload() {
        file = new DataFile();
        file.setDefaults();
    }
}
