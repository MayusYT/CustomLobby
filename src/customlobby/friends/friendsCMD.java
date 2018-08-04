package customlobby.friends;

import customlobby.CustomLobby;
import customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static customlobby.CustomLobby.getInstance;

public class friendsCMD implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        getInstance().saveConfig();
        getInstance().reloadConfig();
        if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 2) {
                Player rec = Bukkit.getPlayer(args[1]);
                if(rec != null) {

                    List<String> requests = CustomLobby.getInstance().getConfig().getStringList("player." + sender.getName() + ".requests");
                    sender.sendMessage(String.valueOf(requests));
                    if (requests == null) {
                        requests = new ArrayList<String>();
                        requests.add(rec.getName());
                    } else {
                        requests.add(rec.getName());
                    }
                    CustomLobby.getInstance().getConfig().set("player." + sender.getName() + ".requests" , requests);
                    getInstance().saveConfig();
                    getInstance().reloadConfig();
                    sender.sendMessage(API.getPrefix() + "§aDu hasst eine Freundschaftsanfrage an §6" + rec.getName() + "§a geschickt.");
                    rec.sendMessage(API.getPrefix() + "§aDu hasst eine Freundschaftsanfrage von §6" + sender.getName() + "§a bekommen!");
                    rec.sendMessage(API.getPrefix() + "§aNutze §7/§6friend accept " + sender.getName() + " §aum seine anfrage anzunehmen");
                } else {
                    sender.sendMessage(API.getPrefix()  + "§cDieser Spieler wurde nicht gefunden!");
                }
            } else {
                printHelp(sender);
            }
        }

        if(args[0].equalsIgnoreCase("accept")) {
            if(args.length == 2) {
                getInstance().saveConfig();
                getInstance().reloadConfig();
                Player p = Bukkit.getPlayer(args[1]);
                boolean friended = false;
                List<String> friends = CustomLobby.getInstance().getConfig().getStringList("player." + p.getName() + ".friends");
                if(friends != null) {
                    if(friends.contains(p.getName())) {
                        friended = true;
                    }
                }
                if(!friended) {
                    getInstance().saveConfig();
                    getInstance().reloadConfig();
                    if (p != null) {
                        boolean e = true;
                        List<String> requests = CustomLobby.getInstance().getConfig().getStringList("player." + sender.getName() + ".requests");
                        if (requests != null) {

                            for (int i = 0; i < requests.size(); i++) {
                                if (requests.get(i).equalsIgnoreCase(p.getName())) {
                                    e = false;
                                    break;
                                }
                            }
                            if (!e) {
                                friendsUtil.makeFriend((Player) sender, p);
                                friendsUtil.makeFriend(p, (Player) sender);

                            } else {
                                sender.sendMessage(API.getPrefix() + "§cDu hasst keine Anfrage dieser Person");
                            }
                        } else {
                            sender.sendMessage(API.getPrefix() + "§cDu hasst keine Anfrage dieser Person");
                        }

                    } else {
                        sender.sendMessage(API.getPrefix() + "§cDieser Spieler wurde nicht gefunden!");
                    }
                } else {
                    sender.sendMessage(API.getPrefix() + "§cDu bist bereits mit diesem Spieler befreundet");
                }
            } else {
                printHelp(sender);
            }
        }
        if(!args[0].equalsIgnoreCase("add") && !args[0].equalsIgnoreCase("accept")) {
            sender.sendMessage("Wrong");
            printHelp(sender);
        }

        return true;
    }

    public void printHelp(CommandSender sender) {
        sender.sendMessage(API.getPrefix() + "§cUsage: §7/§6friend §7<§6add§7/§6accept§7> <§6Name§7>");
    }
}
