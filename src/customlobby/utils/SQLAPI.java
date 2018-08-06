package customlobby.utils;

import java.sql.SQLException;
import java.util.List;

public interface SQLAPI {


    void initialize(String hostName, String userName, String pwName, String dbNameL);

    boolean canConnect();


    boolean addFriendReq(String name1, String name2);
    List<String> getFriendReqs(String playername) throws SQLException;
    boolean removeFriendReq(String name1, String name2);
    boolean addFriend(String name1, String name2);
    List<String> getFriends(String playername) throws SQLException;
    boolean removeFriend(String name1, String name2);



    boolean addWarn(String player) throws SQLException;

    boolean removeWarn(String player);

    int getWarncount(String player) throws SQLException;

    boolean addTempBan(String player, String reason, int oldmillis, int banneduntil);
    List<TempBan> getTempBan(String player) throws SQLException;


    boolean addBan(String player, String reason);
    boolean removeBan(String name);
    List<PermBan> getBan(String player) throws SQLException;



}
