package jp.amaterasu_hyouka.core;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Log {
    private final Logger logger;
    public Log(JavaPlugin plugin) {
        this.logger = plugin.getLogger();
    }

    private static final String PREFIX = "> ";

    public void info(String msg){logger.log(Level.INFO, PREFIX + msg);}
    public void warn(String msg){logger.log(Level.WARNING, PREFIX + msg);}
    public void error(String msg){logger.log(Level.SEVERE, PREFIX + msg);}
    public void error(Throwable t){logger.log(Level.SEVERE, PREFIX + "Unexpected error", t);}
    public void error(String msg, Throwable t){logger.log(Level.SEVERE, PREFIX + msg, t);}
    public void debug(String msg) {logger.log(Level.INFO, PREFIX + "[DEBUG] " + msg);}
}
