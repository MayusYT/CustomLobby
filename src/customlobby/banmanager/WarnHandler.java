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
                    try {
                        BanmanagerCfg.addToWarns(Bukkit.getPlayer(args[0]));

                        if(BanmanagerCfg.getWarns(Bukkit.getPlayer(args[0])) == 3) {
                            BanmanagerCfg.addToBans(Bukkit.getPlayer(args[0]), "Du wurdest nach 3 Warns automatisch für 15 Tage gebannt!", System.currentTimeMillis(), 15);
                            Bukkit.getPlayer(args[0]).kickPlayer("Du wurdest nach 3 Warns automatisch für 15 Tage gebannt!");
                            // TODO: setWarns() Methode einfügen
                            // BanmanagerCfg.setWarns(p, Bukkit.getPlayer(args[0]), 0);
                        } else {
                            Bukkit.getPlayer(args[0]).sendMessage(CustomLobby.prefix + "Du hast einen §cWarn §rerhalten. Bei 3 Warns" +
                                    " wirst du von Netzwerk verbannt! Anzahl Warns: " + BanmanagerCfg.getWarns(Bukkit.getPlayer(args[0])));
                        }

                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    p.sendMessage(CustomLobby.prefix + "§cDieser Spieler existiert nicht!");
                }
            } else {
                p.sendMessage(CustomLobby.prefix + "§cBenutzung: /warn <Spieler>");
            }
        } else {
            p.sendMessage(CustomLobby.noPermission);
        }
        return true;
    }
}
