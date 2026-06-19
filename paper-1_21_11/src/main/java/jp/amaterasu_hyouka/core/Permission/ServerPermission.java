package jp.amaterasu_hyouka.core.Permission;

import org.bukkit.entity.Player;

public enum ServerPermission implements Permission {
    ADMIN {
        @Override
        public boolean canExecute(Player player) {
            return player.isOp();
        }
    },
    OP {
        @Override
        public boolean canExecute(Player player) {
            return player.isOp();
        }
    },
    CREATOR {
        @Override
        public boolean canExecute(Player player) {
            return player.isOp();
        }
    },
}
