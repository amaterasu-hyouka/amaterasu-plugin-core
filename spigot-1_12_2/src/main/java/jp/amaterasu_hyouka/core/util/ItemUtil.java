package jp.amaterasu_hyouka.core.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ItemUtil {
    private ItemUtil(){}

    public static final ItemStack AIR = createItem(Material.AIR);
    public static final ItemStack DEFAULT = createItem(Material.STONE);

    public static String getEmptyString(String name) {
        if (name == null || name.isEmpty()) return ChatColor.RESET.toString();
        return ChatColor.RESET + name;
    }

    /**
     * 素材、数、名前、ロア（説明文）でアイテム作成
     *
     * @param material アイテムの素材（Material）
     * @param amount アイテムの数
     * @param name アイテムの表示名
     * @param lore アイテムの説明文（可変長引数）
     * @return 作成されたアイテム（ItemStack）
     */
    public static ItemStack createItem(Material material, int amount, short data, String name, String... lore) {
        ItemStack item = new ItemStack(material, amount, data);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;

        meta.setDisplayName(getEmptyString(name));

        if (lore != null && lore.length > 0) {
            meta.setLore(Arrays.asList(lore));
        }

        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack createItem(Material material) {
        return createItem(material, 1, (short) 0, "");
    }
    public static ItemStack createItem(Material material, int amount) {
        return createItem(material, amount, (short) 0, "");
    }
    public static ItemStack createItem(Material material, int amount, short data) {
        return createItem(material, amount, data, "");
    }
    public static ItemStack createItem(Material material, int amount, String name) {
        return createItem(material, amount, (short) 0, name);
    }
    public static ItemStack createItem(Material material, int amount, String name, String... lore) {
        return createItem(material, amount, (short) 0, name, lore);
    }
    public static ItemStack createItem(Material material, short data) {
        return createItem(material, 1, data, "");
    }
    public static ItemStack createItem(Material material, short data, String name) {
        return createItem(material, 1, data, name);
    }
    public static ItemStack createItem(Material material, short data, String name, String... lore) {
        return createItem(material, 1, data, name, lore);
    }
    public static ItemStack createItem(Material material, String name) {
        return createItem(material, 1, (short) 0, name);
    }
    public static ItemStack createItem(Material material, String name, String... lore) {
        return createItem(material, 1, (short) 0, name, lore);
    }

    /**
     * アイテムにエンチャントを付与
     *
     * @param item エンチャントを付与するアイテム（ItemStack）
     * @return エンチャントが付与された同じアイテム（ItemStack）
     */
    public static ItemStack enchantItem(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.LUCK,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * アイテムの名前と説明文変更
     *
     * @param item 変更するアイテム（ItemStack）
     * @param name 新しいアイテム名
     * @param lore 新しい説明文（Lore）
     * @return 名前と説明文が変更された同じアイテム（ItemStack）
     */
    public static ItemStack changeItemName(ItemStack item, String name, String... lore){
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getEmptyString(name));
        if (lore != null && lore.length > 0) {
            meta.setLore(Arrays.asList(lore));
        }
        item.setItemMeta(meta);
        return item;
    }


    private static final short PLAYER_HEAD_DATA = 3;
    /**
     * プレイヤーネームとロア（説明文）からプレイヤー頭作成
     *
     * @param playerName プレイヤーの名前
     * @param lore アイテムの説明文（Lore）
     * @return 作成されたプレイヤーの頭（ItemStack）
     */
    @SuppressWarnings("deprecation")
    public static ItemStack createPlayerHeadFromName(String playerName, String... lore) {
        ItemStack item = createItem(Material.SKULL_ITEM, PLAYER_HEAD_DATA);
        SkullMeta skullmeta = (SkullMeta) item.getItemMeta();
        skullmeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
        skullmeta.setDisplayName(getEmptyString(playerName));

        if (lore != null && lore.length > 0) {
            skullmeta.setLore(Arrays.asList(lore));
        }

        item.setItemMeta(skullmeta);
        return item;
    }
    @SuppressWarnings("deprecation")
    public static ItemStack createPlayerHeadFromUuid(String playerUuid, String playerName, String... lore) {
        ItemStack item = createItem(Material.SKULL_ITEM, PLAYER_HEAD_DATA);
        SkullMeta skullmeta = (SkullMeta) item.getItemMeta();
        skullmeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerUuid));
        skullmeta.setDisplayName(getEmptyString(playerName));

        if (lore != null && lore.length > 0) {
            skullmeta.setLore(Arrays.asList(lore));
        }

        item.setItemMeta(skullmeta);
        return item;
    }

    /**
     * テクスチャプロパティ値からカスタムヘッド作成
     *
     * @param value テクスチャプロパティ値（Base64エンコードされたスキンデータ）
     * @return 作成されたカスタムヘッド（ItemStack）
     */
    public static ItemStack createPropertyHead(String value) {//プロパティから頭アイテムを入手
        ItemStack skull = createItem(Material.SKULL_ITEM, PLAYER_HEAD_DATA);
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(skull,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}"
        );
    }

    /**
     * Itemの名前、データ値、Materialが一致しているかの判定
     *
     * @param a 一つ目のアイテム
     * @param b 二つ目のアイテム
     */
    public static boolean isSameItem(ItemStack a, ItemStack b){
        if (!hasDisplayName(a) || !hasDisplayName(b)) return false;
        return a.getItemMeta().getDisplayName().equals(b.getItemMeta().getDisplayName());
    }
    /**
     * ItemがDisplayNameを持っているかの判定
     *
     * @param item アイテム
     */
    public static boolean hasDisplayName(ItemStack item){
        return item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName();
    }
    public static String getDisplayName(ItemStack item){
        return hasDisplayName(item) ? item.getItemMeta().getDisplayName() : "";
    }

    /**
     *　StringをMaterialに変換する、できない場合STONEを返す
     *
     * @param materialName Materialの名前
     */
    public static Material materialOrDefault(String materialName){
        if (materialName == null || materialName.isEmpty() || materialName.equals("AIR")) return DEFAULT.getType();
        try {
            return Material.valueOf(materialName);
        } catch (IllegalArgumentException e) {
            return DEFAULT.getType();
        }
    }

    /**
     * 指定したloreの行(line)から、prefixで始まる値を取り出す。
     * 例: line="command: /spawn", prefix="command:" -> "/spawn"
     *
     * @param item      対象アイテム
     * @param lineIndex loreの行番号（0始まり）
     * @param prefix    先頭一致させたい文字列（例: "command:"）
     * @return 値（trim済み）。条件に合わない場合は null
     */
    public static String getValueFromLore(ItemStack item, int lineIndex, String prefix) {
        if (ItemUtil.hasNoItem(item) || prefix == null) return null;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasLore()) return null;

        List<String> lore = meta.getLore();
        if (lore == null || lineIndex < 0 || lore.size() <= lineIndex) return null;

        String line = lore.get(lineIndex);
        if (line == null) return null;

        String strippedLine = ChatColor.stripColor(line);
        if (strippedLine == null || !strippedLine.startsWith(prefix)) return null;

        return strippedLine.substring(prefix.length()).trim();
    }
    public static Integer getIntValueFromLore(ItemStack item, int lineIndex, String prefix) {
        String value = getValueFromLore(item, lineIndex, prefix);
        if (value == null) return null;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public static Double getDoubleValueFromLore(ItemStack item, int lineIndex, String prefix) {
        String value = getValueFromLore(item, lineIndex, prefix);
        if (value == null) return null;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public static Float getFloatValueFromLore(ItemStack item, int lineIndex, String prefix) {
        String value = getValueFromLore(item, lineIndex, prefix);
        if (value == null) return null;
        try {
            return Float.parseFloat(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean hasNoItem(ItemStack item){
        return item == null || item.getType() == Material.AIR;
    }
}
