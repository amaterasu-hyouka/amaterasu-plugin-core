package jp.amaterasu_hyouka.core.listener;

import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public interface CustomAsyncPreJoinServerListener {
    void handle(AsyncPlayerPreLoginEvent e);
}
