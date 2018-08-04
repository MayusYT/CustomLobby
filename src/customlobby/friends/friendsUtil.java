package customlobby.friends;

import customlobby.CustomLobby;
import customlobby.utils.API;
import org.bukkit.entity.Player;

import java.util.List;

public class friendsUtil {
    public static void makeFriend(Player friend1, Player friend2) {
        CustomLobby.getInstance().saveConfig();
        CustomLobby.getInstance().reloadConfig();
        List<String> friends = CustomLobby.getInstance().getConfig().getStringList("player." + friend1.getName() + ".friends");

        friends.add(friend2.getName());

        CustomLobby.getInstance().getConfig().set("player." + friend1.getName() + ".friends", friends);
        CustomLobby.getInstance().saveConfig();
        CustomLobby.getInstance().reloadConfig();
        friend1.sendMessage(API.getPrefix() + "§aDu bist nun mit §6" + friend2.getName() + " §abefreundet");

    }
}
