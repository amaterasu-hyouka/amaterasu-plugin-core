package jp.amaterasu_hyouka.core.inventory.element;

import java.util.function.Consumer;

import org.bukkit.entity.Player;

public interface InventoryAction {
    Consumer<Player> action();
}
