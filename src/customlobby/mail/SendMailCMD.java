package customlobby.mail;

import customlobby.SQL.SQLConfig;
import customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static customlobby.CustomLobby.getInstance;

public class SendMailCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));


        if (args.length >= 2) {


                String msg = "";
                int l = 0;
                for(String s : args) {
                    if (l != 0) {
                        msg = msg + " " + s;
                    }
                    l++;
                }
                conf.addMail(sender.getName(), args[0], msg);
                sender.sendMessage(API.getPrefix() + "§aNachricht an §6" + args[0] + "§a gesendet.");

        } else {
            sender.sendMessage(API.getPrefix() + "§cUsage: §7/§6sendmail <Player> <Message>");
        }

    return true;
    }
}
