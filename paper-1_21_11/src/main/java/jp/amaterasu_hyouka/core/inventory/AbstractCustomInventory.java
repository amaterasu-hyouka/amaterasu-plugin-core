package jp.amaterasu_hyouka.core.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import jp.amaterasu_hyouka.core.inventory.element.InventoryItem;
import jp.amaterasu_hyouka.core.inventory.element.InventoryItemAction;
import jp.amaterasu_hyouka.core.inventory.element.InventorySlotItem;
import jp.amaterasu_hyouka.core.inventory.element.InventorySlotItemAction;
import jp.amaterasu_hyouka.core.util.ItemUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractCustomInventory implements CustomInventoryClickListener {
    protected final CustomInventoryHolder baseHolder;
    protected final Component baseTitle;
    protected final Inventory baseInventory;
    protected final Map<Integer, Map<Material, Consumer<InventoryClickContext>>> actionMap = new HashMap<>();

    protected AbstractCustomInventory(int size, String title) {
        this.baseHolder = new CustomInventoryHolder(this);
        this.baseTitle = Component.text(title);
        this.baseInventory = Bukkit.createInventory(baseHolder, size, baseTitle);
    }

    protected AbstractCustomInventory(int size, Component title) {
        this.baseHolder = new CustomInventoryHolder(this);
        this.baseTitle = title;
        this.baseInventory = Bukkit.createInventory(baseHolder, size, baseTitle);
    }

    public void setInventory(Player p) {
        p.openInventory(baseInventory);
    }

    @Override
    public void handle(InventoryClickEvent e) {
        InventoryClickContext c = InventoryClickContext.from(e);
        runAction(c);
    }

    protected boolean runAction(InventoryClickContext c) {
        Consumer<InventoryClickContext> action = getAction(c);
        if (action == null) return false;
        action.accept(c);
        return true;
    }

    protected Consumer<InventoryClickContext> getAction(InventoryClickContext c) {
        Map<Material, Consumer<InventoryClickContext>> slotActions = actionMap.get(c.slot());
        return slotActions != null ? slotActions.get(c.material()) : null;
    }

    protected Inventory cloneBaseInventory() {
        Inventory inventory = Bukkit.createInventory(baseHolder, baseInventory.getSize(), baseTitle);
        inventory.setContents(baseInventory.getContents());
        return inventory;
    }
    protected Inventory cloneBaseInventory(InventoryHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, baseInventory.getSize(), baseTitle);
        inventory.setContents(baseInventory.getContents());
        return inventory;
    }

    protected boolean isNoAction(InventoryClickContext c) {
        return c.material() == Material.GRAY_DYE;
    }

    protected <E extends Enum<E> & InventorySlotItem> boolean matchesAction(InventoryClickContext c, E element) {return matchesAction(c.slot(), c.item(), element);}
    protected <E extends Enum<E> & InventorySlotItem> boolean matchesAction(int slot, ItemStack item, E element) {return matchesAction(slot, item == null ? Material.AIR : item.getType(), element);}
    protected <E extends Enum<E> & InventorySlotItem> boolean matchesAction(int slot, Material material, E element) {
        return element.slot() == slot && element.item().getType() == material;
    }

    protected <E extends Enum<E> & InventorySlotItemAction> void setItemAndRegisterAction(Class<E> elementClass){for(E element : elementClass.getEnumConstants())setItemAndRegisterAction(element);}
    protected <E extends Enum<E> & InventorySlotItemAction> void setItemAndRegisterAction(E element){setItemAndRegisterAction(element.slot(), element.item(), element.action());}
    protected <E extends Enum<E> & InventorySlotItem> void setItemAndRegisterAction(E element, Consumer<InventoryClickContext> action){setItemAndRegisterAction(element.slot(), element.item(), action);}
    protected <E extends Enum<E> & InventoryItemAction> void setItemAndRegisterAction(int slot, E element){setItemAndRegisterAction(slot, element.item(), element.action());}
    protected void setItemAndRegisterAction(int slot, ItemStack item, Consumer<InventoryClickContext> action){
        setItem(slot, item);
        registerAction(slot, item, action);
    }

    protected <E extends Enum<E> & InventorySlotItem> void setItem(Class<E> elementClass){for(E element : elementClass.getEnumConstants())setItem(element.slot(), element.item());}
    protected <E extends Enum<E> & InventorySlotItem> void setItem(E element){setItem(element.slot(), element.item());}
    protected <E extends Enum<E> & InventoryItem> void setItem(int slot, E element){setItem(slot, element.item());}
    protected void setItem(int slot, ItemStack item){
        if (slot == -1 || ItemUtil.hasNoItem(item)) return;
        baseInventory.setItem(slot, item);
    }

    protected <E extends Enum<E> & InventorySlotItemAction> void registerAction(E element){registerAction(element.slot(), element.item(), element.action());}
    protected <E extends Enum<E> & InventoryItemAction> void registerAction(int slot, E element){registerAction(slot, element.item(), element.action());}
    protected <E extends Enum<E> & InventorySlotItem> void registerAction(E element, Consumer<InventoryClickContext> action){registerAction(element.slot(), element.item(), action);}
    protected void registerAction(int slot, ItemStack item, Consumer<InventoryClickContext> action){registerAction(slot, item.getType(), action);}
    protected void registerAction(int slot, Material material, Consumer<InventoryClickContext> action){
        actionMap.computeIfAbsent(slot, k -> new HashMap<>()).put(material, action);
    }
}
