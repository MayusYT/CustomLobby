package customlobby.chat;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatListener implements Listener {


    private String group = "";
    @EventHandler
    public void onChat(PlayerChatEvent e) {

        e.setCancelled(true);
        Player p = e.getPlayer();
        String msg = e.getMessage();
        String pref = "";

        if(PermissionsEx.getUser(p).inGroup("Premium")) {
            pref = "§6Premium";
        } if(PermissionsEx.getUser(p).inGroup("YouTuber")) {
            pref = "§5YouTuber";
        } if(PermissionsEx.getUser(p).inGroup("Builder")) {
            pref = "§l§9Builder ";
        } if(PermissionsEx.getUser(p).inGroup("Developer")) {
            pref = "§l§3Dev ";
        } if(PermissionsEx.getUser(p).inGroup("Moderator")) {
            pref = "§l§aMod ";
        } if(PermissionsEx.getUser(p).inGroup("Inhaber")) {
            pref = "§l§cInhaber ";
        } if(PermissionsEx.getUser(p).inGroup("Premium")) {
            pref = "§l§6Premium  ";
        } if(PermissionsEx.getUser(p).inGroup("Spieler")) {
            pref = "§l§7Spieler  ";
        } if(PermissionsEx.getUser(p).inGroup("Admin")) {
            pref = "§l§2Admin  ";
        } if(PermissionsEx.getUser(p).inGroup("Supporter")) {
            pref = "§l§3Support  ";
        }

        for(Player cp : Bukkit.getOnlinePlayers()) {
            cp.sendMessage(pref + "§7[§f" + p.getName() + "§7]§5: §r" + e.getMessage());
        }

    }


    public static String getRankString(Player p) {
        String pref = "";
        if(PermissionsEx.getUser(p).inGroup("Premium")) {
            pref = "§6Premium";
        } if(PermissionsEx.getUser(p).inGroup("YouTuber")) {
            pref = "§5YouTuber";
        } if(PermissionsEx.getUser(p).inGroup("Builder")) {
            pref = "§l§9Builder ";
        } if(PermissionsEx.getUser(p).inGroup("Developer")) {
            pref = "§l§3Dev ";
        } if(PermissionsEx.getUser(p).inGroup("Moderator")) {
            pref = "§l§aMod ";
        } if(PermissionsEx.getUser(p).inGroup("Inhaber")) {
            pref = "§l§cInhaber ";
        } if(PermissionsEx.getUser(p).inGroup("Premium")) {
            pref = "§l§6Premium  ";
        } if(PermissionsEx.getUser(p).inGroup("Spieler")) {
            pref = "§l§7Spieler  ";
        } if(PermissionsEx.getUser(p).inGroup("Admin")) {
            pref = "§l§2Admin  ";
        } if(PermissionsEx.getUser(p).inGroup("Supporter")) {
            pref = "§l§3Support  ";
        }

        return pref;
    }

}
