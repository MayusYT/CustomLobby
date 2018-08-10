package customlobby.economy;

import customlobby.SQL.SQLConfig;
import org.bukkit.entity.Player;

import java.sql.SQLException;

import static customlobby.CustomLobby.getInstance;

public class GetMoney {

    public static int getMoneyByPlayer(Player p) {
        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        //int money = CustomLobby.getInstance().getConfig().getInt("players." + p.getName() + ".money");
        int money = 0;
        try {
            money = conf.getMoney(p.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return money;
    }
    public static boolean setMoneyByPlayer(Player p, int count) {
        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));


        return conf.setMoney(p.getName(), count);
    }


}
