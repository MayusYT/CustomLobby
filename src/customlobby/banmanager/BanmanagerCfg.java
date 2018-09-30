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



    public static void addToBans(Player p, String reason) throws IOException {
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        conf.addBan(p.getName(), reason);

    }

    public static void addToBans(Player p, String reason, Long currentmillis, Integer untilbanned) throws IOException{
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        Long milis = TimeUnit.DAYS.toMillis(untilbanned);
        conf.addTempBan(p.getName(), reason, currentmillis.intValue(), milis.intValue());
    }

    public static void addToWarns(Player p) throws IOException {
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        try {
            conf.addWarn(p.getName());
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static Integer getWarns(Player p) throws Exception {
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        return conf.getWarncount(p.getName());
    }

    public static void pardonPlayer(String p, Player s) throws IOException{

        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        conf.removeBan(p);
    }
    public static void pardonTempPlayer(String p, Player s) throws IOException{
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        conf.removeTempBan(p);
    }

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
