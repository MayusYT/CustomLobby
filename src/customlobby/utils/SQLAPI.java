package customlobby.utils;

import java.util.List;

public interface SQLAPI {


    void initialize(String hostName, String userName, String pwName, String dbNameL);

    boolean canConnect();


    void addFriendReq(String name1, String name2);
    List<String> getFriendReqs(String playername);
    void removeFriendReq(String name1, String name2);
    void addFriend(String name1, String name2);
    List<String> getFriends(String playername);
    void removeFriend(String name1, String name2);



    void addWarn(String player);
    int getWarncount(String player);

    void addTempBan(String player, String reason, int oldmillis, int banneduntil);
    TempBan getTempBan(String player);


    void addBan(String player, String reason);
    void removeBan(String name);
    PermBan getBan(String player);



}
