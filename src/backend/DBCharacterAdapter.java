package backend;

import java.util.HashMap;
import java.util.Map;

import backend.DBManager.FieldType;

public class DBCharacterAdapter {

	/** The database where all data will be saved for Users. **/
	public static String DB_PATH = "alldata.db";

	/** The table that will be used for Users. **/
	public static String TABLE_NAME = "Character";

	/** The DBManager instance used to connect and run queries. **/
	private static DBManager _db_instance = null;

	/** Private constructor to prevent instantiation. */
	private DBCharacterAdapter() {
		// Do nothing...
	}

	/**
	 * Private method for setting up connection before a query/update.
	 * 
	 * @return True if the connection is fully setup and ready for use.
	 */
	private static boolean checkDatabaseConnection() {

		// Make sure the DBManager has an instance.
		if (_db_instance == null)
			_db_instance = new DBManager(DB_PATH);

		// Connect to the instance.
		boolean rtn = _db_instance.connect();

		// If connected attempt to make/or-not the table (doesn't override so
		// this is ok).
		if (rtn) {

			// Create field setup for table
			Map<String, FieldType> fields = new HashMap<String, FieldType>();
			fields.put("charID", FieldType.INTEGER);
			fields.put("charName", FieldType.TEXT);
			fields.put("race", FieldType.TEXT);
			fields.put("playerBoolean", FieldType.BOOLEAN);
			fields.put("HP", FieldType.INTEGER);
			fields.put("MP", FieldType.INTEGER);
			fields.put("experience", FieldType.INTEGER);
			fields.put("charLevel", FieldType.INTEGER);
			fields.put("stamina", FieldType.INTEGER);
			fields.put("backpackID", FieldType.INTEGER);

			//charID = key, autoincrementing.
			_db_instance.createTable(TABLE_NAME, fields, "charID", true);
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
	public static boolean addNewChar(final String charName, final String race) {

		boolean rtn = false;

		final StringBuilder sb = new StringBuilder();

		sb.append(sqlAcceptable(charName));
		sb.append(", ");
		sb.append(sqlAcceptable(race));
		sb.append(", ");
		sb.append(sqlAcceptable(true));
		sb.append(", ");
		//default values for new char?? what's the hp/mp/etc?
		sb.append(sqlAcceptable(100));
		sb.append(", ");
		sb.append(sqlAcceptable(100));
		sb.append(", ");
		sb.append(sqlAcceptable(0));
		sb.append(", ");
		sb.append(sqlAcceptable(1));
		sb.append(", ");
		sb.append(sqlAcceptable(100));
		//note: took out weightCap to make this easier...

		//backpackID needs to match backpack??
		
		sb.append(", ");
		sb.append(sqlAcceptable(100));
		
		
		if (checkDatabaseConnection() && charName != null && race != null) {
			// Build the sql statement.
			final String sql = "INSERT into "
					+ TABLE_NAME
					+ " (charName, race, playerBoolean, HP, MP, experience, charLevel, stamina, backpackID) values ("
					+ sb.toString() + ")";
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
	public static boolean deleteUser(final String charName) {
		boolean rtn = false;
		if (checkDatabaseConnection() && charName != null) {
			// Build the sql statement.
			final String sql = "DELETE FROM " + TABLE_NAME + " WHERE charName='" + charName + "'";

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
