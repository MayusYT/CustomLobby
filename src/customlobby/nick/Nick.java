package customlobby.nick;

import customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        //TODO: Testen
            if (cmd.getName().equalsIgnoreCase("nick")) {
                if(sender.hasPermission("CustomLobby.nick")) {
                    Player p = (Player) sender;
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("off")) {
                            p.setDisplayName(p.getName());
                        } else {
                            p.setDisplayName(args[0]);
                        }
                    } else {
                        printNickHelp((Player) sender, "self");
                    }

                }
            }


        if(cmd.getName().equalsIgnoreCase("nickplayer")) {
            if (sender.hasPermission("CustomLobby.nickother")) {
                if (args.length == 2) {
                    Player p = Bukkit.getPlayer(args[0]);
                    if (p != null) {
                        if (args[1].equalsIgnoreCase("off")) {
                            p.setDisplayName(p.getName());
                        } else {
                            p.setDisplayName(args[1]);
                        }
                    } else {
                        p.sendMessage(API.getPrefix() + " §cSpieler nicht gefunden");
                    }
                } else {
                    printNickHelp((Player) sender, "other");
                }

            }
        }
        return true;
    }

    public void printNickHelp(Player p, String player) {
        if(player == "self") {
            p.sendMessage(API.getPrefix() + "§6Usage§8: §r /nick §7<§rName/off§7>");
        }
        if(player == "other") {
            p.sendMessage(API.getPrefix() + "§6Usage§8: §r /nick <§rPlayer§7> §7<§rName/off§7>");
        }

    }
}
