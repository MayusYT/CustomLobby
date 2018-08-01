package customlobby.banmanager;

import customlobby.CustomLobby;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarnHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(p.hasPermission("CustomLobby.warn")) {
            if(args.length == 1) {
                if(Bukkit.getPlayer(args[0]) != null) {

                } else {
                    p.sendMessage(CustomLobby.prefix + "§cDieser Spieler existiert nicht!");
                }
            } else {
                p.sendMessage(CustomLobby.prefix + "§cBenutzung: /warn <Spieler>");
            }
        } p.sendMessage(CustomLobby.noPermission);
        return true;
    }
}
