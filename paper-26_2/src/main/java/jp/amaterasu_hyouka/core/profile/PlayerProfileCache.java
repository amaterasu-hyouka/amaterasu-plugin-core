package jp.amaterasu_hyouka.core.profile;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public final class PlayerProfileCache {
    private PlayerProfileCache(){}

    private static final long CLEAR_INTERVAL_TICKS = 24 * 60 * 60 * 20L;
    private static final Map<UUID, PlayerProfile> CACHE = new ConcurrentHashMap<>();

    public static CompletableFuture<PlayerProfile> get(Player player) {return get(player.getUniqueId());}
    public static CompletableFuture<PlayerProfile> get(String uuid) {return get(UUID.fromString(uuid));}
    public static CompletableFuture<PlayerProfile> get(UUID uuid) {
        PlayerProfile profile = CACHE.get(uuid);
        if (profile != null) return CompletableFuture.completedFuture(profile);
        return load(uuid);
    }

    public static BukkitTask startClearTask(JavaPlugin plugin) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(
                plugin,
                PlayerProfileCache::clear,
                CLEAR_INTERVAL_TICKS,
                CLEAR_INTERVAL_TICKS
        );
    }

    public static void clear() {
        CACHE.clear();
    }

    private static CompletableFuture<PlayerProfile> load(UUID uuid) {
        return Bukkit.createProfile(uuid).update()
                .thenApply(profile -> {
                    if (profile.hasTextures()) CACHE.put(uuid, profile);
                    return profile;
                })
                .exceptionally(e -> null);
    }
}
