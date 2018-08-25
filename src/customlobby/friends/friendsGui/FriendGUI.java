package customlobby.friends.friendsGui;

import customlobby.CustomLobby;
import customlobby.SQL.SQLConfig;
import customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static customlobby.CustomLobby.getInstance;

public class FriendGUI {



    public static void createFriendsGUI(Player p) {

        Inventory inv = Bukkit.createInventory(null, 45, "§9Deine Freunde");

        SQLConfig sqlConfig = new SQLConfig();
        sqlConfig.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        if(sqlConfig.canConnect()) {
            p.sendMessage(CustomLobby.getPrefix() + "§cFunktion aktuell deaktiviert! §8(CODE: MYSQL_ERROR)");
            return;
        }





        p.openInventory(inv);

        List<String> friends;
        try {
             friends = sqlConfig.getFriends(p.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }


        if(friends.size() > 45) {
            List<String> friends2 = new ArrayList<>();
            for (int j = 0; j < 45; j++) {
                friends2.add(friends.get(j));
            }

            for (String fr : friends2) {

                if(Bukkit.getPlayer(fr) != null) {
                    List<String> lore = new ArrayList<>();
                    lore.add("§aOnline");
                    inv.addItem(ItemAPI.createSkullWithLore(fr, "§3" + fr, 1, lore));
                } else {
                    List<String> lore = new ArrayList<>();
                    lore.add("§bOffline");
                    inv.addItem(ItemAPI.createSkullWithLore(fr, "§3" + fr, 1, lore));
                }


            }

        } else {

            for (String fr : friends) {

                inv.addItem(ItemAPI.createSkull(fr, "§3" +  fr, 1));
            }
        }
        p.updateInventory();

    }


}
