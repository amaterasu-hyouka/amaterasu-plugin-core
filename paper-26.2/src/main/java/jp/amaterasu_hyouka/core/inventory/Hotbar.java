package jp.amaterasu_hyouka.core.inventory;

import java.util.Arrays;

import jp.amaterasu_hyouka.core.util.ItemUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Hotbar {
    private final ItemStack[] items;

    public Hotbar() {
        this.items = new ItemStack[InventoryRow.ONE];
        Arrays.fill(items, ItemUtil.AIR);
    }

    public Hotbar(ItemStack[] items) {
        if (items.length != InventoryRow.ONE) throw new RuntimeException();
        for (int i = 0; i < 9; i++) {
            if (items[i] == null) {
                items[i] = ItemUtil.AIR;
            }
        }
        this.items = items;
    }

    public void setPlayerHotbar(Player player) {
        Inventory inventory = player.getInventory();
        clearHotbar(inventory);
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, items[i].clone());
        }
    }

    private void clearHotbar(Inventory inventory) {
        for (int i = 0; i < 9; i++) inventory.clear(i);
    }

    public void setItem(int i, ItemStack item) {
        items[i] = item;
    }
}
