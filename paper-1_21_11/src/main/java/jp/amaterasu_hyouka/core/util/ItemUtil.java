package jp.amaterasu_hyouka.core.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

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

    public static ItemStack enchantItem(final ItemStack item) {
        final ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.setEnchantmentGlintOverride(true);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createPlayerHead(String uuid){return createPlayerHead(UUID.fromString(uuid));}
    public static ItemStack createPlayerHead(UUID uuid) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        if (item.getItemMeta() instanceof SkullMeta meta) {
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
            item.setItemMeta(meta);
        }
        return item;
    }
}
