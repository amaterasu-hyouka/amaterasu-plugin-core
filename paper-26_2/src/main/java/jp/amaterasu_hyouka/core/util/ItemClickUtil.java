package jp.amaterasu_hyouka.core.util;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ItemClickUtil {
    private ItemClickUtil(){}

    public static boolean isSneakingRightClick(PlayerInteractEvent e){return e.getPlayer().isSneaking() && isRightClick(e);}
    public static boolean isSneakingRightBlockClick(PlayerInteractEvent e){return e.getPlayer().isSneaking() && isRightBlockClick(e);}
    public static boolean isRightClick(PlayerInteractEvent e){return isRightClick(e.getAction());}
    public static boolean isRightClick(Action action){return action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK;}
    public static boolean isRightBlockClick(PlayerInteractEvent e){return isRightBlockClick(e.getAction());}
    public static boolean isRightBlockClick(Action action){return action == Action.RIGHT_CLICK_BLOCK;}

    public static boolean isSneakingLeftClick(PlayerInteractEvent e){return e.getPlayer().isSneaking() && isLeftClick(e);}
    public static boolean isSneakingLeftBlockClick(PlayerInteractEvent e){return e.getPlayer().isSneaking() && isLeftBlockClick(e);}
    public static boolean isLeftClick(PlayerInteractEvent e){return isLeftClick(e.getAction());}
    public static boolean isLeftClick(Action action){return action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK;}
    public static boolean isLeftBlockClick(PlayerInteractEvent e){return isLeftBlockClick(e.getAction());}
    public static boolean isLeftBlockClick(Action action){return action == Action.LEFT_CLICK_BLOCK;}
}
