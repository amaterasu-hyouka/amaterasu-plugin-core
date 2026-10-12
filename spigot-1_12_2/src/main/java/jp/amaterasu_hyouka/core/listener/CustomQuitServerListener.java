package jp.amaterasu_hyouka.core.listener;

import org.bukkit.event.player.PlayerQuitEvent;

public interface CustomQuitServerListener {
    void handleQuitServer(PlayerQuitEvent e);
}
