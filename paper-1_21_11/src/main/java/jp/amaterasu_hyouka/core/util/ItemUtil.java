package jp.amaterasu_hyouka.core.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ItemUtil {
    private ItemUtil(){}

    public static ItemStack AIR = new ItemStack(Material.AIR);

    public static boolean hasNoItem(ItemStack item) {
        return item == null || item.getType().isAir();
    }

    public static ItemStack createItem(final Material material) {
        return createItem(material, 1, Component.empty());
    }

    public static ItemStack createItem(final Material material, final String name) {
        return createItem(material, 1, Component.text(name));
    }

    public static ItemStack createItem(final Material material, final int amount, final String name) {
        return createItem(material, amount, Component.text(name));
    }

    public static ItemStack createItem(
        final Material material, final int amount, final Component name) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.displayName(TextUtil.clearItalic(name));
        meta.setMaxStackSize(amount);
        item.setItemMeta(meta);
        return item;
    }
}
