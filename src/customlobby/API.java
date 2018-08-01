package customlobby;

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
}
