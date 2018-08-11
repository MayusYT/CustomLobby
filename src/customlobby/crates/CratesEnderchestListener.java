package customlobby.crates;


import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CratesEnderchestListener implements Listener {

    @EventHandler
    public void onEnderChestClick(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == Material.ENDER_CHEST) {
                e.getPlayer().openInventory(CratesGui.getInventory(CratesGui.MAIN_GUI, e.getPlayer()));
            }
        }
        e.setCancelled(true);
    }

}
