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
        return MySQLAccess.connectToMysql(host, dbName, user, pw);
    }

    @Override
    public String getString(String path) {
        return null;
    }

    @Override
    public List<String> getStringList(String path) {
        return null;
    }

    @Override
    public int getInt(String path) {
        return 0;
    }

    @Override
    public void set(String path, Object object) {

    }
}
