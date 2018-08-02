package customlobby.essential;

import customlobby.CustomLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildMode implements CommandExecutor {

    public static List<String> buildmodeplayers = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("CustomLobby.BuildMode")) {
            if(!buildmodeplayers.contains(p.getName()))
            {
                buildmodeplayers.add(p.getName());
                p.sendMessage(CustomLobby.prefix + "§aDu bist nun im Baumodus!");
            } else {
                buildmodeplayers.remove(p.getName());
                p.sendMessage(CustomLobby.prefix + "§aDu bist nun §cnicht §amehr im Baumodus!");
            }

        } else {
            p.sendMessage(CustomLobby.noPermission);
        }
        return true;
    }
}
