package jp.amaterasu_hyouka.core.inventory.element;

import java.util.function.Consumer;

import jp.amaterasu_hyouka.core.inventory.InventoryClickContext;

public interface InventoryAction {
    Consumer<InventoryClickContext> action();
}
