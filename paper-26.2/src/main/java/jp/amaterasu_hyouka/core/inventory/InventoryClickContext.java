package jp.amaterasu_hyouka.core.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public record InventoryClickContext(
    InventoryClickEvent event,
    Player player,
    int slot,
    ItemStack item
) {
    public static InventoryClickContext from(InventoryClickEvent e){
        return new InventoryClickContext(e, (Player) e.getWhoClicked(), e.getRawSlot(), e.getCurrentItem());
    }

    public String playerUuid() {
        return player.getUniqueId().toString();
    }

    public Material material() {
        return item == null ? Material.AIR : item.getType();
    }

    public <H extends InventoryHolder> H getHolder(Class<H> holderClass){
        InventoryHolder holder = event.getInventory().getHolder();
        if(!holderClass.isInstance(holder)){
            throw new IllegalStateException("InventoryHolderの型が不正です: expected=" + holderClass.getSimpleName() + ", actual=" + (holder == null ? "null" : holder.getClass().getSimpleName()));
        }
        return holderClass.cast(holder);
    }
}
