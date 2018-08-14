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
public class DB_RUN_result
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        
        
        
        String url = "jdbc:oracle:thin:@ec2-18-206-54-148.compute-1.amazonaws.com:1521:xe";
		//String url = "jdbc:oracle:thin:@localhost:49161:xe";
		String user = "HR"; // or your username
		String password= "hr";// or your password
		Connection conn = DriverManager.getConnection(url, user, password) ;
		
		
		
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery("SELECT * FROM JOB_history") ; 
		ResultSetMetaData rsmd = rs.getMetaData();
		
		
		int colCount = rsmd.getColumnCount();
		System.out.println(colCount + " colCount");
		System.out.println("column name rsmd.getColumnName(1) : "+ rsmd.getColumnName(1));
		
		
		// find column name of all the columns from the resultset 
		 
		rs.next();
            for(int i=1; i<= colCount; i++) {
                System.out.println(rsmd.getColumnName(i)+" -- ");
                	
		}
		
	
    }
}
