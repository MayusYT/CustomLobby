package customlobby.boots;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class BootListener implements Listener {


    // Boot Listener
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getInventory().getBoots() != null) {
            //Diamond Boots
            if(p.getInventory().getBoots().equals(new ItemStack(Material.DIAMOND_BOOTS))) {
                p.setAllowFlight(true);
            } else {
                    p.setAllowFlight(false);
            }

            //Gold Boots
            if(p.getInventory().getBoots().equals(new ItemStack(Material.GOLD_BOOTS))) {
                p.setWalkSpeed(0.6f);
            } else {
                p.setWalkSpeed(0.2f);
            }

            //Chainmail Boots
            if(p.getInventory().getBoots().equals(new ItemStack(Material.CHAINMAIL_BOOTS))) {
                p.setPlayerListName("§k" + p.getName() + "§r");
            } else {
                p.setPlayerListName(p.getName());
            }

        } else {
            p.setAllowFlight(false);
            p.setWalkSpeed(0.2f);
            p.setPlayerListName(p.getName());
        }
    }




}
