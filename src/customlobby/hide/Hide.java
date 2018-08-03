package customlobby.hide;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Hide {
    public static boolean ishidden = false;
    public static void hideall(Player p) {
        for(Player f : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(f);
            ishidden = true;
        }
    }
    public static void showall(Player p) {
        for(Player f : Bukkit.getOnlinePlayers()) {
            p.showPlayer(f);
            ishidden = false;
        }
    }



}
