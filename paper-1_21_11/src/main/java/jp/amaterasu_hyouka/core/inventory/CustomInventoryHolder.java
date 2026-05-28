package jp.amaterasu_hyouka.core.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public record CustomInventoryHolder(CustomInventoryClickListener handler) implements InventoryHolder {
    @Override
    public @NotNull Inventory getInventory() {
        throw new UnsupportedOperationException("CustomInventoryHolder does not provide an inventory.");
    }
}
