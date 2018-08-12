package customlobby.banmanager;

import customlobby.CustomLobby;
import customlobby.SQL.PermBan;
import customlobby.SQL.SQLConfig;
import customlobby.SQL.TempBan;
import customlobby.utils.API;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static customlobby.CustomLobby.getInstance;

public class BanmanagerCfg {

    static SQLConfig conf = new SQLConfig();

    public static File ConfigFile = new File("plugins/CustomLobby", "bans.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);




    public static void save() throws IOException {
        Config.save(ConfigFile);

    }
    public static void reload() {
        Config = YamlConfiguration.loadConfiguration(ConfigFile);

    }

    public static void addToBans(Player p, String reason) throws IOException {
        /*Config.set("bans." + p.getName() + ".reason", reason);
        save(); */
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        conf.addBan(p.getName(), reason);

    }

    public static void addToBans(Player p, String reason, Long currentmillis, Integer untilbanned) throws IOException{
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        Long milis = TimeUnit.DAYS.toMillis(untilbanned);
        /*
        Config.set("tempbans." + p.getName() + ".reason", reason);
        Config.set("tempbans." + p.getName() + ".oldmillis", currentmillis);
        Config.set("tempbans." + p.getName() + ".banneduntil", milis.intValue());
        save(); */
        conf.addTempBan(p.getName(), reason, currentmillis.intValue(), milis.intValue());
    }

    public static void addToWarns(Player p) throws IOException {
       /*
        if(Config.getString("warns." + p.getName() + ".warnamount") != null) {
            Integer warnamount = Integer.parseInt(Config.getString("warns." + p.getName() + ".warnamount"));
            warnamount = warnamount + 1;
            Config.set("warns." + p.getName() + ".warnamount", warnamount);
            save();
        } else {
            Config.set("warns." + p.getName() + ".warnamount", 1);
            save();
        } */
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        try {
            conf.addWarn(p.getName());
        } catch(Exception e) {
            p.sendMessage("Tallerik muss nun einen Kuchen backen, weil er wieder gepfuscht hat!");
        }

    }

    public static Integer getWarns(Player p) throws Exception {
        /*
        if(Config.getString("warns." + p.getName() + ".warnamount") != null){
            Integer warnamount = Integer.parseInt(Config.getString("warns." + p.getName() + ".warnamount"));
            return warnamount;
        } else {
            return 0;
        }*/
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        return conf.getWarncount(p.getName());
    }



    //TODO: setWarns()
    /*
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
        }*/

    /*
    public static void setOnline(Player p, Boolean online) throws IOException  {
        Config.set(p.getName() + ".Online", online);
        save();
    } */

    public static void pardonPlayer(String p, Player s) throws IOException{
        /*if(Config.getString("bans." + p) != null){
            Config.getConfigurationSection("bans").set(p, null);

        }
        if(Config.getString("tempbans." + p) != null) {
            Config.getConfigurationSection("tempbans").set(p, null);
        }
        else {
            s.sendMessage(API.getPrefix() + "§cDieser Spieler ist nicht gebannt!");
        }
        save();*/
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        conf.removeBan(p);
    }
    /*
    public static Boolean getOnline(Player p) {
        return Config.getBoolean(p.getName() + ".Online");
    }*/

    public static Boolean onBanlist(String p) {
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        try {
            List<PermBan> bans = conf.getBan(p);
            for (int i = 0; i < bans.size(); i++) {
                PermBan b = bans.get(i);
                if(b.isBanned()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  true;
    }

    public static Boolean onTempBanList(String p) {
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        try {
            List<TempBan> bans = conf.getTempBan("Name");
            for(TempBan ban : bans) {
                if(ban.isIstempBanned() == true) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Boolean stillBanned(Player p) {
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        try {
            List<TempBan> bans = conf.getTempBan("Name");
            for(TempBan ban : bans) {
                if(ban.getBanneduntil() < System.currentTimeMillis()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }




}
