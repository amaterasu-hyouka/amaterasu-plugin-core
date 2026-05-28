package jp.amaterasu_hyouka.core.util;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtil {
    private SoundUtil(){}

    public static void play(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
    }
}
