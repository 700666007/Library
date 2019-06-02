package model.dbms.impl;

import java.util.List;
import java.util.Map;

public interface IDBActions {
	Map<String,String> fetchUser(String username, char[] password);
	List<Map<String,String>> fetchAll(String table);
	List<Map<String,String>> fetch(	String content,
						String table, String orderBy);
	boolean delete(String table, String key, String value);
	boolean insert(String table, String column, String value);
	boolean insert(String table, Map<String,String> map);
	boolean update(String table, String column, 
					String newValue, String title);
}
