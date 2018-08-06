package customlobby.economy;

import customlobby.CustomLobby;
import customlobby.SQL.SQLConfig;
import org.bukkit.entity.Player;

import static customlobby.CustomLobby.getInstance;

public class SetMoney {

    public static void setMoneyByPlayer(Player p, int count) {
        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));


        conf.setMoney(p.getName(), count);
    }

}
