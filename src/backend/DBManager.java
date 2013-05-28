package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * 
 * The DBManager is a wrapper which uses SQLite JDBC server-less
 * database to store persistent data on the harddrive. This
 * COULD be used to access a remote database however in our
 * particular case we will not be actually using server/client
 * model.
 *
 */
public class DBManager {
	
	/** The default query timeout in seconds. **/
	public static final int MAX_QUERY_TIMEOUT = 30; // in seconds.
	
	/** The connection of this DBManager. **/
	private Connection conn = null;
	
	/** The database path to load/store data in. **/
	private String dbpath = null;

	/** The statement to run queries with for this DBManager. **/
	private Statement stmt = null;
	
	/** The timeout used by this DBManager, in seconds. **/
	private int timeout_sec = MAX_QUERY_TIMEOUT;
	
	/**
	 * The acceptable field types within a table.
	 */
	public static enum FieldType {
		NULL,
		INTEGER,
		REAL,
		TEXT,
		BOOLEAN;
	}
	
	/**
	 * Create a DBManager object using the specified database path.
	 * @param path The path, relative-or fully qualified, to the 
	 * database file.
	 * @throws IllegalArgumentException if path specified is null or empty.
	 */
	public DBManager(final String path)
		throws IllegalArgumentException {
		
		if ( path == null || path.isEmpty() )
			throw new IllegalArgumentException("dppath cannot be null");
		
		dbpath = path;
	}

	/**
	 * Check, on the current connected database, if the specified table
	 * exists.
	 * @param table_name The name of the table.
	 * @return True if the table exists, false if it does not or an error occurs.
	 * @throws IllegalStateException if connection or statement are null or were
	 * not properly initialized before the call to checkTableExists().
	 * @throws IllegalArgumentException if <code>table_name</code> is null or
	 * empty.
	 */
	public final boolean checkTableExists(final String table_name)
		throws IllegalStateException, IllegalArgumentException {
		
		if ( conn == null || stmt == null)
			throw new IllegalStateException("no connection to run query on!");
		if (table_name == null || table_name.isEmpty())
			throw new IllegalArgumentException("table name cannot be null or empty");
		
		boolean rtn = false;
		ResultSet rslt = runQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + table_name + "'");
		
		// If rslt==null then no table exists due to error.
		// If rslt!=null and there is at least 1 row, then table exists.
		try {
			rtn = (rslt != null) && (rslt.next());
		} catch (SQLException e) {
			rtn = false;
		}		
		
