package jp.amaterasu_hyouka.core.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickContext {
    private final Player player;
    private final int slot;
    private final ItemStack item;

    private InventoryClickContext(Player player, int slot, ItemStack item) {
        this.player = player;
        this.slot = slot;
        this.item = item;
    }

    public static InventoryClickContext from(InventoryClickEvent e) {
        return new InventoryClickContext(
            (Player) e.getWhoClicked(),
            e.getRawSlot(),
            e.getCurrentItem()
        );
    }

    public Player player(){return player;}
    public String playerUuid(){return player.getUniqueId().toString();}
    public int slot(){return slot;}
    public ItemStack item(){return item;}
    public Material material(){return item.getType();}
}
