package jp.amaterasu_hyouka.core.listener;

import org.bukkit.event.player.AsyncPlayerChatEvent;

public interface CustomAsyncChat {
    void handle(AsyncPlayerChatEvent e);
}
