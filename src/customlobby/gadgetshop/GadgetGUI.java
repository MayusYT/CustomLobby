package customlobby.gadgetshop;

import customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GadgetGUI {


    public static Inventory createGadgetInventory() {
        Inventory inv = Bukkit.createInventory(null, 27, "§2Gadget§r - §bShop");
        int i = -1;
        while(i < 26) {
            ++i;
            ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIGHT_BLUE.getData());
            ItemMeta panemeta = pane.getItemMeta();
            panemeta.setDisplayName(" ");
            pane.setItemMeta(panemeta);
            inv.setItem(i, pane);
        }

        inv.setItem(9, ItemAPI.createSkull("Notch", "§3Köpfe", 1));
        inv.setItem(11, ItemAPI.createItem(Material.BANNER, "§5Banner", (byte) 0, 1));
        inv.setItem(13, ItemAPI.createItem(Material.DIAMOND_BOOTS, "§fSchuhe", (byte) 0, 1));
        inv.setItem(15, ItemAPI.createItem(Material.ARMOR_STAND, "§eSkins", (byte) 0, 1));
        inv.setItem(17, ItemAPI.createItem(Material.CHEST, "§cCrates", (byte) 0, 1));

        return inv;
    }




}
