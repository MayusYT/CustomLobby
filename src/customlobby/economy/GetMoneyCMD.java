package customlobby.economy;

import customlobby.CustomLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetMoneyCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if(CustomLobby.getInstance().getConfig().getString("players." + p.getName() + ".money") != null) {
            p.sendMessage(CustomLobby.prefix + "Dein Kontostand: §a" + Integer.parseInt(CustomLobby.getInstance().getConfig().getString("players." +
            p.getName() + ".money")) + "$");
        } else {
            p.sendMessage(CustomLobby.prefix + "Glückwunsch! Du hast dein Startgeld bekommen: §a" + 1000 + "$");
            CustomLobby.getInstance().getConfig().set("players." + p.getName() + ".money", 1000);
        }
        return true;
    }

}
