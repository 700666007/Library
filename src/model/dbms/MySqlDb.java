package model.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.entities.LibraryDaoImpl.SCHEMA_TOKEN;
import utils.Log;
import utils.MyUtils;
import view.IView;

public class MySqlDb extends Database implements IDatabase {
	
	// PROPERTIES ==================================================================================================================================
	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private String path;
	
	// CONSTRUCTOR ==================================================================================================================================
	public MySqlDb(String address, String schema, String username, String password) {
		_setPath(_buildPath(address,schema,username,password));
	}
	private String _buildPath(String address, String schemaName, String username, String password) {
		return "jdbc:mysql://"+address+"/"+schemaName+
				"?user="+username+"&password="+password +
				"&useUnicode=true" + "&serverTimezone=UTC" +
				"&useLegacyDatetimeCode=false" +
				"&useJDBCCompliantTimezoneShift=true";
	}

	private void _setPath(String path) { this.path = path; }
	Log logger = Log.getInstance();
	
	// CONNECTION ==================================================================================================================================
	protected Connection getConn() {
		logger.info(IView.translateLog("CONN"));
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(path);
			logger.info(IView.translateLog("SUX_CONN"));
		} catch(Exception e) {
			logger.error(IView.translateLog("ERR_CONN"),e);
		}
		return conn;
	}

	// TODO implement it ffs
	
	// FETCH, DELETE, INSERT, UPDATE ==================================================================================================================================
	public Map<String,String> fetchUser(String username, char[] password) {
		try {
			return row("SELECT * FROM users WHERE username = ? AND password = ?",username,password);
		} catch(Exception e) {
			logger.error(IView.translateLog("ERR_FETCH"), e);
		}
		return null; 
	}
	public List<Map<String,String>> fetchAll(SCHEMA_TOKEN table) {
		try {
			return rows("SELECT * FROM "+table);
		} catch(Exception e) {
			logger.error(IView.translateLog("ERR_FETCH"), e);
		}
		return null; 
	}
	public List<Map<String,String>> fetch(SCHEMA_TOKEN content, SCHEMA_TOKEN table, SCHEMA_TOKEN orderBy) {
		try {
			return rows("SELECT "+content+" FROM "+table+(
					orderBy!=null?" ORDER BY "+orderBy:"")
			);
		} catch(Exception e) {
			logger.error(IView.translateLog("ERR_FETCH"),e);
		}
		return new ArrayList<Map<String,String>>();
	}
	public boolean delete(SCHEMA_TOKEN table, SCHEMA_TOKEN key, String value) {
		try {
			if( execute(
					"DELETE FROM "+table+" WHERE "+key+" = ?",
					new String[] {value} )
			)
				// TRANSALTE THIS TODO
				logger.info("Deleted '"+key+":"+value+" from '"+table+"'");
			return true;
		} catch(Exception e) {
			logger.error(IView.translateLog("ERR_DEL"),e);
		}
		return false;
	}
	public boolean insert(SCHEMA_TOKEN table, SCHEMA_TOKEN column, String value) {
		try {
			if( execute(
					"INSERT INTO "+table+"("+column+") VALUES (?)",
					new String[] {value} )
			)
				logger.info("Inserted into '"+table+"' value "+value);
			return true;
		} catch(Exception e) {
			logger.error(IView.translateLog("ERR_INS"),e);
		}
		return false;
	}
	public boolean insert(SCHEMA_TOKEN table, Map<String,String> map) {
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
			logger.error(IView.translateLog("ERR_INS"),e);
		}
		return false;
	}
	public boolean update(SCHEMA_TOKEN table, SCHEMA_TOKEN column, String newValue, String title) {
		try {								
			if(execute("UPDATE "+table+" SET "+column+" = ? WHERE title = ?",
					new String[] {newValue.toString(),title} ))
				logger.info("Updated '"+table+"': '"+column+"' of "+title+" set to '"+newValue+"'");
			return true;
		} catch(Exception e) {
			logger.error(IView.translateLog("ERR_UPD"),e);
		}
		return false;
	}
}
