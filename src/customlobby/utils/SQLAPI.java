package customlobby.utils;

import java.util.List;

public interface SQLAPI {


    void initialize(String hostName, String userName, String pwName, String dbNameL);

    boolean canConnect();


    String getString(String path);
    List<String> getStringList(String path);

    int getInt(String path);

    void set(String path, Object object);


}
