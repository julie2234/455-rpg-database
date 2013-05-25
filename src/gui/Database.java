package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;;

public class Database {
	private static Connection conn;
	private static Statement statement;
	private static ResultSet rs;
	private static String result;
	
	
	public static void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/rpg?user=root&password=");
		statement = conn.createStatement();
	}
	
	public static void disconnect() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (conn != null) {
			conn.close();
		}	
	}
	
	public static String executeQuery(String query) throws Exception {
		result = "";
		rs = statement.executeQuery(query);
		ResultSetMetaData meta = rs.getMetaData();
		int colCnt = meta.getColumnCount();
		for (int i = 1; i <= colCnt; i++) {
			result += meta.getColumnName(i) + "\t\t";
		}
		result += "\r\n";
		while (rs.next()) {
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				result += rs.getString(i) + "\t\t";
			}	
			result += "\r\n";
		}
		return result;
	}
	
}

