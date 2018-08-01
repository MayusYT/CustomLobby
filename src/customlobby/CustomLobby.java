package customlobby;

import org.bukkit.plugin.java.JavaPlugin;

public class CustomLobby extends JavaPlugin {
    public static String prefix = "§7[§3Lobby§7]§5 > §r";
    public static CustomLobby instance;
    @Override
    public void onEnable() {
        instance = this;
        init();

        System.out.println("[Lobby] #############");
        System.out.println("[Lobby] Enabled!");
        System.out.println("[Lobby] CustomLobby v" + getDescription().getVersion()+ "by Tayus");
        System.out.println("[Lobby] #############");
    }


    private void init() {

    }


    @Override
    public void onDisable() {
        System.out.println("[Lobby] Enabled!");
    }

    public static CustomLobby getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }
}
