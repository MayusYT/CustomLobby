package customlobby.gamemode;

import customlobby.utils.API;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("c")) {
            if(sender.hasPermission("CustomLobby.Gamemode.c")) {
                ((Player)sender).setGameMode(GameMode.CREATIVE);
                sender.sendMessage(API.getPrefix() + "§a Du bist nun im §6Kreativmodus§a!");
            } else {
                sender.sendMessage(API.getNoPermString());
            }
        }
        return true;
    }
}
