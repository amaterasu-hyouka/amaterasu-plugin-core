package jp.amaterasu_hyouka.core.inventory;

import jp.amaterasu_hyouka.core.inventory.element.InventoryItem;
import jp.amaterasu_hyouka.core.inventory.element.InventoryItemAction;
import jp.amaterasu_hyouka.core.inventory.element.InventorySlotItem;
import jp.amaterasu_hyouka.core.inventory.element.InventorySlotItemAction;
import jp.amaterasu_hyouka.core.listener.CustomInventoryClickListener;
import jp.amaterasu_hyouka.core.util.InventoryUtil;
import jp.amaterasu_hyouka.core.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractCustomInventory implements CustomInventoryClickListener {
    protected final CustomInventoryHolder baseHolder;
    protected final Inventory baseInventory;
    protected final Map<Integer, Map<Material, Consumer<Player>>> actionMap = new HashMap<>();

    protected AbstractCustomInventory(InventoryRow row, String title) {
        this.baseHolder = new CustomInventoryHolder(this);
        this.baseInventory = Bukkit.createInventory(baseHolder, row.size(), title);
    }

    public void setInventory(Player p) {
        p.openInventory(baseInventory);
    }
    @Override
    public void handle(InventoryClickEvent e){
        runAction(new InventoryClickContext(e));
    }

    protected boolean runAction(InventoryClickContext c){
        Consumer<Player> action = getAction(c);
        if(action == null)return false;
        action.accept(c.player());
        return true;
    }

    protected Consumer<Player> getAction(int slot, Material material){
        Map<Material, Consumer<Player>> slotActions = actionMap.get(slot);
        return slotActions != null ? slotActions.get(material) : null;
    }
    protected Consumer<Player> getAction(InventoryClickContext c){
        Map<Material, Consumer<Player>> slotActions = actionMap.get(c.slot());
        return slotActions != null ? slotActions.get(c.material()) : null;
    }

    protected Inventory cloneBaseInventory(){
        return InventoryUtil.cloneInventory(baseHolder, baseInventory);
    }

    protected <E extends Enum<E> & InventorySlotItem> boolean matchesAction(int slot, Material material, E element){
        return element.slot() == slot && element.item().getType() == material;
    }
    protected <E extends Enum<E> & InventorySlotItem> boolean matchesAction(InventoryClickContext c, E element){
        return element.slot() == c.slot() && element.item().getType() == c.material();
    }

    protected boolean isNoAction(ItemStack item){
        Material material = item.getType();
        return material == null || material == Material.INK_SACK && item.getDurability() == 8;
    }

    protected <E extends Enum<E> & InventorySlotItemAction> void setItemAndRegisterAction(Class<E> elementClass){for(E element: elementClass.getEnumConstants())setItemAndRegisterAction(element);}
    protected <E extends Enum<E> & InventorySlotItemAction> void setItemAndRegisterAction(E element){setItemAndRegisterAction(element.slot(), element.item(), element.action());}
    protected <E extends Enum<E> & InventorySlotItem> void setItemAndRegisterAction(E element, Consumer<Player> action){setItemAndRegisterAction(element.slot(), element.item(), action);}
    protected <E extends Enum<E> & InventoryItemAction> void setItemAndRegisterAction(int slot, E element){setItemAndRegisterAction(slot, element.item(), element.action());}
    protected void setItemAndRegisterAction(int slot, ItemStack item, Consumer<Player> action){
        setItem(slot, item);
        registerAction(slot, item, action);
    }

    protected void setItem(List<InventorySlotItem> list){
        if(list == null)return;
        for(InventorySlotItem inventorySlotItem: list){
            setItem(inventorySlotItem.slot(), inventorySlotItem.item());
        }
    }
    protected <E extends Enum<E> & InventorySlotItem> void setItem(Class<E> elementClass){for(E element: elementClass.getEnumConstants())setItem(element.slot(), element.item());}
    protected <E extends Enum<E> & InventorySlotItem> void setItem(E element){setItem(element.slot(), element.item());}
    protected <E extends Enum<E> & InventoryItem> void setItem(int slot, E element){setItem(slot, element.item());}
    protected void setItem(int slot, ItemStack item){
        if(slot == -1 || ItemUtil.hasNoItem(item))return;
        baseInventory.setItem(slot, item);
    }

    protected <E extends Enum<E> & InventorySlotItemAction> void registerAction(E element){registerAction(element.slot(), element.item(), element.action());}
    protected <E extends Enum<E> & InventoryItemAction> void registerAction(int slot, E element){registerAction(slot, element.item(), element.action());}
    protected <E extends Enum<E> & InventorySlotItem> void registerAction(E element, Consumer<Player> action){registerAction(element.slot(), element.item(), action);}
    protected void registerAction(int slot, ItemStack item, Consumer<Player> action){registerAction(slot, item.getType(), action);}
    protected void registerAction(int slot, Material material, Consumer<Player> action){
        actionMap.computeIfAbsent(slot, k -> new HashMap<>()).put(material, action);
    }

    protected <E extends Enum<E> & InventorySlotItem> void setItem(Inventory inventory, Class<E> elementClass){for(E element: elementClass.getEnumConstants())setItem(element.slot(), element.item());}
    protected <E extends Enum<E> & InventorySlotItem> void setItem(Inventory inventory, E element){setItem(element.slot(), element.item());}
    protected <E extends Enum<E> & InventoryItem> void setItem(Inventory inventory, int slot, E element){setItem(slot, element.item());}
    protected void setItem(Inventory inventory, int slot, ItemStack item){
        if(slot == -1 || ItemUtil.hasNoItem(item))return;
        inventory.setItem(slot, item);
    }
}
