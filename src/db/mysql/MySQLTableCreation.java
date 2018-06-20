package db.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

/*
 * automatically reset our tables in our database. 
 * So in the future, you can run this function every time when you think the data stored in you DB is messed up.
 */
public class MySQLTableCreation {
	// Run this as Java application to reset db schema.
	public static void main(String[] args) {
		try {
			// This is java.sql.Connection. Not com.mysql.jdbc.Connection.
			Connection conn = null;

			// Step 1 Connect to MySQL.
			try {
				System.out.println("Connection to " + MySQLDBUtil.URL);
				Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance();
				conn = DriverManager.getConnection(MySQLDBUtil.URL);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (conn == null) {
				return;
			}
			
			// Step 2 Drop tables in case they exist 
			Statement stmt = conn.createStatement();
            String sql = "DROP TABLE IF EXISTS category";
            stmt.executeUpdate(sql);
            
            sql = "DROP TABLE IF EXISTS history";
            stmt.executeUpdate(sql);
            
            sql = "DROP TABLE IF EXISTS items";
            stmt.executeUpdate(sql);
            
            sql = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(sql);
			
			System.out.println("Import is done successfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
