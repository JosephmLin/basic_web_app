package dbsetup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCConnection {
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";

	//  Database credentials
	private static final String USER = "guest";
	private static final String PASS = "password";
	
	public static ResultSet getResultSet(String sql_command, Connection conn, Statement stmt)
	{	
		try{
		//TODO: Abstract this layer a bit and make a new class called just JDBC Connection FROM HERE
		//STEP 2: Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");

		//STEP 3: Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);

		//STEP 4: Execute a query
		System.out.println("Creating a Statement.");
		stmt = conn.createStatement();


		//TODO: ABSTRACT TO HERE
		return stmt.executeQuery(sql_command);
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe.getMessage());
			return null;
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle.getMessage());
			return null;
		}
	}
}
