package customlobby.utils;

import java.util.List;

public class SQLConfig implements SQLAPI {

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
        return false;
    }

    @Override
    public void addFriendReq(String name1, String name2) {

    }

    @Override
    public void addFriend(String name1, String name2) {

    }

    @Override
    public List<String> getFriends(String playername) {
        return null;
    }

    @Override
    public void addWarn(String player) {

    }

    @Override
    public int getWarncount(String player) {
        return 0;
    }

    @Override
    public void addTempBan(String player, String reason, int oldmillis, int banneduntil) {

    }

    @Override
    public TempBan getTempBan(String player) {
        return null;
    }

    @Override
    public void addBan(String player, String reason) {

    }

    @Override
    public PermBan getBan(String player) {
        return null;
    }


}

