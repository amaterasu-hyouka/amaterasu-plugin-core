package jp.amaterasu_hyouka.core;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CoolTime {
    private final Task task;
    private final long time;

    private final Set<UUID> coolTimeSet = ConcurrentHashMap.newKeySet();

    public CoolTime(Task task, long time){
        this.task = task;
        this.time = time;
    }

    public boolean isCoolTime(UUID playerUuid) {
        return coolTimeSet.contains(playerUuid);
    }

    public void setCoolTime(UUID playerUuid) {
        if (!coolTimeSet.add(playerUuid)) return;
        task.runAsyncLater(() -> coolTimeSet.remove(playerUuid), time);
    }

    public boolean checkAndSetCoolTime(UUID playerUuid) {
        if (isCoolTime(playerUuid)) return true;
        setCoolTime(playerUuid);
        return false;
    }
}
