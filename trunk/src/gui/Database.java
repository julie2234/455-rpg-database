import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;;

public class Database {
	private static Database _db = null;
	
	public static Database getDb() {
		if (_db == null) {
			_db = new Database();
		}
		return _db;
	}
	
	public static String executeQuery(String query) throws Exception {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		String ret = "";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/rpg?user=root&password=");
		statement = conn.createStatement();
		rs = statement.executeQuery(query);
		ResultSetMetaData meta = rs.getMetaData();
		int colCnt = meta.getColumnCount();
		for (int i = 1; i <= colCnt; i++) {
			ret += meta.getColumnName(i) + "\t\t";
		}
		ret += "\r\n";
		while (rs.next()) {
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				ret += rs.getString(i) + "\t\t";
			}	
			ret += "\r\n";
		}

		if (rs != null) {
			rs.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (conn != null) {
			conn.close();
		}
		return ret;
	}
	
	/*
	 	//test
	 	public static void main(String[] args) throws Exception {
		executeQuery("SELECT itemID, itemName FROM Item where itemLevel = 1");
	}*/

}

