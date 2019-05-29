package dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Log;
import utils.MyUtils;

public class MySqlDb extends Database implements IDatabase {
	
	// PROPERTIES ==================================================================================================================================
	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private String address;
	private String schema;
	private String username;
	private String password;
	private String path;
	
	// CONSTRUCTOR ==================================================================================================================================
	public MySqlDb(String address, String schema, String username, String password) {
		_setAddress(address)
		._setSchema(schema)
		._setUsername(username)
		._setPassword(password)
		._setPath(_buildPath(address,schema,username,password));
	}
	private String _buildPath(String address, String schemaName, String username, String password) {
		return "jdbc:mysql://" + address + "/" + schemaName +
				"?user=" + username + "&password=" + password +
				"&useSSL=false" + "&useUnicode=true" +
				"&useJDBCCompliantTimezoneShift=true" +
				"&useLegacyDatetimeCode=false" +
				"&serverTimezone=UTC";
	}

	// GETTERS / SETTERS ==================================================================================================================================
	private MySqlDb _setAddress(String address) { this.address = address; return this; }
	private MySqlDb _setSchema(String schema) { this.schema = schema; return this; }
	private MySqlDb _setUsername(String username) { this.username = username; return this; }
	private MySqlDb _setPassword(String password) { this.password = password; return this; }
	private void _setPath(String path) { this.path = path; }
	
	Log logger = Log.getInstance();
	
	// CONNECTION ==================================================================================================================================
	protected Connection getConn() {
		logger.info("Connecting to database...");
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			logger.debug("address: "+address);
			logger.debug("schema: "+schema);
			logger.debug("username: "+username);
			logger.debug("password: "+password);
			logger.debug("path: "+path);
			conn = DriverManager.getConnection(path);
			logger.info("Connection established!");
		} catch(Exception e) {
			logger.error("Couldn't connect to database..",e);
		}
		return conn;
	}

	public enum Table { BOOKS, GENRES }
	
	// FETCH, DELETE, INSERT, UPDATE ==================================================================================================================================
	public List<Map<String,String>> fetch(String content, Table table) {
		try {
			return rows("SELECT "+content+" FROM "+table);
		} catch(Exception e) {
			logger.error("Operation (FETCH) failed.",e);
		}
		return new ArrayList<Map<String,String>>();
	}
	public boolean delete(String table, String key, String value) {
		try {
			if( execute(
					"DELETE FROM ? WHERE ? = '?'",
					new String[] {table,key,value} )
			)
				logger.info("Deleted '"+key+":"+value+" from '"+table+"'");
			return true;
		} catch(Exception e) {
			logger.error("Operation (DELETE) failed.",e);
		}
		return false;
	}
	public boolean insert(String table, String column, String value) {
		Map<String,String> map = new HashMap<>();
		map.put(column, value);
		return insert(table,map);
	}
	public boolean insert(String table, Map<String,String> map) {
		try {
			if( execute(
					"INSERT INTO ? (?) VALUES (?)", 
					new String[] { table,
								   MyUtils.commaSepValues(MyUtils.set2list(map.keySet())),
								   MyUtils.commaSepValues(MyUtils.set2list(map.values())) })
			)
				logger.info("Inserted into '"+table+"' value "+MyUtils.renderMap(map));
			return true;
		} catch(Exception e) {
			logger.error("Operation (INSERT) failed.",e);
		}
		return false;
	}
	public boolean update(String table, String column, String newValue, String id) {
		try {								// TODO dovrebbe essere id non title
			if(execute("UPDATE ? SET ? = ? WHERE title = ?", new String[] {table,column,newValue,id} ))
				logger.info("Updated '"+table+"': '"+column+"' of element "+id+" set to '"+newValue+"'");
			return true;
		} catch(Exception e) {
			logger.error("Operation (UPDATE) failed.",e);
		}
		return false;
	}
}
