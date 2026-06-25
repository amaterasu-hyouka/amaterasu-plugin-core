package jp.amaterasu_hyouka.core.inventory;

import jp.amaterasu_hyouka.core.listener.CustomInventoryClickListener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CustomInventoryHolder implements InventoryHolder {

    private final CustomInventoryClickListener handler;

    public CustomInventoryHolder(CustomInventoryClickListener handler) {
        this.handler = handler;
    }

    public CustomInventoryClickListener handler() {
        return handler;
    }

    @Override
    public Inventory getInventory(){return null;}
}
