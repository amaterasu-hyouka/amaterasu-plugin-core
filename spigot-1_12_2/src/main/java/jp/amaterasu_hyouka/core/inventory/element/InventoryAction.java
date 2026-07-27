package jp.amaterasu_hyouka.core.inventory.element;

import jp.amaterasu_hyouka.core.inventory.InventoryClickContext;

import java.util.function.Consumer;

public interface InventoryAction {
    Consumer<InventoryClickContext> action();
}
