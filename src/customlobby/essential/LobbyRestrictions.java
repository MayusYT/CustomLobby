package customlobby.essential;


import com.connorlinfoot.titleapi.TitleAPI;
import customlobby.CustomLobby;
import customlobby.crates.CratesGui;
import customlobby.gadgetshop.GadgetGUI;
import customlobby.hide.Hide;
import customlobby.navigator.Navigator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LobbyRestrictions implements Listener {

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(KillECMD.allowKills == false) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent event) {

        boolean rain = event.toWeatherState();
        if (rain)
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent event) {

        boolean storm = event.toThunderState();
        if (storm)
            event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if (!e.getPlayer().hasPermission("CustomLobby.pickupItems")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(SpawnCMD.spawnIsDefined()) {
            e.getPlayer().teleport(SpawnCMD.spawnLoc);
        }
        e.getPlayer().getInventory().clear();
        StartItems.setStarterItems(e.getPlayer());
        String title = CustomLobby.getInstance().getConfig().getString("settings.jointitle");
        String subtitle = CustomLobby.getInstance().getConfig().getString("settings.joinsubtitle");

        TitleAPI.sendTitle(e.getPlayer(), 1, 10, 1, title.replace("{player}", e.getPlayer().getName()));
        TitleAPI.sendSubtitle(e.getPlayer(), 1, 10, 1, title.replace("{player}", e.getPlayer().getName()));

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!BuildMode.buildmodeplayers.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        CustomLobby.getInstance().saveConfig();
        CustomLobby.getInstance().reloadConfig();
        Player p = (Player) e.getWhoClicked();
        //Navigator
        //TODO: Redneck lösung
        if(e.getInventory().getSize() == 36 && e.getSlot() != -999) {

            int slot = e.getSlot();
            int row = -1;
           if(slot < 9) {
                row = 0;
           } else if (slot > 8 && slot < 18) {
               row = 1;
           } else if (slot > 17 && slot < 27) {
               row = 2;
           } else if (slot > 26 && slot < 37) {
               row = 3;
           }
           int col = slot - (row * 9);

            //p.sendMessage(CustomLobby.getInstance().getConfig().getString("navigator.row." + row + "." + (slot - (row * 9)) + ".action"));
            if(CustomLobby.getInstance().getConfig().getString("navigator.row." + row + "." + (slot - (row * 9)) + ".action") != null) {
                if (BuildMode.buildmodeplayers.contains(p.getName())) {
                    p.sendMessage(CustomLobby.getInstance().getConfig().getString("navigator.row." + row + "." + (slot - (row * 9)) + ".action"));
                }
                CustomLobby.getInstance().getServer().dispatchCommand(p, CustomLobby.getInstance().getConfig().getString("navigator.row." + row + "." + (slot - (row * 9)) + ".action"));
            }
            /*if(e.getCurrentItem().getType() == Material.BRICK) {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("CB1");

                p.sendPluginMessage(CustomLobby.getInstance(), "BungeeCord", out.toByteArray());
            }
            if(e.getCurrentItem().getType() == Material.CHEST) {
                Location loc = new Location(Bukkit.getWorld(CustomLobby.getInstance().getConfig().getString("warps.skywars.WORLD")), Double.parseDouble(CustomLobby.getInstance().getConfig().getString("warps.skywars.X")), Double.parseDouble(CustomLobby.getInstance().getConfig().getString("warps.skywars.Y")), Double.parseDouble(CustomLobby.getInstance().getConfig().getString("warps.skywars.Z")), Float.parseFloat(CustomLobby.getInstance().getConfig().getString("warps.skywars.PITCH")), Float.parseFloat(CustomLobby.getInstance().getConfig().getString("warps.skywars.YAW")));
                p.teleport(loc);
                p.playSound(loc, Sound.ENDERMAN_TELEPORT, 10, 10);
            }
            if(e.getCurrentItem().getType() == Material.IRON_SWORD) {
                Location loc = new Location(Bukkit.getWorld(CustomLobby.getInstance().getConfig().getString("warps.pvp.WORLD")), Double.parseDouble(CustomLobby.getInstance().getConfig().getString("warps.pvp.X")), Double.parseDouble(CustomLobby.getInstance().getConfig().getString("warps.pvp.Y")), Double.parseDouble(CustomLobby.getInstance().getConfig().getString("warps.pvp.Z")), Float.parseFloat(CustomLobby.getInstance().getConfig().getString("warps.pvp.PITCH")), Float.parseFloat(CustomLobby.getInstance().getConfig().getString("warps.pvp.YAW")));
                p.teleport(loc);
                p.playSound(loc, Sound.ENDERMAN_TELEPORT, 5, 10);
            }*/




        }


        //Gatgets
        if(e.getInventory().getName().equalsIgnoreCase("§2Gadget§r - §bShop")) {

        }




        //TODO: Implement
        //Friend
        if(e.getInventory().getName().equalsIgnoreCase("§9Profil, Freunde und Parties")) {



        }


        if(!BuildMode.buildmodeplayers.contains(p.getName())) {
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
        Material chest = Material.CHEST;
        Material skull = Material.SKULL_ITEM;
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR) {

            if(item == null) {
                item = new ItemStack(Material.AIR);
            }

            if (item.getType() == compass) {
                Navigator.createNavigatorGUI(p);
            }
            if (item.getType() == blazerod) {
                if (!Hide.ishidden) {
                    Hide.hideall(p);
                } else {
                    Hide.showall(p);
                }


            } if(item.getType() == chest) {
                Inventory inv = GadgetGUI.createGadgetInventory();
                p.openInventory(inv);
            }

            if (item.getType() == skull) {
                //FriendGUI.createFriendsGUI(p);
            }

            if(e.getClickedBlock().getType() == Material.ENDER_CHEST) {
                e.getPlayer().openInventory(CratesGui.getInventory(CratesGui.MAIN_GUI, e.getPlayer()));
            }

            e.setCancelled(true);
        }
        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (BuildMode.buildmodeplayers.contains(p.getName())) {
                e.setCancelled(false);
            } else {
                if (item.getType() == compass) {
                    Navigator.createNavigatorGUI(p);
                }
                if(item.getType() == chest) {
                    Inventory inv = GadgetGUI.createGadgetInventory();
                    p.openInventory(inv);
                }
                if (item.getType() == blazerod) {
                    if (!Hide.ishidden) {
                        Hide.hideall(p);
                    } else {
                        Hide.showall(p);
                    }
                }

                e.setCancelled(true);

            }
            if (e.getAction() == Action.PHYSICAL) {
                Block block = e.getClickedBlock();
                if (block == null) return;
                // If the block is farmland (soil)
                if (block.getType() == Material.SOIL) {
                    // Deny event and set the block
                    e.setUseInteractedBlock(org.bukkit.event.Event.Result.DENY);
                    e.setCancelled(true);

                    block.setTypeIdAndData(block.getType().getId(), block.getData(), true);
                }
            }

        }
    }
}
