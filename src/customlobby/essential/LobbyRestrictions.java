package customlobby.essential;

import customlobby.banmanager.BanmanagerCfg;
import customlobby.hide.Hide;
import customlobby.navigator.Navigator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
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
        if(BanmanagerCfg.onTempBanList(e.getPlayer().getName()) && BanmanagerCfg.stillBanned(e.getPlayer())) {
            if (!e.getPlayer().hasPermission("CustomLobby.JoinEvenWithBan")) {
                e.getPlayer().kickPlayer("Du bist gebannt!");
            }

        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(!BuildMode.buildmodeplayers.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }
    @Deprecated
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        Material compass = Material.COMPASS;
        Material blazerod = Material.BLAZE_ROD;
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR) {

            if (item.getType() == compass) {
                Navigator.createNavigatorGUI(p);
            } if(item.getType() == blazerod) {
                if(!Hide.ishidden) {
                    Hide.hideall(p);
                } else {
                    Hide.showall(p);
                }

            }
            e.setCancelled(true);
        } if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(BuildMode.buildmodeplayers.contains(p.getName())) {
                e.setCancelled(false);
            } else {
                if(item.getType() == compass) {
                    Navigator.createNavigatorGUI(p);
                } if(item.getType() == blazerod) {
                    if(!Hide.ishidden) {
                        Hide.hideall(p);
                    } else {
                        Hide.showall(p);
                    }

                    e.setCancelled(true);
            }

            //If farmland gets destroyed
        } if(e.getAction() == Action.PHYSICAL) {
            Block block = e.getClickedBlock();
            if (block == null) return;
            // If the block is farmland (soil)
            if (block.getType() == Material.SOIL){
                // Deny event and set the block
                e.setUseInteractedBlock(org.bukkit.event.Event.Result.DENY);
                e.setCancelled(true);

                block.setTypeIdAndData(block.getType().getId(), block.getData(), true);
            }
        }

    }
}
