package customlobby.crates;

import customlobby.CustomLobby;
import customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class CratesGui {
    public static final int MAIN_GUI = 0;

    public static Inventory getInventory(int Gui, Player p) {
        Inventory inv = Bukkit.createInventory(null, 36, "§bCrates");//23
        List<String> lore = new ArrayList<String>();
        CustomLobby.getInstance().saveConfig();
        CustomLobby.getInstance().reloadConfig();

        int count = CustomLobby.getInstance().getConfig().getInt("player." + p.getName() + ".crates");

        lore.add("You have " + count + "lores");
        inv.setItem(23, ItemAPI.createItemWithLore(Material.CHEST, "§6Crates", (byte)0, 1, lore));

        return inv;
    }
}
