package Listeners;

import customlobby.CustomLobby;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;


    public class DoubleJump implements Listener {

        @EventHandler
        public void onJoin(PlayerJoinEvent e) {
            Player p = e.getPlayer();
            p.setAllowFlight(true);
            p.setFlying(false);

        }

        @EventHandler
        public void onFly(PlayerToggleFlightEvent e) {
            Player p = e.getPlayer();
            if (p.getGameMode() == GameMode.SURVIVAL) {
                e.setCancelled(true);
                p.setAllowFlight(false);
                p.setFlying(false);
                if(Float.parseFloat(CustomLobby.getInstance().getConfig().getString("settings.doublejump")) != -1) {
                    p.setVelocity(p.getLocation().getDirection().add(new Vector(Float.parseFloat(CustomLobby.getInstance().getConfig().getString("settings.doublejump")) / 2, Float.parseFloat(CustomLobby.getInstance().getConfig().getString("settings.doublejump")), 0)));
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 3, 2);

                    for (int i = 0; i < 50; i++) {
                        p.playEffect(p.getLocation(), Effect.LARGE_SMOKE, 20);
                    }
                  }
                }

             }

        @EventHandler
        public void onMove(PlayerMoveEvent e) {
            Player p = e.getPlayer();
            if(p.getGameMode() == GameMode.SURVIVAL) {
                if(p.getLocation().add(0,-1, 0).getBlock().getType() != Material.AIR) {
                    p.setAllowFlight(true);
                    p.setFlying(false);
                }
            }

        }

    }


