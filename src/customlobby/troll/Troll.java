package customlobby.troll;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Troll implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem().getType() == Material.DIAMOND_SPADE) {
                Location spawnAt = player.getEyeLocation().toVector().add(player.getEyeLocation().getDirection().multiply(3)).toLocation(player.getWorld());
                Fireball fireball = player.getWorld().spawn(spawnAt, Fireball.class);
                fireball.setBounce(false);
                fireball.setDirection(fireball.getVelocity().multiply(10));
                fireball.setIsIncendiary(false);
                fireball.setYield(4F);
                fireball.setShooter(player);
            }
        }


    }
}
