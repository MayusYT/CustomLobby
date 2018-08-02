package customlobby.essential;

import customlobby.banmanager.BanmanagerCfg;
import customlobby.navigator.Navigator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class LobbyRestrictions implements Listener {

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
        e.setFoodLevel(20);
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent event) {

        boolean rain = event.toWeatherState();
        if(rain)
            event.setCancelled(true);
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent event) {

        boolean storm = event.toThunderState();
        if(storm)
            event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if(!e.getPlayer().hasPermission("CustomLobby.pickupItems")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        StartItems.setStarterItems(e.getPlayer());
        //Bann-Abfrage

        //Auf Perma-Ban Liste?
        if (BanmanagerCfg.onBanlist(e.getPlayer())) {
            if (!e.getPlayer().hasPermission("CustomLobby.JoinEvenWithBan")) {
                e.getPlayer().kickPlayer("Du bist gebannt!");
            }

        }
        //Auf Temp-Ban Liste?
        if(BanmanagerCfg.onTempBanList(e.getPlayer().getName()) /*&& BanmanagerCfg.stillBanned(e.getPlayer())*/) {
            if (!e.getPlayer().hasPermission("CustomLobby.JoinEvenWithBan")) {
                e.getPlayer().kickPlayer("Du bist gebannt!");
            }

        } else {
            Bukkit.broadcastMessage("Spieler Tallerik: Keine Vorbestraftungen");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        Material compass = Material.COMPASS;
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if(item.getType() == compass) {
                    e.getPlayer().sendMessage("Trigger:Compass");
                    Navigator.createNavigatorGUI(e.getPlayer());
                }

            } else {

            }


        e.setCancelled(true);
    }


}
