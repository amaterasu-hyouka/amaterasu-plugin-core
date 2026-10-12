package jp.amaterasu_hyouka.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Task {
    private final JavaPlugin plugin;
    public Task(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void runSync(Runnable task) {Bukkit.getScheduler().runTask(plugin, task);}
    public void runSyncLater(Runnable task, int delaySeconds){Bukkit.getScheduler().runTaskLater(plugin, task, delaySeconds * 20L);}
    public void runSyncLater(Runnable task, long delayTicks){Bukkit.getScheduler().runTaskLater(plugin, task, delayTicks);}
    public BukkitTask runSyncTimer(Runnable task, int periodSeconds){return runSyncTimer(task, 0L, periodSeconds * 20L);}
    public BukkitTask runSyncTimer(Runnable task, long periodTicks){return runSyncTimer(task, 0L, periodTicks);}
    public BukkitTask runSyncTimer(Runnable task, int delaySeconds, int periodSeconds){return runSyncTimer(task, delaySeconds * 20L, periodSeconds * 20L);}
    public BukkitTask runSyncTimer(Runnable task, long delayTicks, long periodTicks){return Bukkit.getScheduler().runTaskTimer(plugin, task, delayTicks, periodTicks);}

    public void runAsync(Runnable task){Bukkit.getScheduler().runTaskAsynchronously(plugin, task);}
    public void runAsyncLater(Runnable task, int delaySeconds){Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delaySeconds * 20L);}
    public void runAsyncLater(Runnable task, long delayTicks){Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delayTicks);}
    public BukkitTask runAsyncTimer(Runnable task, int periodSeconds){return runAsyncTimer(task, 0L, periodSeconds * 20L);}
    public BukkitTask runAsyncTimer(Runnable task, long periodTicks){return runAsyncTimer(task, 0L, periodTicks);}
    public BukkitTask runAsyncTimer(Runnable task, int delaySeconds, int periodSeconds){return runAsyncTimer(task, delaySeconds * 20L, periodSeconds * 20L);}
    public BukkitTask runAsyncTimer(Runnable task, long delayTicks, long periodTicks) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, delayTicks, periodTicks);
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
