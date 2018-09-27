package customlobby.utils;

import customlobby.CustomLobby;
import org.bukkit.Material;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.List;

public class API {

    public static String getPrefix() {
        return CustomLobby.getPrefix();
    }

    public static String getPluginVersion() {
        return CustomLobby.getInstance().getDescription().getVersion();
    }
    public static String getPluginDescription() {
        return CustomLobby.getInstance().getDescription().getDescription();
    }
    public static List<String> getPluginAuthor() {
        return CustomLobby.getInstance().getDescription().getAuthors();
    }
    public static String getPluginName() {
        return CustomLobby.getInstance().getDescription().getFullName();
    }
    public static PluginDescriptionFile getPluginDescriptionObject() {
        return CustomLobby.getInstance().getDescription();
    }
    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static String getNoPermString() { return CustomLobby.noPermission;}

    public static String toString(boolean b, String trueReturn, String falseReturn) {
        if(b) {
            return trueReturn;
        } else {
            return falseReturn;
        }
    }

    public static Material toMaterial(String material) {
            return Material.getMaterial(material);
    }
}
