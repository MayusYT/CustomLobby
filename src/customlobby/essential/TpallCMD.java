package customlobby.essential;

import customlobby.CustomLobby;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpallCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("CustomLobby.tpall")) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                if (all != p) {
                    all.teleport(p);
                    all.sendMessage(CustomLobby.prefix + "§aAlle Spieler wurden zu §6" + p.getName() + " teleportiert");
                    p.sendMessage(CustomLobby.prefix + "§aAlle Spieler wurden zu §6dir §a teleportiert!");
                }
            }

            } else {
            p.sendMessage(CustomLobby.noPermission);
        }


        return true;
    }
}
