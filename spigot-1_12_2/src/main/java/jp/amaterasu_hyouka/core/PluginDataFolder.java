package jp.amaterasu_hyouka.core;

import jp.amaterasu_hyouka.core.exception.PluginDataFolderException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class PluginDataFolder {
    private final File pluginDirectory;

    public PluginDataFolder(JavaPlugin plugin) throws PluginDataFolderException {
        this.pluginDirectory = plugin.getDataFolder();
        ensurePluginDirectory();
    }

    private void ensurePluginDirectory() throws PluginDataFolderException {
        if (pluginDirectory.exists()) {
            if (!pluginDirectory.isDirectory()) {
                throw new PluginDataFolderException("パスがディレクトリではない: " + pluginDirectory.getAbsolutePath());
            }
            return;
        }
        if (!pluginDirectory.mkdirs()) {
            throw new PluginDataFolderException("ディレクトリの作成に失敗しました: " + pluginDirectory.getAbsolutePath());
        }
    }

    public File getPluginDirectory() {
        return pluginDirectory;
    }

    public String getPluginPath() {
        return pluginDirectory.getAbsolutePath();
    }
}
