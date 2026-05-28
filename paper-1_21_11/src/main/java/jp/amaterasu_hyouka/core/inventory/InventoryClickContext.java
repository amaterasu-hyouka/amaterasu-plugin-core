package jp.amaterasu_hyouka.core.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public record InventoryClickContext(Player player, int slot, ItemStack item) {
    public InventoryClickContext(InventoryClickEvent e) {
        this((Player) e.getWhoClicked(), e.getRawSlot(), e.getCurrentItem());
    }

    public String playerUuid() {
        return player.getUniqueId().toString();
    }

    public Material material() {
        return item.getType();
    }
}
