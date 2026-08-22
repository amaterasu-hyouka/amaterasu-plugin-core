package jp.amaterasu_hyouka.core.listener;

import io.papermc.paper.event.player.AsyncChatEvent;

public interface CustomAsyncChatListener {
    void handle(AsyncChatEvent e);
}
