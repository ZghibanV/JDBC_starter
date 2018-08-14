package jdbc.JDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.stream.events.StartElement;

/**
 * Hello world!
 *
 */
public class DB_RUN_resultSetScroll 
{
	  public static void main( String[] args ) throws Exception
	    {
	        System.out.println( "Hello World!" );
	                 
	        //
	        /*
	         *  connection string : 
	         *    1 , jdbc
	         *  2 , vendor name  for example : oracle mysql mariadb posgress 
	         *  3 , sub type : driver type 
	         *  4 , hostname  
	         *  5 , port name
	         *  6 , SID / Service    
	         * 
	         * */
	        
	        
	        //String url = "jdbc:oracle:thin:@YOUR-EC2-HOSTNAME-GOES-HERE:1521:xe";
	        String url = "jdbc:oracle:thin:@ec2-18-206-54-148.compute-1.amazonaws.com:1521:xe";
			//String url = "jdbc:oracle:thin:@localhost:49161:xe";
			String user = "HR"; // or your username
			String password= "hr";// or your password
			Connection conn = DriverManager.getConnection(url, user, password) ;
	    
	    Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    
	    ResultSet rs = st.executeQuery("SELECT * FROM JOBS") ; 
	    ResultSetMetaData rsmd = rs.getMetaData() ; 
	    
	    int colCount = rsmd.getColumnCount() ;   

	    while(rs.next()) {
	      
	      for (int i = 1; i <= colCount; i++) 
	        System.out.print(  rs.getObject(i) + "----");
	        
	      System.out.println();
	      
	    }
	    
	    rs.first() ; 
	    System.out.print(  rs.getObject(1) + "----");
	    rs.last() ;  
	    System.out.print(  rs.getObject(1) + "----");
	    rs.getRow();  // will return current row number 
	    
	    // task find out how many row returned from the result 
	    
	    int rowCount = rs.getRow();
	    System.out.println("row count is -- " + rowCount);
	    
	    rs.beforeFirst();
	    rs.afterLast();
	    System.out.println("---------------------------------++------------------------------");
	    //task 3 -- > print the resultset in reversed order. 
	    rs.afterLast();
		while (rs.previous()) {
			System.out.print(rs.getRow() + "\t");
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.printf("%10s  ", rs.getObject(i));
			}
			System.out.println();
		}
	    
	    
	    
	   // System.out.println(rs.getObject(1) + "----");
	    
	    
	    rs.close();
	    st.close();
	    conn.close();

	      
	      
	      //System.out.println(rs.getInt(4) );
	      
	      /// try to print out all the value from all other column on this row 
	        
	      
	    System.out.println("connected ");
	        
	    }
	}
