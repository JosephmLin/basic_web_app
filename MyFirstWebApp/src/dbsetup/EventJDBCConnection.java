package dbsetup;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.event.EventBatch;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import controller.ObjectController;
public class EventJDBCConnection {


	public static final String getAllEntries = "myfirstwebapp.dbsetup.jdbcconnection.getallentries";
	public static final String getCount= "myfirstwebapp.dbsetup.jdbcconnection.getCount";

	private static ArrayList<Object> values;
	private static ArrayList<String> columnNames;
	private static int columnCount;
	private static int eventcount;
	public EventJDBCConnection()
	{
		executeAllEntries(0);
		executeCount();
	}
	public static void main(String[] args)
	{
		new EventJDBCConnection();
	}
	public Object[] getValues()
	{
		return values.toArray();
	}
	public String[] getColumnNames()
	{
		return columnNames.toArray(new String[columnNames.size()]);
	}
	public int getColumnCount()
	{
		return columnCount;
	}
	public int getCount()
	{
		executeCount();
		return eventcount;
	}
	//receives a command and a page number.
	public void executeCount()
	{
		String sql_command = "SELECT COUNT(*) FROM events.events";
		Connection conn = null;
		Statement stmt = null;
		
		try{
			ResultSet rs = JDBCConnection.getResultSet(sql_command, conn, stmt);
			while(rs.next())
			{
				
				EventJDBCConnection.eventcount = ((Long)rs.getObject(1)).intValue();
				//System.out.println("EVENT COUNT: " + EventJDBCConnection.eventcount);
			}
			//Values just saves each element based on the row/columns
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle.getMessage());
		}
		finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

	}
	
	public void executeAllEntries(int page)
	{

		if (values != null)
			values.clear();
		if (columnNames != null)
			columnNames.clear();
		Integer offset = page * EventBatch.default_size;
		String 	sql_command = "SELECT * FROM events.events LIMIT " + EventBatch.default_size + " OFFSET " + offset.toString();
		Connection conn = null;
		Statement stmt = null;
		
		try{
			ResultSet rs = JDBCConnection.getResultSet(sql_command, conn, stmt);
			ResultSetMetaData rsmd = rs.getMetaData();

			columnCount = rsmd.getColumnCount();
			columnNames = new ArrayList<String>(columnCount);
			values = new ArrayList<Object>(columnCount);

			for (int i = 1; i < columnCount+1; i++)
			{	
				String name = rsmd.getColumnName(i);
				columnNames.add(i-1, name);
			}

			while(rs.next())
			{
				for (int i = 1; i < columnCount+1; i++)
				{
					values.add(rs.getObject(i));
				}
			}
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle.getMessage());
		}
		finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
	}//end main
}//end JDBCExample

