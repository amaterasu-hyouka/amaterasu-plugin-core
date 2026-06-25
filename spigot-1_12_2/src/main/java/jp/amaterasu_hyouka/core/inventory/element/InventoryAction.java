package jp.amaterasu_hyouka.core.inventory.element;

import org.bukkit.entity.Player;

import java.util.function.Consumer;

public interface InventoryAction {
    Consumer<Player> action();
}
