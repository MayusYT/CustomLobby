package customlobby.crates;

import customlobby.CustomLobby;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CratesGuiEventHandler implements Listener {


    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack item = e.getCurrentItem();

        if(inv.getName().equals(CratesGui.getInventory(CratesGui.MAIN_GUI, p).getName())) {

            if(item.getType() == Material.CHEST) {

                if(CustomLobby.getInstance().getConfig().getInt("player." + p.getName() + ".crates") >= 1) {

                    OpenCrate.openCrate(p);
                    CustomLobby.getInstance().getConfig().set("player." + p.getName() + ".crates", CustomLobby.getInstance().getConfig().getInt("player." + p.getName() + ".crates") - 1);

                }

            }

            e.setCancelled(true);
        }
    }

}
