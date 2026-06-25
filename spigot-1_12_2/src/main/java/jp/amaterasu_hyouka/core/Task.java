package jp.amaterasu_hyouka.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Task {
    private final JavaPlugin plugin;
    public Task(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void runSync(Runnable task) {
        Bukkit.getScheduler().runTask(plugin, task);
    }
    public void runSyncLater(Runnable task, long delaySeconds) {
        Bukkit.getScheduler().runTaskLater(plugin, task, delaySeconds * 20L);
    }

    public void runAsync(Runnable task) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }
    public void runAsyncLater(Runnable task, long delaySeconds) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delaySeconds * 20L);
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
