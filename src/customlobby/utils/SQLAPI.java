package customlobby.utils;

public interface SQLAPI {


    void initialize(String hostName, String userName, String pwName, String dbNameL);

    boolean canConnect();

    void SQLConnect();

    //TODO: Erweitern




}
