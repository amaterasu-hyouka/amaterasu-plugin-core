package jp.amaterasu_hyouka.core.listener;

import io.papermc.paper.event.player.AsyncChatEvent;

public interface CustomAsyncChat {
    void handle(AsyncChatEvent e);
}
