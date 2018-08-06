package customlobby.economy;

import customlobby.CustomLobby;
import customlobby.SQL.SQLConfig;
import customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

import static customlobby.CustomLobby.getInstance;

public class MoneyTransfer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        if(conf.canConnect()) {

        Player p = (Player) sender;
        if (args.length == 2) {
            if (Bukkit.getPlayer(args[0]) != null) {
                if (API.isInt(args[1]) && Integer.parseInt(args[1]) > 0) {
                    if (GetMoney.getMoneyByPlayer(p) > Integer.parseInt(args[1])) {
                        int moneyamountofexecutor = GetMoney.getMoneyByPlayer(p);
                        int moneyamounttobetransferred = Integer.parseInt(args[1]);
                        int moneyamountofreceiver = GetMoney.getMoneyByPlayer(Bukkit.getPlayer(args[0]));

                        SetMoney.setMoneyByPlayer(p, moneyamountofexecutor - moneyamounttobetransferred);
                        SetMoney.setMoneyByPlayer(Bukkit.getPlayer(args[0]), moneyamountofreceiver + moneyamounttobetransferred);

                        p.sendMessage(CustomLobby.prefix + "§c-" + args[1]);
                        p.sendMessage(CustomLobby.prefix + "Dem Spieler " + args[0] + " wurden §a" + args[1] +
                                "$ auf das Konto gebucht!");
                        Bukkit.getPlayer(args[0]).sendMessage(CustomLobby.prefix + "§a+" + args[1] + "$");
                        Bukkit.getPlayer(args[0]).sendMessage(CustomLobby.prefix + "Der Spieler " + p.getName() +
                                " hat dir §a" + args[1] + "$§r gegeben!");
                    } else {
                        p.sendMessage(CustomLobby.prefix + "§cDu hast nicht genügend Geld dafür!");
                    }
                } else {
                    p.sendMessage(CustomLobby.prefix + "§cDu musst als zweites Argument eine Zahl über 0 angeben!");
                }
            } else {
                p.sendMessage(CustomLobby.prefix + "§cDer Spieler " + args[0] + " existiert nicht!");
            }
        } else {
            p.sendMessage(CustomLobby.prefix + "§cBenutzung: /transfer <Spieler> <Geld>");
        }

        } else {
            sender.sendMessage(API.getPrefix() + "§cDiese Funktion ist deaktiviert, da die Database nicht aktiviert ist! Bitte melde dich bei einem Server Administrator!");
        }
        return false;
    }
}
