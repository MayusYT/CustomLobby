package customlobby.essential;

import customlobby.CustomLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SpawnCMD implements CommandExecutor {
    public static Location spawnLoc = new Location(Bukkit.getWorld(CustomLobby.getInstance().getConfig().getString("spawn.WORLD")),
            Double.parseDouble(CustomLobby.getInstance().getConfig().getString("spawn.X")),
            Double.parseDouble(CustomLobby.getInstance().getConfig().getString("spawn.Y")),
            Double.parseDouble(CustomLobby.getInstance().getConfig().getString("spawn.Z")),
            Float.parseFloat(CustomLobby.getInstance().getConfig().getString("spawn.YAW")),
            Float.parseFloat(CustomLobby.getInstance().getConfig().getString("spawn.PITCH")));


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if(CustomLobby.getInstance().getConfig().getString("spawn.X") != null) {
            p.teleport(spawnLoc);
            p.sendMessage(CustomLobby.prefix + "§bWillkommen am §aSpawn!");
        } else {
            p.sendMessage(CustomLobby.prefix + "§cEs ist ein Fehler aufgetreten: Der Spawn ist nicht gesetzt! Frage" +
                    " deinen Admin um Hilfe.");
        }

        return true;
    }
}
