package customlobby.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.sql.Date;



public class MySQLAccess {

	private static Connection connection;
	private static ResultSet resultSet = null;
	
	
	public static ResultSet getSQLContents(String host, String database, String user, String passwd, String Quary) {
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			String connectionCommand = "jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwd;
			
			connection = DriverManager.getConnection(connectionCommand);
			
			Statement statement = connection.createStatement();
			
			resultSet = statement.executeQuery(Quary);
			
			return resultSet;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static boolean setSQLContents(String host, String database, String user, String passwd, String Quary) {
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			String connectionCommand = "jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwd;
			
			connection = DriverManager.getConnection(connectionCommand);
			
			Statement statement = connection.createStatement();
			
			statement.executeUpdate(Quary);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	
	 
	public static boolean connectToMysql(String host, String database, String user, String passwd){
	try{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	String connectionCommand = "jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwd;
	connection = DriverManager.getConnection(connectionCommand);
	Statement statement = connection.createStatement();
	resultSet = statement.executeQuery("select * from Connection");
    //writeResultSet(resultSet);
	connection.close();
	return true;
	 
	}catch (Exception ex){
	System.out.println("false");
	ex.printStackTrace();
	return false;
	}
	}
/*
	
	private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
        	int i = 1;
        	
        	String out = "";
        	
        	out = resultSet.getString(1);
        	out = out + ", " + resultSet.getString(2);
        	
        	/*
        	while (resultSet.getString(i) != "") {
        		
        		i++;
        	}
        	
          */
        	/*
            
             System.out.println(out);

            
        }
    }
	*/
}
