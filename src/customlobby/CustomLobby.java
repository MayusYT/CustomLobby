package customlobby;

import customlobby.banmanager.Banmanager;
import customlobby.banmanager.BanmanagerCfg;
import customlobby.banmanager.WarnHandler;
import customlobby.essential.LobbyRestrictions;
import customlobby.nick.Nick;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomLobby extends JavaPlugin {
    public static String prefix = "§7[§3Lobby§7]§5 > §r";
    public static String noPermission = prefix + "§cDu hast nicht die nötige Berechtigung, um diesen Befehl auszuführen";
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
        try {
            BanmanagerCfg.save();
        } catch(Exception e) {
            e.printStackTrace();
        }

        //############################
        //Listener & Events
        //############################
        Bukkit.getPluginManager().registerEvents(new LobbyRestrictions(), this);



        //############################
        //Commands
        //Nicht vergessen, die Commands in der plugin.yml einzutragen!
        //############################

        //Package: Nick
        getCommand("nick").setExecutor(new Nick());
        //Package: banmanager
        getCommand("ban").setExecutor(new Banmanager());
        getCommand("warn").setExecutor(new WarnHandler());
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
