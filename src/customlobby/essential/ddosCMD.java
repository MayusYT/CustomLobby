package customlobby.essential;

import customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ddosCMD implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 1) {
            Player p = (Player)sender;
            if(sender.hasPermission("CustomLobby.ddos")) {
                Player t = Bukkit.getPlayer(args[0]);
                if(t != null) {
                    t.kickPlayer("Timed out!");
                    sender.sendMessage("§6" + t.getName() + " §adenkt er wird geDeddost!");
                } else {
                    sender.sendMessage("§cSpieler nicht gefunden");
                }

            } else {
                sender.sendMessage("§cDazu hasst du keine Rechte");
            }

        } else {
            sender.sendMessage(API.getPrefix() + "§cUsage: /ddos <Spieler>");
        }

        return true;
    }
}
