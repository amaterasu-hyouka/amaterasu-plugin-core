package jp.amaterasu_hyouka.core.Permission;

import org.bukkit.entity.Player;

public interface Permission {
    boolean canExecute(Player player);
}
