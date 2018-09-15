package customlobby.economy;

import customlobby.CustomLobby;
import customlobby.SQL.SQLConfig;
import customlobby.utils.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static customlobby.CustomLobby.getInstance;

public class GetMoneyCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        if (conf.canConnect()) {

            Player p = (Player) sender;
            int money = GetMoney.getMoneyByPlayer(p);
            if (money != 0) {
                p.sendMessage(CustomLobby.prefix + "Dein Kontostand: §a" + money + "$");
            } else {
                p.sendMessage(CustomLobby.prefix + "Glückwunsch! Du hast dein Startgeld bekommen: §a" + 1000 + "$");
                //CustomLobby.getInstance().getConfig().set("players." + p.getName() + ".money", 1000);
                GetMoney.setMoneyByPlayer(p, 1000);
            }

        } else {
            sender.sendMessage(API.getPrefix() + "§cDiese Funktion ist deaktiviert, da die Database nicht aktiviert ist! Bitte melde dich bei einem Server Administrator!");
        }
        return true;
    }
}
