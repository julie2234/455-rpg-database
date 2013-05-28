package backend;

import java.util.HashMap;
import java.util.Map;

import backend.DBManager.FieldType;

public class DBSpellTreeAdapter {

	/** The database where all data will be saved for Users. **/
	public static String DB_PATH = "alldata.db";

	/** The table that will be used for Users. **/
	public static String TABLE_NAME = "SpellTree";

	/** The DBManager instance used to connect and run queries. **/
	private static DBManager _db_instance = null;

	/** Private constructor to prevent instantiation. */
	private DBSpellTreeAdapter() {
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
			fields.put("charID", FieldType.INTEGER);
			fields.put("magicType", FieldType.TEXT);
			fields.put("novice", FieldType.INTEGER);
			fields.put("apprentice", FieldType.INTEGER);
			fields.put("adept", FieldType.INTEGER);
			fields.put("expert", FieldType.INTEGER);
			fields.put("master", FieldType.INTEGER);

			//NOTE: THIS WON"T WORK!!!! How do we get multiple keys..?
			_db_instance.createTable(TABLE_NAME, fields, "charID", false);
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
			throw new IllegalArgumentException(
					"invalid type to be inserted in to sql statement");
		}

	}

	/**
	 * Add a new character to the database.
	 */
	public static boolean addSpellTree(int charID, String magicType) {
		boolean rtn = false;		
		
		if (checkDatabaseConnection()) {
			// Build the sql statement.
			final String sql = "INSERT into "
					+ TABLE_NAME
					+ " (charID, magicType) values ("
					+ charID + "," + magicType + ")";
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
	public static boolean removeTree(int charID) {
		boolean rtn = false;
		if (checkDatabaseConnection()) {
			// Build the sql statement.
			final String sql = "DELETE FROM " + TABLE_NAME + " WHERE charID='" + charID + "'";

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
