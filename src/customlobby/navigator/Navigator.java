package customlobby.navigator;

import customlobby.utils.ItemAPI;
import net.minecraft.server.v1_8_R3.BlockStainedGlassPane;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Navigator {

    public static void createNavigatorGUI(Player p) {
        Integer i = 0;
        Inventory inv = Bukkit.createInventory(null, 36, "§bNavigator");

        while(i < 35) {
            ++i;
            ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIGHT_BLUE.getData());
            inv.setItem(i, pane);
        }
        inv.setItem(10, ItemAPI.createItem(Material.GRASS, "SkyBlock", (byte)0, 1));
        inv.setItem(12, ItemAPI.createItem(Material.BRICK, "CityBuild", (byte) 0, 1));
        inv.setItem(14, ItemAPI.createItem(Material.CHEST, "SkyWars", (byte) 0, 1));
        inv.setItem(16, ItemAPI.createItem(Material.IRON_SWORD, "PVP", (byte) 0, 1));
        inv.setItem(20, ItemAPI.createItem(Material.FIREBALL, "Nuke", (byte) 0, 1));
        inv.setItem(24, ItemAPI.createSkull("MayusYT", "Lobbyspiele",1));
        p.openInventory(inv);
    }
}
