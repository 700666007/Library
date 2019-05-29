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
	private String path;
	
	// CONSTRUCTOR ==================================================================================================================================
	public MySqlDb(String address, String schema, String username, String password) {
		_setPath(_buildPath(address,path,username,password));
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
	private void _setPath(String path) { this.path = path; }
	
	Log logger = Log.getInstance();
	
	// CONNECTION ==================================================================================================================================
	protected Connection getConn() {
		logger.info("Connecting to database...");
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(path);
		} catch(Exception e) {
			logger.error("Couldn't connect to database..",e);
		}
		logger.info("Connection established!");
		return conn;
	}

	// FETCH, DELETE, INSERT, UPDATE ==================================================================================================================================
	List<Map<String,String>> fetch(String content, String table) {
		try {
			return rows("SELECT ? FROM ?", "content-table".split("-"));
		} catch(Exception e) {
			logger.error("Operation (FETCH) failed.",e);
		}
		return new ArrayList<Map<String,String>>();
	}
	boolean delete(String table, String key, String value) {
		try {
			if(execute("DELETE FROM ? WHERE ? = '?'", "table-key-value".split("-")))
				logger.info("Deleted '"+key+":"+value+" from '"+table+"'");
			return true;
		} catch(Exception e) {
			logger.error("Operation (DELETE) failed.",e);
		}
		return false;
	}
	boolean insert(String table, String column, String value) {
		Map<String,String> map = new HashMap<>();
		map.put(column, value);
		return insert(table,map);
	}
	boolean insert(String table, Map<String,String> map) {
		try {
			if(execute("INSERT INTO ? (?) VALUES (?)", 
						new String[] { table, MyUtils.commaSepValues(MyUtils.set2list(map.keySet())),
											  MyUtils.commaSepValues(MyUtils.set2list(map.values())) }))
				logger.info("Inserted into '"+table+"' value "+MyUtils.renderMap(map));
			return true;
		} catch(Exception e) {
			logger.error("Operation (INSERT) failed.",e);
		}
		return false;
	}
	boolean update(String table, String column, String newValue, String id) {
		try {								// TODO dovrebbe essere id non title
			if(execute("UPDATE ? SET ? = ? WHERE title = ?", "table-colName-newValue-id".split("-")))
				logger.info("Updated '"+table+"': '"+column+"' of element "+id+" set to '"+newValue+"'");
			return true;
		} catch(Exception e) {
			logger.error("Operation (UPDATE) failed.",e);
		}
		return false;
	}
}
