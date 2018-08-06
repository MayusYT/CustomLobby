package customlobby.friends;

import customlobby.CustomLobby;
import customlobby.SQL.SQLConfig;
import customlobby.utils.API;
import org.bukkit.entity.Player;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.List;

import static customlobby.CustomLobby.getInstance;

public class friendsUtil {
    public static void makeFriend(Player friend1, Player friend2) {
        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        if(!conf.canConnect()) {
            friend1.sendMessage("Fehler!");
        }

        conf.addFriend(friend1.getName(), friend2.getName());
        conf.removeFriendReq(friend2.getName(), friend1.getName());


        friend1.sendMessage(API.getPrefix() + "§aDu bist nun mit §6" + friend2.getName() + " §abefreundet");

    }
}
