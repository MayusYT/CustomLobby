package customlobby.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLConfig implements SQLAPI {


    // Finish!
    // All functions UNTESTET!!



    private String host = "", user = "", pw = "", dbName = "";

    @Override
    public void initialize(String hostName, String userName, String pwName, String dbNameL) {
        this.host = hostName;
        this.user = userName;
        this.pw = pwName;
        this.dbName = dbNameL;
    }

    @Override
    public boolean canConnect() {
        return MySQLAccess.connectToMysql(host, dbName, user, pw);
    }

    @Override
    public boolean addFriendReq(String name1, String name2) {
        String sql = "INSERT INTO friendrequest (`Name1`, `Name2`) VALUES ('" + name1 + "', '" + name2 + "')";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }

    @Override
    public List<String> getFriendReqs(String playername) throws SQLException {
        String sql = "SELECT * FROM `friendrequest` WHERE `Name1`='" + playername + "'";
        List<String> list = new ArrayList<String>();
        ResultSet resultSet = MySQLAccess.getSQLContents(host, dbName, user, pw, sql);

        if(resultSet == null) {
            return new ArrayList<String>();
        } else {
            while (resultSet.next()) {
                list.add(resultSet.getString("Name2"));
            }
        }

        return list;
    }

    @Override
    public boolean removeFriendReq(String name1, String name2) {
        String sql = "DELETE FROM friendrequest WHERE `Name1`='" + name1 + "' AND `Name2`='" + name2 + "'";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }

    @Override
    public boolean addFriend(String name1, String name2) {
        String sql = "INSERT INTO friends (`Name1`, `Name2`) VALUES ('" + name1 + "', '" + name2 + "')";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }

    @Override
    public List<String> getFriends(String playername) throws SQLException {
        String sql = "SELECT * FROM `friends` WHERE `Name1`='" + playername + "'";
        List<String> list = new ArrayList<String>();
        ResultSet resultSet = MySQLAccess.getSQLContents(host, dbName, user, pw, sql);
        while (resultSet.next()) {
            list.add(resultSet.getString("Name2"));
        }
        return list;
    }

    @Override
    public boolean removeFriend(String name1, String name2) {
        String sql = "DELETE FROM friends WHERE `Name1`='" + name1 + "' AND `Name2`='" + name2 + "'";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }

    @Override
    public boolean addWarn(String player) throws SQLException {
        int warncount = getWarncount(player) + 1;
        removeWarn(player);
        String sql = "INSERT INTO warns (Name, warns) VALUES('" + player + "', " + warncount + ") ON DUPLICATE KEY UPDATE warns=" + warncount;
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }


    @Override
    public boolean removeWarn(String player) {
        String sql = "DELETE FROM warns WHERE `Name`='" + player + "'";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }

    @Override
    public int getWarncount(String player) throws SQLException {
        String sql = "SELECT * FROM `warns` WHERE `Name`='" + player + "'";
        ResultSet resultSet = MySQLAccess.getSQLContents(host, dbName, user, pw, sql);
        int i = 0;
        while (resultSet.next()) {
            i = resultSet.getInt("warns");
        }
        return i;
    }

    @Override
    public boolean addTempBan(String player, String reason, int oldmillis, int banneduntil) {
        String sql = "INSERT INTO tempban (`Name`, `reason`, `oldmillis`, `banneduntil`) VALUES ('" + player + "', '" + reason + "', " + oldmillis + ", " + banneduntil + ")";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }

    @Override
    public List<TempBan> getTempBan(String player) throws SQLException {

        String sql = "SELECT * FROM `tempban` WHERE `Name`='" + player + "'";
        List<TempBan> tempBanList = new ArrayList<TempBan>();
        ResultSet resultSet = MySQLAccess.getSQLContents(host, dbName, user, pw, sql);
        boolean used = false;
        while (resultSet.next()) {
            used = true;
            TempBan tempBan = new TempBan(true,
                    resultSet.getString("reason"),
                    resultSet.getString("Name"),
                    resultSet.getInt("oldmillis"),
                    resultSet.getInt("banneduntil"));
            tempBanList.add(tempBan);
        }
        if(!used) {
            tempBanList.add(new TempBan(false ,"", "", 0, 0));
        }
        return tempBanList;

    }

    @Override
    public boolean removeTempBan(String name) {
        String sql = "DELETE FROM tempban WHERE `Name`='" + name + "'";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }



    @Override
    public boolean addBan(String player, String reason) {
        String sql = "INSERT INTO permban (`Name`, `reason`) VALUES ('" + player + "', '" + reason + "')";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }

    @Override
    public boolean removeBan(String name) {
        String sql = "DELETE FROM permban WHERE `Name`='" + name + "'";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }

    @Override
    public List<PermBan> getBan(String player) throws SQLException {

        String sql = "SELECT * FROM `permban` WHERE `Name`='" + player + "'";
        List<PermBan> permBanList = new ArrayList<PermBan>();
        ResultSet resultSet = MySQLAccess.getSQLContents(host, dbName, user, pw, sql);
        boolean used = false;
        while (resultSet.next()) {
            used = true;
            PermBan permBan = new PermBan(true,
                    resultSet.getString("reason"),
                    resultSet.getString("Name"));
            permBanList.add(permBan);
        }
        if(!used) {
            permBanList.add(new PermBan(false ,"", ""));
        }
        return permBanList;


    }

    @Override
    public int getMoney(String player) throws SQLException {
        String sql = "SELECT * FROM `money` WHERE `Name`='" + player + "'";
        ResultSet resultSet = MySQLAccess.getSQLContents(host, dbName, user, pw, sql);
        int i = 0;
        while (resultSet.next()) {
            i = resultSet.getInt("money");
        }
        return i;
    }

    @Override
    public boolean setMoney(String player, int count) {
        String sql = "DELETE FROM money WHERE `Name`='" + player + "'";
        boolean b1 = MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
        String sql2 = "INSERT INTO money (Name, money) VALUES ('" + player + "', " + count + ")";
        boolean b2 = MySQLAccess.setSQLContents(host, dbName, user, pw, sql2);

        if(!b1 || !b2) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean addMail(String from, String to, String msg) {
        String sql2 = "INSERT INTO mails (fromPlayer, toPlayer, msg) VALUES ('" + from + "', '" + to + "', '" + msg + "')";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql2);
    }

    @Override
    public List<Mail> getMails(String name) throws SQLException {
        String sql = "SELECT * FROM mails WHERE `toPlayer`='" + name + "'";
        ResultSet resultSet = MySQLAccess.getSQLContents(host, dbName, user, pw, sql);
        int i = 0;
        List<Mail> list = new ArrayList<Mail>();
        while (resultSet.next()) {
            list.add(new Mail(resultSet.getString("fromPlayer"), resultSet.getString("toPlayer"), resultSet.getString("msg")));
        }
        return list;
    }

    @Override
    public boolean delMail(String to, String msg) {
        String sql = "DELETE FROM mails WHERE `toPlayer`='" + to + "' AND `msg`='" + msg + "'";
        return MySQLAccess.setSQLContents(host, dbName, user, pw, sql);
    }

}

