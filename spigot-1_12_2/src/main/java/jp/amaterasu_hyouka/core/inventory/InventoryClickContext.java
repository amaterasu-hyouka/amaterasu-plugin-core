package jp.amaterasu_hyouka.core.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryClickContext {
    private final InventoryClickEvent event;
    private final Player player;
    private final int slot;
    private final ItemStack item;

    private InventoryClickContext(InventoryClickEvent event, Player player, int slot, ItemStack item) {
        this.event = event;
        this.player = player;
        this.slot = slot;
        this.item = item;
    }

    public static InventoryClickContext from(InventoryClickEvent e) {
        return new InventoryClickContext(
            e,
            (Player) e.getWhoClicked(),
            e.getRawSlot(),
            e.getCurrentItem()
        );
    }

    public Player player(){return player;}
    public String playerUuid(){return player.getUniqueId().toString();}
    public int slot(){return slot;}
    public ItemStack item(){return item;}
    public Material material(){return item == null ? Material.AIR : item.getType();}

    public <H extends InventoryHolder> H getHolder(Class<H> holderClass){
        InventoryHolder holder = event.getInventory().getHolder();
        if(!holderClass.isInstance(holder)){
            throw new IllegalStateException("InventoryHolderの型が不正です: expected=" + holderClass.getSimpleName() + ", actual=" + (holder == null ? "null" : holder.getClass().getSimpleName()));
        }
        return holderClass.cast(holder);
    }
}
