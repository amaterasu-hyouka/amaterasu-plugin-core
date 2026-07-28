package jp.amaterasu_hyouka.core.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class CustomInventoryHolder implements InventoryHolder {
    private final CustomInventoryClickListener handler;

    public CustomInventoryHolder(CustomInventoryClickListener handler) {
        this.handler = handler;
    }

    public CustomInventoryClickListener handler() {
        return handler;
    }

    @Override
    public @NotNull Inventory getInventory() {
        throw new UnsupportedOperationException("CustomInventoryHolder does not provide an inventory.");
    }
}
