package jp.amaterasu_hyouka.core.listener;


import org.bukkit.event.player.AsyncPlayerChatEvent;

public interface CustomChatListener {
    void handle(AsyncPlayerChatEvent e);
}
