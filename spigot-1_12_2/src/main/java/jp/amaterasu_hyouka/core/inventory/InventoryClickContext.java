package jp.amaterasu_hyouka.core.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickContext {
    private final Player player;
    private final int slot;
    private final ItemStack item;

    public InventoryClickContext(InventoryClickEvent e){
        this.player = (Player) e.getWhoClicked();
        this.slot = e.getRawSlot();
        this.item = e.getCurrentItem();
    }

    public Player player(){return player;}
    public String playerUuid(){return player.getUniqueId().toString();}
    public int slot(){return slot;}
    public ItemStack item(){return item;}
    public Material material(){return item.getType();}
}
