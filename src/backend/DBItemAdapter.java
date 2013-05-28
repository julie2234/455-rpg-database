package backend;

import java.util.HashMap;
import java.util.Map;

import backend.DBManager.FieldType;

public class DBItemAdapter {

	/** The database where all data will be saved for Users. **/
	public static String DB_PATH = "alldata.db";

	/** The table that will be used for Users. **/
	public static String TABLE_NAME = "Backpack";

	/** The DBManager instance used to connect and run queries. **/
	private static DBManager _db_instance = null;

	/** Private constructor to prevent instantiation. */
	private DBItemAdapter() {
		// Do nothing...
	}

	/**
	 * Private method for setting up connection before a query/update.
	 * @return True if the connection is fully setup and ready for use.
	 */
	private static boolean checkDatabaseConnection() {

		// Make sure the DBManager has an instance.
		if (_db_instance == null)
			_db_instance = new DBManager(DB_PATH);

		// Connect to the instance.
		boolean rtn = _db_instance.connect();

		// If connected attempt to make/or-not the table (doesn't override so this is ok).
		if (rtn) {

			// Create field setup for table
			Map<String, FieldType> fields = new HashMap<String, FieldType>();
			fields.put("itemID", FieldType.INTEGER);
			fields.put("itemName", FieldType.TEXT);
			fields.put("itemLevel", FieldType.INTEGER);
			fields.put("cost", FieldType.INTEGER);
			fields.put("defense", FieldType.INTEGER);
			fields.put("damage", FieldType.INTEGER);
			fields.put("potency", FieldType.INTEGER);
			fields.put("duration", FieldType.INTEGER);
			fields.put("itemType", FieldType.TEXT);

			_db_instance.createTable(TABLE_NAME, fields, "itemID", true);
		}
		return rtn;
	}

	/**
	 * A convenience method for disconnecting from the database.
	 */
	private static void disconnect() {
		if (_db_instance != null)
			_db_instance.disconnect();
	}

	/**
	 * Retrieve an SQL compatible String from the given object, or NULL if the
	 * object will not be converted correctly.
	 * 
	 * @param obj The object to retrieve string for.
	 * @return An String which can be used in SQL statement or null.
	 */
	private static String sqlAcceptable(final Object obj) {

		if (obj == null) {
			return "NULL";
		} else if (obj instanceof Integer) {
			return obj.toString();
		} else if (obj instanceof String) {
			return "'" + obj.toString() + "'";
		} else {
			throw new IllegalArgumentException("invalid type to be inserted in to sql statement");
		}
	}

	public static boolean addItem(String name, int lvl, int cost, int def, int dmg, int pot, int dur, String type) {
		boolean rtn = false;		
		
		if (checkDatabaseConnection()) {
			// Build the sql statement.
			final String sql = "INSERT into "
					+ TABLE_NAME
					+ " (itemName, itemLevel, cost, defense, damage, potency, duration, itemType) values ("
					+ name + "," + lvl + "," + cost + "," + def + "," + dmg + "," + pot + "," + dur + "," + type + ")";
			// Run the statement
			if (_db_instance.runUpdate(sql) > 0)
				rtn = true;
			// Disconnect from database and change return status.
			disconnect();
		}
		return rtn;
	}


	/**
	 * Delete a given user from the database, using the users email as the
	 * indication on what user to delete.
	 * 
	 * @param usr
	 * @return
	 */
	public static boolean removeItem(int itemID) {
		boolean rtn = false;
		if (checkDatabaseConnection()) {
			// Build the sql statement.
			final String sql = "DELETE FROM " + TABLE_NAME + " WHERE itemID='" + itemID + "'";

			// Run the statement
			if (_db_instance.runUpdate(sql) > 0) {
				rtn = true;
			}
			// Disconnect from database and change return status.
			disconnect();
		}
		return rtn;
	}


}
