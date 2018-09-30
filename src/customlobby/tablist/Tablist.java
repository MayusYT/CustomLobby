package customlobby.tablist;

import customlobby.CustomLobby;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Tablist {
    public static int stage = 0;
    public static String toJsonString(String input) {
        return "{'text':'" + input + "'}";
    }
    public static void updateTab(String header, String footer, Player p) {
        PacketPlayOutPlayerListHeaderFooter tab = new PacketPlayOutPlayerListHeaderFooter(IChatBaseComponent.ChatSerializer.a(toJsonString(header)));
        try {
            Field a = tab.getClass().getDeclaredField("a");
            a.setAccessible(true);
            a.set(tab, IChatBaseComponent.ChatSerializer.a(toJsonString(header)));
            a.setAccessible(false);

            Field b = tab.getClass().getDeclaredField("b");
            b.setAccessible(true);
            b.set(tab, IChatBaseComponent.ChatSerializer.a(toJsonString(footer)));
            b.setAccessible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(tab);
    }

    private static volatile int tier;
    public static int count = 0;
    public static void repeatingTab() {

        String defaultHeader = "Wilkommen, ";

        Bukkit.getScheduler().scheduleSyncRepeatingTask(CustomLobby.getInstance(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                String header = randColors(count) + defaultHeader + randColors(count + 1) + p.getName();
                int stage = tier % header.length();
                //String h = "§a" + header.substring(0, stage) + "§2" + header.charAt(stage + 1) + "§a" + header.substring(stage + 1);
                String h = header;
                updateTab(h, randColors(count + 2) + "Webseite: " + randColors(count + 3) + "http://snapecraft.ddns.net", p);

                count++;

            }

        }, 8, 8);
    }


    public static String randColors(int i) {

        List<String> list = new ArrayList<>();
        list.add("§a");
        list.add("§b");
        list.add("§c");
        list.add("§d");
        list.add("§e");
        list.add("§f");

        list.add("§0");
        list.add("§1");
        list.add("§2");
        list.add("§3");
        list.add("§4");
        list.add("§5");
        list.add("§6");
        list.add("§7");
        list.add("§8");
        list.add("§9");

        try {

            return list.get(i);
        } catch (IndexOutOfBoundsException e) {
            count = 0;
            return "§a";
        }

    }

}
/**
 *ColorCodes:
 *	§a Hellgrün
 *	§b Hellblau
 *	§c Rot
 *	§d Pink
 *	§e Gelb
 *	§f Weiß
 *
 *	§0 Schwarz
 *	§1 Dunkelblau
 *	§2 Dunkelgrün
 *	§3 Dunkelaqua
 *	§4 Dunkelrot
 *	§5 Dunkelviolett
 *	§6 Gold
 *	§7 Grau
 *	§8 Dunkelgrau
 *	§9 Blau
 *
 *
 *Formationen:
 *	§o Kursiv
 *	§n Unterstrichen
 *	§m Durchgestrichen
 *	§l Fett
 *	§k Unlesbare Verschleierrung
 *
 *	§r deformatieren
 *
 *Oft Genutzt:
 *	§a Hellgrün
 *	§c Rot
 *
 */