package customlobby.banmanager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class BanmanagerCfg {

    public static File ConfigFile = new File("plugins/Youtube", "bans.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);


    public static void save() throws IOException {
        Config.save(ConfigFile);

    }

    public static void addToBans(Player p, String reason) throws IOException {
        Config.set("bans." + p.getName() + ".reason", reason);
        save();

    }

    public static void addToBans(Player p, String reason, Long currentmillis, Integer untilbanned) throws IOException{
        Config.set("tempbans." + p.getName() + ".reason", reason);
        Config.set("tempbans." + p.getName() + ".oldmillis", currentmillis);
        Config.set("tempbans." + p.getName() + ".banneduntil", untilbanned * 86400 * 1000);
        save();
    }

    public static void setOnline(Player p, Boolean online) throws IOException  {
        Config.set(p.getName() + ".Online", online);
        save();
    }

    public static Boolean getOnline(Player p) {
        return Config.getBoolean(p.getName() + ".Online");
    }

    public static Boolean onBanlist(Player p) {

        for(String key : Config.getConfigurationSection("bans").getKeys(false)) {
            if(key.equalsIgnoreCase(p.getName())) {
                return true;

            }

        } return false;

    }

    public static Boolean onTempBanList(Player p) {
        for(String key : Config.getConfigurationSection("tempbans").getKeys(true)) {
            if(key.equalsIgnoreCase(p.getName())) {
                return true;

            }

        } return false;
    }

    public static Boolean stillBanned(Player p) {

        String untilString = Config.getString(p.getName() + ".banneduntil");
        if(Float.parseFloat(untilString) >  System.currentTimeMillis()) {
            return false;
        } else {
            return true;
        }

    }


}
