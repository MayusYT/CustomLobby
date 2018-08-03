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
            if(p.getInventory().getBoots().equals(new ItemStack(Material.DIAMOND_BOOTS))) {
                p.setAllowFlight(true);
            } else {

                    p.setAllowFlight(false);


            }
        } else {
            if(p.getGameMode() == GameMode.SURVIVAL) {
                p.setAllowFlight(false);
            } else {
                p.setAllowFlight(true);
            }

        }

    }


}
