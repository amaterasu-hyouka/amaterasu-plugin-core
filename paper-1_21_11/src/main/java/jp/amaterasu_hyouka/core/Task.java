package jp.amaterasu_hyouka.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Task {
    private final JavaPlugin plugin;
    public Task(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void runSync(Runnable task){Bukkit.getScheduler().runTask(plugin, task);}
    public void runSyncLater(Runnable task, int delaySeconds){Bukkit.getScheduler().runTaskLater(plugin, task, delaySeconds * 20L);}
    public void runSyncLater(Runnable task, long delayTicks){Bukkit.getScheduler().runTaskLater(plugin, task, delayTicks);}
    public BukkitTask runSyncInfinite(Runnable task, int periodSeconds){return runSyncInfinite(task, 0L, periodSeconds * 20L);}
    public BukkitTask runSyncInfinite(Runnable task, long periodTicks){return runSyncInfinite(task, 0L, periodTicks);}
    public BukkitTask runSyncInfinite(Runnable task, int delaySeconds, int periodSeconds){return runSyncInfinite(task, delaySeconds * 20L, periodSeconds * 20L);}
    public BukkitTask runSyncInfinite(Runnable task, long delayTicks, long periodTicks){return Bukkit.getScheduler().runTaskTimer(plugin, task, delayTicks, periodTicks);}

    public void runAsync(Runnable task){Bukkit.getScheduler().runTaskAsynchronously(plugin, task);}
    public void runAsyncLater(Runnable task, int delaySeconds){Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delaySeconds * 20L);}
    public void runAsyncLater(Runnable task, long delayTicks){Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delayTicks);}
    public BukkitTask runAsyncInfinite(Runnable task, int periodSeconds){return runAsyncInfinite(task, 0L, periodSeconds * 20L);}
    public BukkitTask runAsyncInfinite(Runnable task, long periodTicks){return runAsyncInfinite(task, 0L, periodTicks);}
    public BukkitTask runAsyncInfinite(Runnable task, int delaySeconds, int periodSeconds){return runAsyncInfinite(task, delaySeconds * 20L, periodSeconds * 20L);}
    public BukkitTask runAsyncInfinite(Runnable task, long delayTicks, long periodTicks){return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, delayTicks, periodTicks);}

    public BukkitTask runSyncTimes(Runnable task, int periodSeconds, int times){return runSyncTimes(task, 0L, periodSeconds * 20L, times);}
    public BukkitTask runSyncTimes(Runnable task, long periodTicks, int times){return runSyncTimes(task, 0L, periodTicks, times);}
    public BukkitTask runSyncTimes(Runnable task, int delaySeconds, int periodSeconds, int times){return runSyncTimes(task, delaySeconds * 20L, periodSeconds * 20L, times);}
    public BukkitTask runSyncTimes(Runnable task, long delayTicks, long periodTicks, int times){return repeatTimes(task, times).runTaskTimer(plugin, delayTicks, periodTicks);}

    public BukkitTask runAsyncTimes(Runnable task, int periodSeconds, int times){return runAsyncTimes(task, 0L, periodSeconds * 20L, times);}
    public BukkitTask runAsyncTimes(Runnable task, long periodTicks, int times){return runAsyncTimes(task, 0L, periodTicks, times);}
    public BukkitTask runAsyncTimes(Runnable task, int delaySeconds, int periodSeconds, int times){return runAsyncTimes(task, delaySeconds * 20L, periodSeconds * 20L, times);}
    public BukkitTask runAsyncTimes(Runnable task, long delayTicks, long periodTicks, int times){return repeatTimes(task, times).runTaskTimerAsynchronously(plugin, delayTicks, periodTicks);}

    private BukkitRunnable repeatTimes(Runnable task, int times) {
        java.util.Objects.requireNonNull(task, "task");
        if (times <= 0) {
            throw new IllegalArgumentException("times must be greater than zero");
        }
        return new BukkitRunnable() {
            private int remaining = times;

            @Override
            public void run() {
                try {
                    task.run();
                } finally {
                    if (--remaining == 0) {
                        cancel();
                    }
                }
            }
        };
    }

    public void runAsync(Runnable task, Runnable callback) {
        runAsync(() -> {
            task.run();
            runSync(callback);
        });
    }
    public <T> void supplyAsync(Supplier<T> supplier, Consumer<T> callback) {
        runAsync(() -> {
            T result = supplier.get();
            runSync(() -> callback.accept(result));
        });
    }
}
