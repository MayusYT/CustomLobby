package customlobby.scoreboard;

import customlobby.CustomLobby;
import customlobby.chat.ChatListener;
import customlobby.economy.GetMoney;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardListener implements Listener {

    int shed;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    /*if(!Bukkit.getScheduler().isCurrentlyRunning(shed)) {

        shed = Bukkit.getScheduler().scheduleSyncRepeatingTask(CustomLobby.getInstance(), new Runnable() {
            @Override
            public void run() {
                reloadScoreboard(e.getPlayer());
            }
        }, 20, 20);

    }
*/

    }

    public void reloadScoreboard(Player p) {

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("Snapecraft", "dummy");

        obj.setDisplayName("§6Snapecraft.ddns.net");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score six = obj.getScore("§2Willkommen, " + p.getName());
        Score five = obj.getScore("§1Rang:");
        Score four = obj.getScore(ChatListener.getRankString(p));
        /*Score three = obj.getScore("§1Money:");
        Score two;
        try {
            two = obj.getScore("§2" + GetMoney.getMoneyByPlayer(p));
        } catch (NullPointerException e) {
            two = obj.getScore("§eERROR");
        }*/
        Score one = obj.getScore("§1Spieler online:");
        Score zero = obj.getScore("§2" + Bukkit.getOnlinePlayers().size() + "§7 / §2" + Bukkit.getMaxPlayers());


        six.setScore(6);
        five.setScore(5);
        four.setScore(4);
        //three.setScore(3);
        //two.setScore(2);
        one.setScore(1);
        zero.setScore(0);

        p.setScoreboard(board);

    }


}
