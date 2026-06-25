package jp.amaterasu_hyouka.core.listener;

import java.util.UUID;

public interface CustomQuitServerListener {
    void handleQuitServer(UUID playerUuid);
}
