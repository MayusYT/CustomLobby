package customlobby.crates;

import customlobby.utils.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CratesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            p.openInventory(CratesGui.getInventory(CratesGui.MAIN_GUI, p));
        } else {
            sender.sendMessage(API.getPrefix() + "§cDu musst ein Spieler sein");
        }
        return true;
    }
}
