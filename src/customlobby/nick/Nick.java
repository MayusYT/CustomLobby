package customlobby.nick;

import customlobby.CustomLobby;
import customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length > 2 && args.length <= 3) {
            if(sender.hasPermission("CustomLobby.nick")) {
                if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("off")) {
                        ((Player)sender).setDisplayName(sender.getName());
                    } else {
                        printNickHelp((Player) sender);
                    }
                }
                if (args.length == 2) {
                    if(args[0].equalsIgnoreCase("on")) {
                        ((Player)sender).setDisplayName(args[1]);
                    } else {
                        printNickHelp((Player)sender);
                    }
                    if(args[0].equalsIgnoreCase("off")) {
                        if(Bukkit.getPlayer(args[1]) != null) {
                            Bukkit.getPlayer(args[1]).setDisplayName(Bukkit.getPlayer(args[1]).getName());
                        } else {
                            sender.sendMessage(CustomLobby.prefix + "§cDieser Spieler existiert nicht!");
                        }

                    } else {
                        printNickHelp((Player) sender);
                    }
                }
                if (args.length == 3) {
                    if(args[0].equalsIgnoreCase("on")) {
                        if(Bukkit.getPlayer(args[2]) != null) {
                            Bukkit.getPlayer(args[2]).setDisplayName(args[1]);
                        } else {
                            sender.sendMessage(CustomLobby.prefix + "§cDieser Spieler existiert nicht!");
                        }
                    } else {
                        printNickHelp((Player)sender);
                    }
                }
            } else {
                sender.sendMessage(CustomLobby.noPermission);
            }
        } else {
            printNickHelp((Player)sender);
        }


        return true;
    }

    public void printNickHelp(Player p) {
        p.sendMessage(API.getPrefix() + "$6Usage§8: §r /nick §7<§ron/off§7> <§rName§7> <§rPlayer§7>");
    }
}
