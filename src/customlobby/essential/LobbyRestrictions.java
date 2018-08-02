package customlobby.essential;

import customlobby.banmanager.Banmanager;
import customlobby.banmanager.BanmanagerCfg;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Weather;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

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

        //Bann-Abfrage

        //Auf Perma-Ban Liste?
        if (BanmanagerCfg.onBanlist(e.getPlayer()) == true) {
            e.getPlayer().kickPlayer("Du bist gebannt!");
        }
        //Auf Temp-Ban Liste?
        if(BanmanagerCfg.onTempBanList(e.getPlayer()) == true /* && BanmanagerCfg.stillBanned(e.getPlayer()) == true*/) {

            e.getPlayer().kickPlayer("Du bist gebannt!");
        } else {
            Bukkit.broadcastMessage("Spieler Tallerik: Keine Vorbestraftungen");
        }
    }


}
