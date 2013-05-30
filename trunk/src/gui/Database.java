package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
	
	public static JTable executeQuery(String query) throws Exception {
		rs = statement.executeQuery(query);
		TableModel model = resultSetToTableModel(rs);
		return new JTable(model);
	}
	
	public static String executeQueryString(String query) throws Exception {
		rs = statement.executeQuery(query);
		ResultSetMetaData meta = rs.getMetaData();
		String result = "";
		int colCnt = meta.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				result += rs.getString(i);
			}	
		}
		return result;
		
	}
	
	public static int executeUpdate(String query) throws SQLException {
		int num = statement.executeUpdate(query);
		return num;
	}
	
	public static TableModel resultSetToTableModel(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Vector columnNames = new Vector();
            for (int column = 0; column < numberOfColumns; column++) {
                columnNames.addElement(metaData.getColumnLabel(column + 1));
            }
            Vector rows = new Vector();
            while (rs.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= numberOfColumns; i++)
                    newRow.addElement(rs.getObject(i));
                rows.addElement(newRow);
            }
            return new DefaultTableModel(rows, columnNames);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

