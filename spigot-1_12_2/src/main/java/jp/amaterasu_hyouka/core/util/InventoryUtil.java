package jp.amaterasu_hyouka.core.util;


import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventoryUtil {
    private InventoryUtil(){}

    public static Inventory cloneInventory(InventoryHolder holder, Inventory originalInventory){
        Inventory copyInventory = Bukkit.createInventory(holder,originalInventory.getSize(), originalInventory.getTitle());
        copyInventory.setContents(originalInventory.getContents());
        return copyInventory;
    }

}
