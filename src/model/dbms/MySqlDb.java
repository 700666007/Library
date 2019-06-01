package model.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
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
		_setPath(_buildPath(address,schema,username,password));
	}
	private String _buildPath(String address, String schemaName, String username, String password) {
		return "jdbc:mysql://" + address + "/" + schemaName +
				"?user=" + username + "&password=" + password +
				"&useUnicode=true" +
				"&serverTimezone=UTC" +
				"&useLegacyDatetimeCode=false" +
				"&useJDBCCompliantTimezoneShift=true";
	}

	private void _setPath(String path) { this.path = path; }
	Log logger = Log.getInstance();
	
	// CONNECTION ==================================================================================================================================
	protected Connection getConn() {
		logger.info("Connecting to database...");
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(path);
			logger.info("Connection established!");
		} catch(Exception e) {
			logger.error("Couldn't connect to database..",e);
		}
		return conn;
	}

	public enum Table { BOOKS, GENRES, USERS }
	
	// FETCH, DELETE, INSERT, UPDATE ==================================================================================================================================
	public Map<String,String> fetchUser(String username, char[] password) {
		try {
			return row("SELECT * FROM users WHERE username = ? AND password = ?",username,password);
		} catch(Exception e) {
			logger.error("Operation (FETCH_USER) failed.", e);
		}
		return null; 
	}
	public List<Map<String,String>> fetch(String content, Table table, String orderBy) {
		try {
			return rows("SELECT "+content+" FROM "+table+(
					orderBy!=null?" ORDER BY "+orderBy:"")
			);
		} catch(Exception e) {
			logger.error("Operation (FETCH) failed.",e);
		}
		return new ArrayList<Map<String,String>>();
	}
	public boolean delete(String table, String key, String value) {
		try {
			if( execute(
					"DELETE FROM "+table+" WHERE "+key+" = ?",
					new String[] {value} )
			)
				logger.info("Deleted '"+key+":"+value+" from '"+table+"'");
			return true;
		} catch(Exception e) {
			logger.error("Operation (DELETE) failed.",e);
		}
		return false;
	}
	public boolean insert(String table, String column, String value) {
		try {
			if( execute(
					"INSERT INTO "+table+"("+column+") VALUES (?)",
					new String[] {value} )
			)
				logger.info("Inserted into '"+table+"' value "+value);
			return true;
		} catch(Exception e) {
			logger.error("Operation (INSERT) failed.",e);
		}
		return false;
	}
	public boolean insert(String table, Map<String,String> map) {
		try {
			int size = map.size();
			String query = "INSERT INTO "+table+
					" ("+MyUtils.commaSepValues(MyUtils.set2list(map.keySet()))+
					") VALUES ("+MyUtils.commaSepQuestionMarks(size)+")";
			String[] params = map.values().toArray(new String[size]);
			if( execute(query,params) )
				logger.info("Inserted into '"+table+"' value "+MyUtils.renderMap(map));
			return true;
		} catch(Exception e) {
			logger.error("Operation (INSERT) failed.",e);
		}
		return false;
	}
	public boolean update(String table, String column, String newValue, String id) {
		try {								// TODO dovrebbe essere id non title
			if(execute("UPDATE "+table+" SET "+column+" = ? WHERE title = ?",
					new String[] {newValue,id} ))
				logger.info("Updated '"+table+"': '"+column+"' of element "+id+" set to '"+newValue+"'");
			return true;
		} catch(Exception e) {
			logger.error("Operation (UPDATE) failed.",e);
		}
		return false;
	}
}
