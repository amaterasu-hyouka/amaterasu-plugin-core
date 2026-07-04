package jp.amaterasu_hyouka.core.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtil {
    private InventoryUtil(){}

    private static void cloneInventoryContent(Inventory inventory, Inventory original) {
        if(inventory.getSize() != original.getSize())throw new IllegalArgumentException("inventory size が一致していません");

        ItemStack[] contents = original.getContents();
        ItemStack[] clonedContents = new ItemStack[contents.length];

        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            clonedContents[i] = item == null ? null : item.clone();
        }

        inventory.setContents(clonedContents);
    }
}
