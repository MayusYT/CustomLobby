package customlobby.crates;

import customlobby.CustomLobby;
import customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CratesNewCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("CustomLobby.addCrate")) {

            if(args.length == 2) {

                Player p = Bukkit.getPlayer(args[0]);
                if(p != null) {
                    try {
                        int count = Integer.parseInt(args[1]);
                        if(count >= 1 && count <= 64) {
                            CustomLobby.getInstance().saveConfig();
                            CustomLobby.getInstance().reloadConfig();

                            CustomLobby.getInstance().getConfig().set("player." + p.getName() + ".crates", CustomLobby.getInstance().getConfig().getInt("player." + p.getName() + ".crates") + count);

                            CustomLobby.getInstance().saveConfig();
                            CustomLobby.getInstance().reloadConfig();

                            sender.sendMessage(API.getPrefix() + "§6" + p.getName() + " §4" + count + " §acrates gegebem");
                        } else {
                            sender.sendMessage(CustomLobby.getPrefix() + " §6" + args[1] + "§c ist keine Valide possitive nummer unter 64");
                        }
                    } catch (NumberFormatException e) {
                        sender.sendMessage(CustomLobby.getPrefix() + " §6" + args[1] + "§c ist keine Valide possitive nummer unter 64");
                    }
                } else {
                    sender.sendMessage(CustomLobby.getPrefix() + " §cSpieler nicht gefunden");
                }

            } else {
                sendUsage(sender);
            }
        } else {
            sender.sendMessage(API.getNoPermString());
        }

        return true;
    }

    private void sendUsage(CommandSender p) {
        p.sendMessage(API.getPrefix() + "§6Usage§8: §r /addcrate §7<§rPlayer§7> §7<§rCount§7>");
    }
}
