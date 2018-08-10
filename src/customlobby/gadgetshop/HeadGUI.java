package customlobby.gadgetshop;

import customlobby.essential.BuildMode;
import customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HeadGUI implements Listener {

    public static Inventory createHeadGUI() {
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


        inv.setItem(9, Heads.Notch);
        inv.setItem(10, Heads.Koala);
        inv.setItem(11, Heads.Sensenmann);
        inv.setItem(12, Heads.Auge);
        inv.setItem(13, Heads.RubiksCube);
        inv.setItem(14, Heads.Miner);
        inv.setItem(15, Heads.Batman);
        inv.setItem(16, Heads.Dog);
        inv.setItem(17, Heads.own);

        return inv;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(!BuildMode.buildmodeplayers.contains(p.getName())) {
            if(e.getClickedInventory().getName().equalsIgnoreCase("§2Gadget§r - §bShop")){
                if(e.getCurrentItem() == Heads.Notch) {

                } if(e.getCurrentItem() == Heads.Koala) {

                } if(e.getCurrentItem() == Heads.Sensenmann) {

                } if(e.getCurrentItem() == Heads.Auge) {

                } if(e.getCurrentItem() == Heads.RubiksCube) {

                } if(e.getCurrentItem() == Heads.Miner) {

                } if(e.getCurrentItem() == Heads.Batman) {

                } if(e.getCurrentItem() == Heads.Dog) {

                } if(e.getCurrentItem() == Heads.own) {

                }
                e.setCancelled(true);
            }

        } else {
            e.setCancelled(false);
        }
    }




}
