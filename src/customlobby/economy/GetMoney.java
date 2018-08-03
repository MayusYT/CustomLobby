package customlobby.economy;

import customlobby.CustomLobby;
import org.bukkit.entity.Player;

public class GetMoney {

    public static Integer getMoneyByPlayer(Player p) {
        int money = CustomLobby.getInstance().getConfig().getInt("players." + p.getName() + ".money");
        return money;
    }


}
