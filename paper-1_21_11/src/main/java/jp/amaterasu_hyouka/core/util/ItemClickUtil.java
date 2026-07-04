package jp.amaterasu_hyouka.core.util;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ItemClickUtil {
    private ItemClickUtil(){}

    public static boolean isRightClick(PlayerInteractEvent e) {
        return isRightClick(e.getAction());
    }
    public static boolean isRightClick(Action action) {
        return action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK;
    }

    public static boolean isLeftClick(PlayerInteractEvent e) {
        return isLeftClick(e.getAction());
    }
    public static boolean isLeftClick(Action action) {
        return action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK;
    }
}
