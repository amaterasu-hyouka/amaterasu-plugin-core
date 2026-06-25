package jp.amaterasu_hyouka.core.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegister {
    private final JavaPlugin plugin;

    public CommandRegister(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void register(String command, CommandExecutor commandExecutor){
        PluginCommand pluginCommand = plugin.getCommand(command);
        if (pluginCommand == null) {
            throw new IllegalStateException("plugin.yml にコマンドが未定義です: " + command);
        }
        pluginCommand.setExecutor(commandExecutor);
    }
}
