package DbOperations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
  	    private static DbConnection instance = null;
	    private Connection connection=null;
	    private static String url = "jdbc:postgresql://localhost:5432/train_ticket_booking_system";
	    private static String user = "postgres";
	    private static String password = "admin";

	    private DbConnection() throws SQLException {
			// try{
			// Class.forName("org.postgresql.Driver");
			// }catch(Exception e){
			// 	e.printStackTrace();
			// }
	        connection = DriverManager.getConnection(url, user, password);
	    }

	    public static DbConnection getInstance() throws SQLException {
	        if (instance == null) {
	            instance = new DbConnection();
	        }
	        return instance;
	    }

	    public Connection getConnection() throws SQLException {
	    	if(!connection.isValid(5)) {
	    		throw new SQLException();
	    	}
	        return connection;
	    }
	    public void closeConnection() throws SQLException {
	    	connection.close();
	    	connection=null;
	    }

}