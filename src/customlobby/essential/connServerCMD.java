package customlobby.essential;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import customlobby.CustomLobby;
import customlobby.utils.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class connServerCMD implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 1) {
            Player p = (Player)sender;
            if(sender.hasPermission("CustomLobby.connServerCMD")) {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(args[0]);

                p.sendPluginMessage(CustomLobby.getInstance(), "BungeeCord", out.toByteArray());
            }

        } else {
            sender.sendMessage(API.getPrefix() + "§cUsage: /connServer <Server>");
        }

        return true;
    }
}
