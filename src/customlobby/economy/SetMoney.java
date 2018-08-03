package customlobby.economy;

import customlobby.CustomLobby;
import org.bukkit.entity.Player;

public class SetMoney {

    public static void setMoneyByPlayer(Player p, Integer amount) {
            CustomLobby.getInstance().getConfig().set("players." + p.getName() + ".money", amount);
            CustomLobby.getInstance().saveConfig();
            CustomLobby.getInstance().reloadConfig();



    }

}
