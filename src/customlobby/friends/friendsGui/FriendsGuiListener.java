package customlobby.friends.friendsGui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class FriendsGuiListener implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInvClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();


        if(e.getClickedInventory().getName().equalsIgnoreCase("§9Deine Freunde")) {

            String name = e.getCurrentItem().getItemMeta().getDisplayName();

            name = name.substring(1);
            p.sendMessage(name);


        }

    }

}
