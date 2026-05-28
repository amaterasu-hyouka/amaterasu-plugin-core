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
import org.bukkit.inventory.ItemStack;

public abstract class AbstractCustomInventory implements CustomInventoryClickListener {
    protected final CustomInventoryHolder baseHolder;
    protected final Inventory baseInventory;
    protected final Map<Integer, Map<Material, Consumer<Player>>> actionMap = new HashMap<>();

    protected AbstractCustomInventory(int size, String title) {
        this.baseHolder = new CustomInventoryHolder(this);
        this.baseInventory = Bukkit.createInventory(baseHolder, size, Component.text(title));
    }

    protected AbstractCustomInventory(int size, Component title) {
        this.baseHolder = new CustomInventoryHolder(this);
        this.baseInventory = Bukkit.createInventory(baseHolder, size, title);
    }

    public void setInventory(Player p) {
        p.openInventory(baseInventory);
    }

    @Override
    public void handle(InventoryClickEvent e) {
        InventoryClickContext c = new InventoryClickContext(e);
        runAction(c);
    }

    protected boolean runAction(InventoryClickContext c) {
        Consumer<Player> action = getAction(c);
        if (action == null) return false;
        action.accept(c.player());
        return true;
    }

    protected Consumer<Player> getAction(InventoryClickContext c) {
        Map<Material, Consumer<Player>> slotActions = actionMap.get(c.slot());
        return slotActions != null ? slotActions.get(c.material()) : null;
    }

    protected boolean isNoAction(InventoryClickContext c) {
        return c.material() == Material.GRAY_DYE;
    }

    protected <E extends Enum<E> & InventorySlotItem> boolean matchesAction(InventoryClickContext c, E element) {
        return element.slot() == c.slot() && element.item().getType() == c.material();
    }

    protected <E extends Enum<E> & InventorySlotItemAction> void setItemAndRegisterAction(Class<E> elementClass){for(E element : elementClass.getEnumConstants())setItemAndRegisterAction(element);}
    protected <E extends Enum<E> & InventorySlotItemAction> void setItemAndRegisterAction(E element){setItemAndRegisterAction(element.slot(), element.item(), element.action());}
    protected <E extends Enum<E> & InventorySlotItem> void setItemAndRegisterAction(E element, Consumer<Player> action){setItemAndRegisterAction(element.slot(), element.item(), action);}
    protected <E extends Enum<E> & InventoryItemAction> void setItemAndRegisterAction(int slot, E element){setItemAndRegisterAction(slot, element.item(), element.action());}
    protected void setItemAndRegisterAction(int slot, ItemStack item, Consumer<Player> action){
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
    protected <E extends Enum<E> & InventorySlotItem> void registerAction(E element, Consumer<Player> action){registerAction(element.slot(), element.item(), action);}
    protected void registerAction(int slot, ItemStack item, Consumer<Player> action){
        actionMap.computeIfAbsent(slot, k -> new HashMap<>()).put(item.getType(), action);
    }
}
