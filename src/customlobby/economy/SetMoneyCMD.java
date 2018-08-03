package customlobby.economy;

import customlobby.CustomLobby;
import customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMoneyCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        //TODO Commands registrieren, elses, usw.
        Player p = (Player) sender;

        if(args.length == 2) {
            if(p.hasPermission("CustomLobby.setMoney")) {
                if(Bukkit.getPlayer(args[0]) != null) {
                    if(API.isInt(args[1])) {
                        SetMoney.setMoneyByPlayer(Bukkit.getPlayer(args[0]), Integer.parseInt(args[1]));
                        p.sendMessage(CustomLobby.prefix + "§aDer Kontostand des Spielers " + args[0] + " wurde auf " + args[1]
                        + "gesetzt.");
                    } else {
                        p.sendMessage(CustomLobby.prefix + "§cBitte gib eine Zahl über 0 ein!");
                    }

                } else {
                    p.sendMessage(CustomLobby.prefix + args[0] + " ist kein Spieler");
                }
            } else {
                p.sendMessage(CustomLobby.noPermission);
            }



        } else {
            p.sendMessage(CustomLobby.prefix + "§cVerwendung: /setmoney <Spieler> <Geld>");
        }


        return true;
    }
}