		return rtn;
		
	}
	
	public final Connection getConnection() {
		return conn;
	}

	/**
	 * Connect to the database. DON'T FORGET TO CALL disconnect() WHEN
	 * FINISHED!
	 * @return boolean if connection was a success, false otherwise.
	 */
	public final synchronized boolean connect() {
		
		if ( dbpath == null ) {
			return false;
		}
		try {
			Class.forName("org.sqlite.JDBC");
		} catch ( ClassNotFoundException cnfe ) {
			System.err.println("could not load or.sqlite.JDBC device driver... check libs directory and recompile!");
			return false;
		}
		
		try {
			
			// Try and create a connection. (WILL CREATE THE DB AT PATH)
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbpath);
			
			// Try and create a statement object so we can run queries.
			stmt = conn.createStatement();
			stmt.setQueryTimeout(timeout_sec);
			
		} catch ( SQLException sqle ) {
			disconnect();
			return false;
		}
		
		return true;
		
	}

	/**
	 * Create a table with the specified table name, including the given fields.
	 * @param table_name The name to call the table.
	 * @param fields A Map containing field id's as key, and field type as value.
	 * REFER to {@link FieldType} for more info.
	 * @return boolean if the table is successfully created, false otherwise or on
	 * error.
	 * @throws IllegalArgumentException if <code>table_name</code> is null or empty.
	 * @throws IllegalStateException if connection or statement are null or were
	 * not properly initialized before calling createTable.
	 */
	public final boolean createTable(String table_name, Map<String, FieldType> fields, String primary_key, boolean autoinc)
		throws IllegalArgumentException, IllegalStateException {
		
		if (table_name == null)
			throw new IllegalArgumentException("cannot create a table with null table name");
		else if ( conn == null || stmt == null)
			throw new IllegalStateException("no connection to create table in");
		
		boolean success = false;
		
		// First check if table exists... do not overwrite tables.
		if (!checkTableExists(table_name)) {
		
			// Build the statement
			final StringBuilder sb = new StringBuilder();
			
			sb.append("create table ");
			sb.append(table_name);
			sb.append(" ");
			
			if (fields != null && !fields.isEmpty()) {
				sb.append("(");			
				
				final String[] ids = fields.keySet().toArray(new String[0]);
				for (int i = 0; i < ids.length; i++) {
					if (i > 0)
						sb.append(", ");
					sb.append(ids[i]);
					sb.append(" ");
					sb.append(fields.get(ids[i]));
					
					// Can add primary key to the end of this line or add a separate
					// line to the end of "Primary Key ( field1, field2...)
					if (ids[i].equals(primary_key))
						sb.append(" PRIMARY KEY");
					if (autoinc && fields.get(ids[i]).equals(FieldType.INTEGER))
						sb.append(" AUTOINCREMENT");
				}
				
				sb.append(")");
			}
			
			System.out.println(sb.toString());
			final int rslt = runUpdate(sb.toString()); 
			
			// Check for exception
			if (rslt == -1) {
				success = false;
			// Do a final check to make sure table was created.
			} else 
				success = checkTableExists(table_name);
		} 
		return success;
	}

	/**
	 * Create an indexing field which will serve to prevent multiple rows having
	 * the same value in the specified field. For instance, if a list of employees
	 * are to be saved to a table, each with a unique id number, a Unique Index Field
	 * should be created to prevent multiple persons from entering the table with the
	 * same id. The index_label is mostly useless unless you specify advanced search
	 * queries-the index_label will serve to provide quicker searches in this aspect.
	 * @param table_name The table name to create the indexing field.
	 * @param field_id The, previously created, field id name to use as a unique index.
	 * @param index_label The label to call this indexing field.
	 * @return True if the database took the indexing field successfully, false otherwise
	 * or if an SQLException was thrown and caught.
	 * @throws IllegalArgumentException if ANY field is null or empty.
	 * @thorws IllegalStateException if connection or statement are null or were not properly
	 * initialized before the call to createUniqueIndexElement.
	 */
	public final boolean createUniqueIndexElement(final String table_name, final String field_id, final String index_label) {
		
		if (table_name == null || table_name.isEmpty()
			|| field_id == null || field_id.isEmpty()
			|| index_label == null || index_label.isEmpty())
			throw new IllegalArgumentException("all arguments must be non-null and non-empty");
		
		if ( conn == null || stmt == null )
			throw new IllegalStateException("there is no database connection to provide an inexing field for");
		
		
		return (runUpdate("create unique index " + index_label + " on " + table_name + " (" + field_id + ")") != -1);
		
	}
	
	/**
	 * Delete the specified table from this database.
	 * @param table_name The table to delete.
	 * @return True if table was successfully removed, or was never
	 * present. False if, by the end of this call, the table still
	 * remains.
	 * @throws IllegalStateException if connection or statement are null
	 * or were not properly initialized before calling deleteTable.
	 */
	public final boolean deleteTable(final String table_name)
		throws IllegalStateException {
		
		boolean rtn = true;
		
		if (conn == null || stmt == null)
			throw new IllegalStateException("no connection to delete tables from");
		
		if (checkTableExists(table_name)) {
			
			final int rslt = runUpdate("drop table if exists " + table_name);
			
			if (rslt == -1) {
				rtn = false;
			} else
				rtn = !checkTableExists(table_name);
			
		}
		
		return rtn;
		
	}

	/**
	 * Disconnect from the database. NOTE: Calling this on an un-connected
	 * DBManager will have no effect... SO USE IT! 
	 */
	public final void disconnect() {
		
		// Only attempt a disconnect on a non-null object.
		if ( conn != null ) {
			
			try {
				
				conn.close();
				
			} catch ( SQLException sqle ) {
				System.err.println("could not close database connection!");
			}
			
		}
		
	}

	/**
	 * Retrieve the current query timeout setting, in seconds.
	 * @return The integer number of seconds before a query will time out.
	 */
	public final int getQueryTimeout() {
		
		return timeout_sec;
		
	}
	
	/**
	 * Execute a standard SQL Statement which will return a
	 * {@link ResultSet}. USE runUpdate(..) FOR UPDATE/INSERT/DELETE
	 * STATEMENTS.
	 * @param qry_str The SQL query to execute.
	 * @return A ResultSet object that contains the data produced by
	 * the given query. Returns <code>null</code> if an SQLException
	 * is thrown and caught.
	 * @throws IllegalStateException if connection or statement are null
	 * or were not properly initialized before the call to runQuery().
	 */
	public final synchronized ResultSet runQuery(final String qry_str)
		throws IllegalStateException {
		
		if ( conn == null || stmt == null)
			throw new IllegalStateException("no connection to run query on!");
		
		ResultSet rtn = null;
		try {
			rtn = stmt.executeQuery(qry_str);
		} catch ( SQLException sqle ) {
			rtn = null;
		}
		
		return rtn;
		
	}

	/**
	 * Execute either an Update, Insert, or Delete statement on the
	 * current connection. USE runQuery(..) FOR STANDARD QUERYING
	 * TO GET A {@link ResultSet}.<BR><BR>
	 * <em>(NOTE: ALL SALES ARE FINAL! CHANGES ARE AUTOMATICALLY
	 * SAVED TO DATABASE; TRY TO USE CONVENIENCE METHODS WHENEVER
	 * POSSIBLE!)</em>
	 * @param stmt_str The SQL UPDATE/INSERT/DELETE statement string.
	 * @return Either the row count for INSERT, UPDATE or DELETE
	 * statement or 0 for SQL that returns nothing. Returns
	 * -1 if an SQLException has been thrown and caught.
	 * @throws IllegalStateException if connection has not been made
	 * or statement was not properly initialized before calling runUpdate().
	 */
	public final synchronized int runUpdate(final String stmt_str)
		throws IllegalStateException {
		if ( conn == null || stmt == null )
			throw new IllegalStateException("no connection to run update on!");
		
		int rtn = 0;
		try {
			rtn = stmt.executeUpdate(stmt_str);
		} catch ( SQLException sqle ) {
			System.err.println(sqle.getMessage() + ": while running [" + stmt_str + "]");
			rtn = -1;
		}
		return rtn;
	}

	/**
	 * Manually set the query timeout, in seconds. Default value is 30
	 * seconds.
	 * @param seconds The integer number of seconds before a query times out.
	 * @throws IllegalArgumentException if <code>seconds</code> is less than 1.
	 */
	public final synchronized void setQueryTimeout(final int seconds)
		throws IllegalArgumentException {
		if (seconds < 1)
			throw new IllegalArgumentException("timeout must be greater than 0");
		
		timeout_sec = seconds;
		if (stmt != null) {
			try {
				stmt.setQueryTimeout(timeout_sec);
			} catch ( SQLException sqle ) {
				// Do nothing.
			}
		}	
	}	
}