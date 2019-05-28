package dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import log.Log;
import utils.MyUtils;

public class MySqlDb extends Database implements IDatabase {
	
	// PROPERTIES ==================================================================================================================================
	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private String address;
	private String schema;
	private String path;
	private String username;
	private String password;
	
	// CONSTRUCTOR ==================================================================================================================================
	public MySqlDb(String address, String schema, String username, String password) {
		this.address = address;
		this.schema = schema;
		path = _buildPath(address,path,username,password);
		this.username = username;
		this.password = password;
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
	public String getAddress()  { return address;   }
	public String getSchema()   { return schema;    }
	public String getPath()     { return path;      }
	public String getUsername() { return username;  }
	public String getPassword() { return password;  }
	
	public void setAddress(String address)   { this.address = address;   }
	public void setSchema(String schema)     { this.schema = schema;     }
	public void setPath(String path)         { this.path = path;         }
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	
	Log logger = Log.getInstance();
	
	// CONNECTION ==================================================================================================================================
	protected Connection _getConn() {
		logger.info("Connecting to "+address+"...");
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(path);
		} catch(Exception e) {
			logger.error("Connection failed.",e);
		}
		logger.info("Connection established!");
		return conn;
	}

	// FETCH, DELETE, INSERT, UPDATE ==================================================================================================================================
	public List<Map<String,String>> fetch(String content, String table) {
		try {
			return rows("SELECT ? FROM ?", "content-table".split("-"));
		} catch(Exception e) {
			logger.error("Operation (FETCH) failed.",e);
		}
		return new ArrayList<Map<String,String>>();
	}
	public boolean delete(String table, String key, String value) {
		try {
			if(execute("DELETE FROM ? WHERE ? = '?'", "table-key-value".split("-")))
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
	public boolean update(String table, String column, String newValue, String id) {
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
