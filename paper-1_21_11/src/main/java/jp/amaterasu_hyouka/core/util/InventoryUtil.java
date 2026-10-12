package jp.amaterasu_hyouka.core.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

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

    public static void replaceItemsWithSameName(Inventory inventory, ItemStack item) {
        Component name = ItemUtil.getItemCustomName(item);
        if (name == null) return;

        forEachMatchingItem(inventory,
            current -> name.equals(ItemUtil.getItemCustomName(current)),
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

    public static void replaceItemByName(Inventory inventory, Component name, ItemStack item) {
        if (name == null) return;
        replaceItemByName(inventory, PlainTextComponentSerializer.plainText().serialize(name), item);
    }
    public static void replaceItemByName(Inventory inventory, String name, ItemStack item) {
        if (name == null || name.isEmpty() || ItemUtil.hasNoItem(item)) return;

        forEachMatchingItem(inventory,
            current -> name.equals(ItemUtil.getItemCustomNameAsString(current)),
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
    public static void replaceItemByNameAndMaterial(Inventory inventory, Material material, Component name, ItemStack item) {
        if (name == null) return;
        replaceItemByNameAndMaterial(inventory, material, PlainTextComponentSerializer.plainText().serialize(name), item);
    }
    public static void replaceItemByNameAndMaterial(Inventory inventory, Material material, String name, ItemStack item) {
        if (material == null || name == null || name.isEmpty() || ItemUtil.hasNoItem(item)) return;

        forEachMatchingItem(inventory,
            current -> current.getType() == material && name.equals(ItemUtil.getItemCustomNameAsString(current)),
            slot -> inventory.setItem(slot, item.clone())
        );
    }

    public static void deleteItemsWithSameName(Inventory inventory, ItemStack item) {
        Component name = ItemUtil.getItemCustomName(item);
        if (name == null) return;

        forEachMatchingItem(inventory,
            current -> name.equals(ItemUtil.getItemCustomName(current)),
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

    public static void deleteItemByName(Inventory inventory, Component name) {
        if (name == null) return;
        deleteItemByName(inventory, PlainTextComponentSerializer.plainText().serialize(name));
    }
    public static void deleteItemByName(Inventory inventory, String name) {
        if (name == null || name.isEmpty()) return;

        forEachMatchingItem(inventory,
            current -> name.equals(ItemUtil.getItemCustomNameAsString(current)),
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
    public static void deleteItemByNameAndMaterial(Inventory inventory, Material material, Component name) {
        if (name == null) return;
        deleteItemByNameAndMaterial(inventory, material, PlainTextComponentSerializer.plainText().serialize(name));
    }
    public static void deleteItemByNameAndMaterial(Inventory inventory, Material material, String name) {
        if (material == null || name == null || name.isEmpty()) return;

        forEachMatchingItem(inventory,
            current -> current.getType() == material && name.equals(ItemUtil.getItemCustomNameAsString(current)),
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
