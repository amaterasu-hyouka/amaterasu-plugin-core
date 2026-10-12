package jp.amaterasu_hyouka.core.util;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class InventoryUtil {
    private InventoryUtil(){}

    public static Inventory cloneInventory(InventoryHolder holder, Inventory originalInventory){
        Inventory copyInventory = Bukkit.createInventory(holder,originalInventory.getSize(), originalInventory.getTitle());
        copyInventory.setContents(originalInventory.getContents());
        return copyInventory;
    }

    public static void replaceItemsWithSameName(Inventory inventory, ItemStack item) {
        if (!ItemUtil.hasDisplayName(item)) return;
        String name = ItemUtil.getDisplayName(item);

        forEachMatchingItem(inventory,
                current -> ItemUtil.hasDisplayName(current) && name.equals(ItemUtil.getDisplayName(current)),
                slot -> inventory.setItem(slot, item.clone())
        );
    }
    public static void replaceItemsWithSameMaterial(Inventory inventory, ItemStack item) {
        if (ItemUtil.hasNoItem(item)) return;

        forEachMatchingItem(inventory,
                current -> current.getType() == item.getType(),
                slot -> inventory.setItem(slot, item.clone())
        );
    }

    public static void replaceItemByName(Inventory inventory, String name, ItemStack item) {
        if (name == null || name.isEmpty() || ItemUtil.hasNoItem(item)) return;

        forEachMatchingItem(inventory,
                current -> ItemUtil.hasDisplayName(current) && name.equals(ItemUtil.getDisplayName(current)),
                slot -> inventory.setItem(slot, item.clone())
        );
    }
    public static void replaceItemByMaterial(Inventory inventory, Material material, ItemStack item) {
        if (material == null || ItemUtil.hasNoItem(item)) return;

        forEachMatchingItem(inventory,
                current -> current.getType() == material,
                slot -> inventory.setItem(slot, item.clone())
        );
    }
    public static void replaceItemByNameAndMaterial(Inventory inventory, Material material, String name, ItemStack item) {
        if (material == null || name == null || name.isEmpty() || ItemUtil.hasNoItem(item)) return;

        forEachMatchingItem(inventory,
                current -> current.getType() == material && ItemUtil.hasDisplayName(current) && name.equals(ItemUtil.getDisplayName(current)),
                slot -> inventory.setItem(slot, item.clone())
        );
    }

    public static void deleteItemsWithSameName(Inventory inventory, ItemStack item) {
        if (!ItemUtil.hasDisplayName(item)) return;
        String name = ItemUtil.getDisplayName(item);

        forEachMatchingItem(inventory,
                current -> ItemUtil.hasDisplayName(current) && name.equals(ItemUtil.getDisplayName(current)),
                inventory::clear
        );
    }
    public static void deleteItemsWithSameMaterial(Inventory inventory, ItemStack item) {
        if (ItemUtil.hasNoItem(item)) return;

        forEachMatchingItem(inventory,
                current -> current.getType() == item.getType(),
                inventory::clear
        );
    }

    public static void deleteItemByName(Inventory inventory, String name) {
        if (name == null || name.isEmpty()) return;

        forEachMatchingItem(inventory,
                current -> ItemUtil.hasDisplayName(current) && name.equals(ItemUtil.getDisplayName(current)),
                inventory::clear
        );
    }
    public static void deleteItemByMaterial(Inventory inventory, Material material) {
        if (material == null) return;

        forEachMatchingItem(inventory,
                current -> current.getType() == material,
                inventory::clear
        );
    }
    public static void deleteItemByNameAndMaterial(Inventory inventory, Material material, String name) {
        if (material == null || name == null || name.isEmpty()) return;

        forEachMatchingItem(inventory,
                current -> current.getType() == material && ItemUtil.hasDisplayName(current) && name.equals(ItemUtil.getDisplayName(current)),
                inventory::clear
        );
    }

    public static void forEachMatchingItem(Inventory inventory, Predicate<ItemStack> filter, BiConsumer<Integer, ItemStack> action) {
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            ItemStack current = inventory.getItem(slot);
            if (ItemUtil.hasNoItem(current)) continue;
            if (filter.test(current)) action.accept(slot, current);
        }
    }
    public static void forEachMatchingItem(Inventory inventory, Predicate<ItemStack> filter, Consumer<Integer> action) {
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            ItemStack current = inventory.getItem(slot);
            if (ItemUtil.hasNoItem(current)) continue;
            if (filter.test(current)) action.accept(slot);
        }
    }
}
