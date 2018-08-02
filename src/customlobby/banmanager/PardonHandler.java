package customlobby.banmanager;

import com.avaje.ebeaninternal.server.lib.sql.Prefix;
import customlobby.CustomLobby;
import customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class PardonHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;



        if(p.hasPermission("CustomLobby.unban")) {
            if(args.length == 1) {
                if(Bukkit.getOfflinePlayer(args[0]) != null) {
                    if(BanmanagerCfg.onBanlist(Bukkit.getOfflinePlayer(args[0]).getName())) {
                        try {
                            BanmanagerCfg.pardonPlayer(Bukkit.getOfflinePlayer(args[0]).getName(), p);
                            p.sendMessage(CustomLobby.prefix + "§aSpieler erfolgreich gebannt!");
                        } catch(IOException e) {
                            e.printStackTrace();
                        }

                    } if (BanmanagerCfg.onTempBanList(Bukkit.getOfflinePlayer(args[0]).getName())) {
                        try{
                            BanmanagerCfg.pardonPlayer(Bukkit.getOfflinePlayer(args[0]).getName(), p);
                            p.sendMessage(CustomLobby.prefix + "§aSpieler erfolgreich gebannt!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    p.sendMessage(CustomLobby.prefix + "§cDieser Spieler existiert nicht!");
                }
            } else {
                p.sendMessage(CustomLobby.prefix + "§cBenutzung: /pardon <Spieler>");
            }
        } else {
            p.sendMessage(API.getNoPermString());
        }

        return true;
    }
}
