package jp.amaterasu_hyouka.core.inventory;

import jp.amaterasu_hyouka.core.util.ArrayUtil;
import jp.amaterasu_hyouka.core.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Hotbar {
    private static final int SIZE = InventoryRow.ONE.size();

    private final ItemStack[] items;

    public Hotbar() {
        this.items = new ItemStack[SIZE];
        Arrays.fill(items, ItemUtil.AIR.clone());
    }

    public Hotbar(ItemStack[] items) {
        if (items.length != SIZE) throw new IllegalArgumentException("hotbar は 9 スロットである必要があります");
        this.items = new ItemStack[SIZE];
        for (int i = 0; i < SIZE; i++) {
            ItemStack item = items[i];
            this.items[i] = item == null ? ItemUtil.AIR.clone() : item.clone();
        }
    }

    public Hotbar(Player player) {
        Inventory inventory = player.getInventory();
        this.items = new ItemStack[SIZE];
        for (int i = 0; i < SIZE; i++) {
            ItemStack item = inventory.getItem(i);
            this.items[i] = item == null ? ItemUtil.AIR.clone() : item.clone();
        }
    }

    public void setPlayerHotbar(Player player) {
        Inventory inventory = player.getInventory();
        for (int i = 0; i < SIZE; i++) {
            inventory.setItem(i, items[i].clone());
        }
    }

    public void setItem(int i, ItemStack item) {
        items[i] = item == null ? new ItemStack(Material.AIR) : item.clone();
    }

    public ItemStack getItemBySlot(int slot){
        return ArrayUtil.getOrDefault(items, slot, ItemUtil.AIR.clone());
    }
}
