package jp.amaterasu_hyouka.core.inventory;

import jp.amaterasu_hyouka.core.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public final class InventoryPage {
    private InventoryPage(){}

    public static void setPageItem(Inventory inventory, InventoryPageLayout layout, List<ItemStack> items, int page) {
        validatePage(page);
        int[] slots = layout.contentSlots();
        int size = Math.min(items.size(), slots.length);
        for (int i = 0; i < size; i++) inventory.setItem(slots[i], items.get(i));
        setPageMoveItem(inventory, layout, page, items.size() >= slots.length);
    }
    public static void setPageItem(Inventory inventory, InventoryPageLayout layout, List<ItemStack> items, int page, int maxPage) {
        validatePage(page);
        int[] slots = layout.contentSlots();
        int size = Math.min(items.size(), slots.length);
        for (int i = 0; i < size; i++) inventory.setItem(slots[i], items.get(i));
        setPageMoveItem(inventory, layout, page, page < maxPage);
    }
    private static void validatePage(int page) {
        if (page <= 0)throw new IllegalArgumentException("page は 1 以上である必要があります");
    }
    private static void setPageMoveItem(Inventory inventory, InventoryPageLayout layout, int page, boolean hasNext) {
        inventory.setItem(layout.prevSlot(), page > 1
            ? ItemUtil.createItem(Material.ARROW, page - 1, (page - 1) + "ページ目へ")
            : ItemUtil.createItem(Material.STRUCTURE_VOID)
        );
        inventory.setItem(layout.nextSlot(), hasNext
            ? ItemUtil.createItem(Material.ARROW, page + 1, (page + 1) + "ページ目へ")
            : ItemUtil.createItem(Material.STRUCTURE_VOID)
        );
    }

    public static boolean isMovePageAction(InventoryPageLayout layout, int slot, Material material){
        return material == Material.ARROW && (slot == layout.prevSlot() || slot == layout.nextSlot());
    }

    public static Integer getPageDelta(InventoryPageLayout layout, int slot, Material material){
        if(material == Material.ARROW){
            if(slot == layout.prevSlot())return -1;
            if(slot == layout.nextSlot())return 1;
        }
        return null;
    }
    public static Integer getPageDelta(InventoryPageLayout layout, InventoryClickContext c){
        if(c.material() == Material.ARROW){
            if(c.slot() == layout.prevSlot())return -1;
            if(c.slot() == layout.nextSlot())return 1;
        }
        return null;
    }
}
