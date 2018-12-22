package com.benzoft.countinggame.files;

import com.benzoft.countinggame.CountingGame;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

abstract class AFile {

    private final File file;
    private final YamlConfiguration config;

    AFile(final String name) {
        this("", name);
    }

    AFile(final String path, final String name) {
        this.file = new File(CountingGame.getPlugin(CountingGame.class).getDataFolder() + path, name);
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (final IOException ignored) {
            }
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    Object add(final String path, final Object value) {
        if (this.config.contains(path)) return this.config.get(path);
        this.config.set(path, value);
        return value;
    }

    void setHeader(final String... lines) {
        this.config.options().header(String.join("\n", lines));
    }

    public File getFile() {
        return this.file;
    }

    YamlConfiguration getConfig() {
        return this.config;
    }

    public abstract void setDefaults();

    public abstract void save();
}
