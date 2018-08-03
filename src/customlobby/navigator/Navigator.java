package customlobby.navigator;

import customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Navigator {
    @Deprecated
    public static void createNavigatorGUI(Player p) {
        Integer i = -1;
        Inventory inv = Bukkit.createInventory(null, 36, "§bNavigator");

        while(i < 35) {
            ++i;
            ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIGHT_BLUE.getData());
            ItemMeta panemeta = pane.getItemMeta();
            panemeta.setDisplayName(" ");
            pane.setItemMeta(panemeta);
            inv.setItem(i, pane);
        }
        inv.setItem(10, ItemAPI.createItem(Material.GRASS, "§aSky§7Block", (byte)0, 1));
        inv.setItem(12, ItemAPI.createItem(Material.BRICK, "§4City§bBuild", (byte) 0, 1));
        inv.setItem(14, ItemAPI.createItem(Material.CHEST, "§3Sky§7Wars", (byte) 0, 1));
        inv.setItem(16, ItemAPI.createItem(Material.IRON_SWORD, "§cPVP", (byte) 0, 1));
        inv.setItem(20, ItemAPI.createItem(Material.FIREBALL, "§eNuke", (byte) 0, 1));
        inv.setItem(24, ItemAPI.createSkull("MayusYT", "§4L§co§6b§eb§2y§as§bp§3i§1e§9l§de",1));
        p.openInventory(inv);
    }
}
