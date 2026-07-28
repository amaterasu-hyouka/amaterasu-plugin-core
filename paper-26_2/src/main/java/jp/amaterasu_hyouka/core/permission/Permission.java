package jp.amaterasu_hyouka.core.permission;

import org.bukkit.entity.Player;

public interface Permission {
    boolean canExecute(Player player);
}
