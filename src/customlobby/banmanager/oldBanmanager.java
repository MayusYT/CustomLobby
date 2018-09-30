
/*
    Unused File with outdated Code
 */

package customlobby.banmanager;

public class oldBanmanager {
/*
    public static File ConfigFile = new File("plugins/CustomLobby", "bans.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);




    public static void save() throws IOException {
        Config.save(ConfigFile);

    }
    public static void reload() {
        Config = YamlConfiguration.loadConfiguration(ConfigFile);

    }

    public static void addToBans(Player p, String reason) throws IOException {
        Config.set("bans." + p.getName() + ".reason", reason);
        save();

    }

    public static void addToBans(Player p, String reason, Long currentmillis, Integer untilbanned) throws IOException{
        Long milis = TimeUnit.DAYS.toMillis(untilbanned);

        Config.set("tempbans." + p.getName() + ".reason", reason);
        Config.set("tempbans." + p.getName() + ".oldmillis", currentmillis);
        Config.set("tempbans." + p.getName() + ".banneduntil", milis.intValue());
        save();

    }

    public static void addToWarns(Player p) throws IOException {

        if(Config.getString("warns." + p.getName() + ".warnamount") != null) {
            Integer warnamount = Integer.parseInt(Config.getString("warns." + p.getName() + ".warnamount"));
            warnamount = warnamount + 1;
            Config.set("warns." + p.getName() + ".warnamount", warnamount);
            save();
        } else {
            Config.set("warns." + p.getName() + ".warnamount", 1);
            save();
        }

    }

    public static Integer getWarns(Player p) throws Exception {

        if(Config.getString("warns." + p.getName() + ".warnamount") != null){
            Integer warnamount = Integer.parseInt(Config.getString("warns." + p.getName() + ".warnamount"));
            return warnamount;
        } else {
            return 0;
        }
    }



    public static void setWarns(Player s, Player p, Integer amount) throws IOException {
        if (Config.getString("warns." + p.getName() + ".warnamount") != null) {
            if (amount == 0) {
                Config.getConfigurationSection("warns").set(p.getName(), null);
            }
            if (amount > 0) {
                Config.set("warns." + p.getName() + ".warnamount", amount);
            } else {
                s.sendMessage(API.getPrefix() + "§cBitte gib eine gültge Zahl ein.");
            }

            save();
        } else {
            s.sendMessage(API.getPrefix() + "§cDieser Spieler wurde noch nie verwarnt, du kannst also keine bestimmte Anzahl" +
                    "von Warns für ihn festlegen!");
        }
    }

    public static void setOnline(Player p, Boolean online) throws IOException  {
        Config.set(p.getName() + ".Online", online);
        save();
    }

    public static void pardonPlayer(String p, Player s) throws IOException{
        if(Config.getString("bans." + p) != null){
            Config.getConfigurationSection("bans").set(p, null);

        }
        if(Config.getString("tempbans." + p) != null) {
            Config.getConfigurationSection("tempbans").set(p, null);
        }
        else {
            s.sendMessage(API.getPrefix() + "§cDieser Spieler ist nicht gebannt!");
        }
        save();
    }
    public static void pardonTempPlayer(String p, Player s) throws IOException {
        if (Config.getString("bans." + p) != null) {
            Config.getConfigurationSection("bans").set(p, null);

        }
        if (Config.getString("tempbans." + p) != null) {
            Config.getConfigurationSection("tempbans").set(p, null);
        } else {
            s.sendMessage(API.getPrefix() + "§cDieser Spieler ist nicht gebannt!");
        }
        save();
    }

    public static Boolean getOnline(Player p) {
        return Config.getBoolean(p.getName() + ".Online");
    }




}
*/
}
