package customlobby;

/*
import customlobby.banmanager.Banmanager;
import customlobby.banmanager.BanmanagerCfg;
import customlobby.banmanager.PardonHandler;
import customlobby.banmanager.WarnHandler;
*/
import customlobby.boots.BootListener;
import customlobby.chat.ChatListener;
import customlobby.crates.CratesCommand;
import customlobby.crates.CratesEnderchestListener;
import customlobby.crates.CratesGuiEventHandler;
import customlobby.crates.CratesNewCommand;
import customlobby.economy.GetMoneyCMD;
import customlobby.economy.MoneyTransfer;
import customlobby.economy.SetMoneyCMD;
import customlobby.essential.*;
import customlobby.friends.friendsCMD;
import customlobby.gamemode.Gamemode;
import customlobby.mail.MailCMD;
import customlobby.mail.MailListener;
import customlobby.mail.SendMailCMD;
import customlobby.navigator.NavigatorCommandListener;
import customlobby.navigator.SetNavigatorWarpsCMD;
import customlobby.nick.Nick;
import customlobby.scoreboard.ScoreboardListener;
import customlobby.tablist.Tablist;
import customlobby.troll.Troll;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomLobby extends JavaPlugin {
    public final static String prefix = "§7[§3Lobby§7]§5 > §r";
    public final static String noPermission = prefix + "§cDu hast nicht die nötige Berechtigung, um diesen Befehl auszuführen";
    public static CustomLobby instance;


    @Override
    public void onEnable() {
        instance = this;
        initConfig();
        init();
        System.out.println("[Lobby] #############");
        System.out.println("[Lobby] Enabled!");
        System.out.println("[Lobby] CustomLobby v" + getDescription().getVersion()+ " by MayusYT and Tallerik");
        System.out.println("[Lobby] Credits: https://www.github.com/MayusYT!");
        System.out.println("[Lobby] Credits: https://www.github.com/Tallerik!");
        System.out.println("[Lobby] #############");
    }


    private void initConfig() {
        saveConfig();
        reloadConfig();
        getConfig().addDefault("player.DEFAULT.exist", false);
        getConfig().addDefault("spawn.exist", false);

        getConfig().addDefault("SQL.host", "");
        getConfig().addDefault("SQL.user", "");
        getConfig().addDefault("SQL.pw", "");
        getConfig().addDefault("SQL.db", "");

        getConfig().addDefault("spawn.WORLD", "world");
        getConfig().addDefault("spawn.x", 0);
        getConfig().addDefault("spawn.y", 0);
        getConfig().addDefault("spawn.z", 0);
        getConfig().addDefault("spawn.YAW", 0.0);
        getConfig().addDefault("spawn.PITCH", 0.0);

        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();

    }

    private void init() {


        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        //############################
        //Listener & Events
        //############################
        Bukkit.getPluginManager().registerEvents(new LobbyRestrictions(), this);
        Bukkit.getPluginManager().registerEvents(new CratesGuiEventHandler(), this);
        Bukkit.getPluginManager().registerEvents(new BootListener(), this);
        Bukkit.getPluginManager().registerEvents(new Troll(), this);
        Bukkit.getPluginManager().registerEvents(new CratesEnderchestListener(), this);
        Bukkit.getPluginManager().registerEvents(new MailListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new ScoreboardListener(), this);

        //############################
        //Commands
        //Nicht vergessen, die Commands in der plugin.yml einzutragen!
        //############################

        //Package crates
        getCommand("crate").setExecutor(new CratesCommand());
        getCommand("addcrate").setExecutor(new CratesNewCommand());
        //Package: Nick
        getCommand("nick").setExecutor(new Nick());
        getCommand("nickplayer").setExecutor(new Nick());
        //Package: banmanager
        /*
        getCommand("ban").setExecutor(new Banmanager());
        getCommand("tempban").setExecutor(new Banmanager());
        getCommand("warn").setExecutor(new WarnHandler());
        getCommand("pardon").setExecutor(new PardonHandler());
        */
        //Package: gamemode
        getCommand("c").setExecutor(new Gamemode());
        getCommand("s").setExecutor(new Gamemode());
        getCommand("sp").setExecutor(new Gamemode());
        //Package: navigator
        getCommand("gui").setExecutor(new NavigatorCommandListener());
        getCommand("setwarp").setExecutor(new SetNavigatorWarpsCMD());
        //Package: essential
        getCommand("build").setExecutor(new BuildMode());
        getCommand("spawn").setExecutor(new SpawnCMD());
        getCommand("setspawn").setExecutor(new SpawnCreateCMD());
        getCommand("tpall").setExecutor(new TpallCMD());
        getCommand("clearall").setExecutor(new KillECMD());
        getCommand("ddos").setExecutor(new ddosCMD());
        //Package: economy
        getCommand("money").setExecutor(new GetMoneyCMD());
        getCommand("setmoney").setExecutor(new SetMoneyCMD());
        getCommand("transfer").setExecutor(new MoneyTransfer());
        //Package: profile
        //getCommand("friend").setExecutor(new friendsCMD());
        //Package: mail
        getCommand("sendmail").setExecutor(new SendMailCMD());
        getCommand("mail").setExecutor(new MailCMD());


        getCommand("connServer").setExecutor(new connServerCMD());

        Tablist.repeatingTab();
    }


    @Override
    public void onDisable() {
        System.out.println("[Lobby] Disabled!");

    }

    public static CustomLobby getInstance() {
        return instance;
    }


    public static String getPrefix() {
        return prefix;
    }




}
