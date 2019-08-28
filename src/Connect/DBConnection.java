package Connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "444");
		}
		catch(Exception connectionError) {
			System.out.println(connectionError);
			System.out.println("Connection error occured in DBConnection");
		}
		return con;
	}
}
