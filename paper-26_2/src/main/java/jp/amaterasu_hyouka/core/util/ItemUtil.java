package jp.amaterasu_hyouka.core.util;

import jp.amaterasu_hyouka.core.profile.PlayerProfileCache;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class ItemUtil {
    private ItemUtil(){}

    public static final ItemStack AIR = new ItemStack(Material.AIR);
    public static final Material DEFAULT_MATERIAL = Material.STONE;
    public static final ItemStack DEFAULT = createItem(DEFAULT_MATERIAL);

    public static boolean hasNoItem(ItemStack item) {
        return item == null || item.getType().isAir();
    }
    public static Material getMaterialOrDefault(ItemStack item){
        return hasNoItem(item) ? DEFAULT_MATERIAL : item.getType();
    }

    public static ItemStack createItem(final Material material){return createItem(material, 1, Component.empty());}
    public static ItemStack createItem(final Material material, final int amount){return createItem(material, amount, Component.empty());}
    public static ItemStack createItem(final Material material, final String name){return createItem(material, 1, Component.text(name));}
    public static ItemStack createItem(final Material material, final String name, final String... lore){return createItem(material, 1, name, lore);}
    public static ItemStack createItem(final Material material, final Component name){return createItem(material, 1, name);}
    public static ItemStack createItem(final Material material, final Component name, final Component... lore){return createItem(material, 1, name, lore);}
    public static ItemStack createItem(final Material material, final int amount, final String name){return createItem(material, amount, Component.text(name));}
    public static ItemStack createItem(final Material material, final int amount, final String name, final String... lore){return createItem(material, amount, Component.text(name), lore == null ? new Component[0] : Arrays.stream(lore).map(Component::text).toArray(Component[]::new));}
    public static ItemStack createItem(final Material material, final int amount, final Component name){return createItem(material, amount, name, new Component[0]);}
    public static ItemStack createItem(final Material material, final int amount, final Component name, final Component... lore) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.displayName(TextUtil.clearItalic(name));
        if (lore != null && lore.length > 0) {
            meta.lore(Arrays.stream(lore).map(TextUtil::clearItalic).toList());
        }
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

    public static ItemStack changeName(final ItemStack item, final String name){return changeName(item, Component.text(name));}
    public static ItemStack changeName(final ItemStack item, final Component name) {
        final ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.displayName(TextUtil.clearItalic(name));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack changeLore(final ItemStack item, final String... lore){return changeLore(item, lore == null ? new Component[0] : Arrays.stream(lore).map(Component::text).toArray(Component[]::new));}
    public static ItemStack changeLore(final ItemStack item, final Component... lore) {
        final ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.lore(lore == null || lore.length == 0 ? null : Arrays.stream(lore).map(TextUtil::clearItalic).toList());
        item.setItemMeta(meta);
        return item;
    }

    public static CompletableFuture<ItemStack> createPlayerHead(String uuid){return createPlayerHead(UUID.fromString(uuid));}
    public static CompletableFuture<ItemStack> createPlayerHead(UUID uuid) {
        return PlayerProfileCache.get(uuid).thenApply(profile -> {
            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            if (profile != null && item.getItemMeta() instanceof SkullMeta meta) {
                meta.setPlayerProfile(profile);
                item.setItemMeta(meta);
            }
            return item;
        });
    }

    public static void createPlayerHead(String uuid, Consumer<ItemStack> callback){createPlayerHead(UUID.fromString(uuid), callback);}
    public static void createPlayerHead(UUID uuid, Consumer<ItemStack> callback) {
        createPlayerHead(uuid).thenAccept(callback);
    }
}
