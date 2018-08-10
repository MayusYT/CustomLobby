package customlobby.boots;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BootListener implements Listener {


    // Boot Listener
    @SuppressWarnings("deprecation")
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
            if (p.getLocation().getBlock().getType() == Material.STONE_PLATE) {
                if (p.getLocation().subtract(0D, 1D, 0D).getBlock().getType() == Material.REDSTONE_BLOCK) {

                    Vector v = p.getLocation().getDirection().multiply(3D).setY(1D);
                    p.setVelocity(v);

                    p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1F, 1F);

                    p.setFallDistance(-999F);
                }
            }
            if(p.getGameMode() == GameMode.CREATIVE) {
                p.setAllowFlight(true);
            } else {
                p.setAllowFlight(false);
            }
            p.setWalkSpeed(0.2f);
            p.setPlayerListName(p.getName());
        }
    }




}
