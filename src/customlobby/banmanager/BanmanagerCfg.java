package customlobby.banmanager;

import customlobby.utils.API;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class BanmanagerCfg {

    public static File ConfigFile = new File("plugins/CustomLobby", "bans.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);


    public static void save() throws IOException {
        Config.save(ConfigFile);

    }
    public static void reload() {
        Config = YamlConfiguration.loadConfiguration(ConfigFile);

    }

    public static void addToBans(Player p, String reason) throws IOException {
        Config.set("bans." + p.getName() + ".reason", reason);
        save();

    }

    public static void addToBans(Player p, String reason, Long currentmillis, Integer untilbanned) throws IOException{
        Config.set("tempbans." + p.getName() + ".reason", reason);
        Config.set("tempbans." + p.getName() + ".oldmillis", currentmillis);
        Config.set("tempbans." + p.getName() + ".banneduntil", untilbanned * 86400000);
        save();
    }

    public static void addToWarns(Player p) throws IOException {
        if(Config.getString("warns." + p.getName() + ".warnamount") != null) {
            Integer warnamount = Integer.parseInt(Config.getString("warns." + p.getName() + ".warnamount"));
            warnamount = warnamount + 1;
            Config.set("warns." + p.getName() + ".warnamount", warnamount);
            save();
        } else {
            Config.set("warns." + p.getName() + ".warnamount", 1);
            save();
        }
    }

    public static Integer getWarns(Player p) {

        if(Config.getString("warns." + p.getName() + ".warnamount") != null){
            Integer warnamount = Integer.parseInt(Config.getString("warns." + p.getName() + ".warnamount"));
            return warnamount;
        } else {
            return 0;
        }


    }
    public static void setWarns(Player s, Player p, Integer amount) throws IOException{
        if(Config.getString("warns." + p.getName() + ".warnamount") != null){
            if(amount == 0) {
                Config.getConfigurationSection("warns").set(p.getName(), null);
            } if(amount > 0) {
                Config.set("warns." + p.getName() + ".warnamount", amount);
            } else {
                s.sendMessage(API.getPrefix() + "§cBitte gib eine gültge Zahl ein.");
            }

            save();
        } else {
            s.sendMessage(API.getPrefix() + "§cDieser Spieler wurde noch nie verwarnt, du kannst also keine bestimmte Anzahl" +
                    "von Warns für ihn festlegen!");
        }
    }
    public static void setOnline(Player p, Boolean online) throws IOException  {
        Config.set(p.getName() + ".Online", online);
        save();
    }

    public static void pardonPlayer(String p, Player s) throws IOException{
        if(Config.getString("bans." + p) != null){
            Config.getConfigurationSection("bans").set(p, null);

        }
        if(Config.getString("tempbans." + p) != null) {
            Config.getConfigurationSection("tempbans").set(p, null);
        }
        else {
            s.sendMessage(API.getPrefix() + "§cDieser Spieler ist nicht gebannt!");
        }
        save();
    }

    public static Boolean getOnline(Player p) {
        return Config.getBoolean(p.getName() + ".Online");
    }

    public static Boolean onBanlist(Player p) {
        if(ConfigFile.length() != 0) {
            if(Config.getConfigurationSection("bans") != null) {
                for(String key : Config.getConfigurationSection("bans").getKeys(false)) {
                    if (key.equalsIgnoreCase(p.getName())) {
                        return true;

                    }

                }
            }return false;

        }
        return false;

    }
    public static Boolean onBanlist(String p) {
        if(ConfigFile.length() != 0) {
            if(Config.getConfigurationSection("bans") != null) {
                for(String key : Config.getConfigurationSection("bans").getKeys(false)) {
                    if (key.equalsIgnoreCase(p)) {
                        return true;

                    }

                }
            }return false;

        }
        return false;

    }

    public static Boolean onTempBanList(String p) {
        if(ConfigFile.length() != 0) {
            if(Config.getConfigurationSection("tempbans") != null) {
                for (String key : Config.getConfigurationSection("tempbans").getKeys(true)) {
                    if (key.equalsIgnoreCase(p)) {
                        return true;

                    }

                }
            } return false;


        } return false;
    }

    public static Boolean stillBanned(Player p) {
        reload();
        System.out.println(Config.getInt(p.getName() + ".oldmillis"));
        if(Config.getInt(p.getName() + ".oldmillis") != 0) {//TODO: Show Here
            String untilString = Config.getString(p.getName() + ".banneduntil");
            return !(Float.parseFloat(untilString) > System.currentTimeMillis());
        } else {
            System.out.println(p.getName() + " ist nicht gebannt");
            return false;
        }


    }




}
