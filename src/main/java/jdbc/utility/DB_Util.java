package jdbc.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DB_Util {

	  private static Connection conn ; 
	  private static Statement st ; 
	  private static ResultSet rs ; 
	  
	  
	  public static void main(String[] args) throws Exception {
	    
	    // Establish Connection 
	    
	    boolean b = establishConnection() ; 
	    System.out.println(b);
	    // Create Statement Run Query to store result to collection object 
	    
	    // get row count 
	    
	    // Close The connection 

	  }

	  public static boolean establishConnection() throws Exception {
	    
	    String url = ConfigurationReader.getProperty("connectionString");
	    String user = ConfigurationReader.getProperty("DB_user"); // or your username
	    String password= ConfigurationReader.getProperty("DB_pass");// or your password
	    conn = DriverManager.getConnection(url, user, password) ;
	    if(conn!= null)
	      return true ; 
	    
	    return false; 
	  }
	  
	  //Store the result set in collection of some kind so we can directly work with. 
	  // myResult.goToRow(2).getValueByColumnName("my name");
	  
	  //list of rows. --> map of colName - value
	  //List< Map < String, Object>> 
	  
	  public static List <Map <String, Object>> 
	  runQueryGetResult(String query) throws SQLException {
		  
		  List < Map <String, Object>> rowList = new ArrayList<>();
		  
		  st = conn.createStatement(
				  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		  
		  rs = st.executeQuery(query);
		  ResultSetMetaData rsmd = rs.getMetaData();
		  
		  while ( rs.next()) {
			  
			  Map< String, Object > colNameValueMap = new HashMap<>();
			  
			  for (int i = 1; i < rsmd.getColumnCount(); i++) {
				System.out.println("column name is : " + rsmd.getColumnName(i));
				System.out.println("value is: " + rs.getObject(i));				
				colNameValueMap.put(rsmd.getColumnName(i), rs.getObject(i));
			}
			  rowList.add(colNameValueMap);
		  }
		  
		  return null;
				  
	  }
	  
	  
	  public static int getRowCount() throws Exception { 
		  rs.last();
		  int rowCount = rs.getRow();
		  return rowCount;
	  }
	  
	}