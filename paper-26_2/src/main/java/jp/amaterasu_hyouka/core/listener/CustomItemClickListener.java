package jp.amaterasu_hyouka.core.listener;

import org.bukkit.event.player.PlayerInteractEvent;

public interface CustomItemClickListener {
    void handleItemClickEvent(PlayerInteractEvent e);
}
