package model.dbms.impl;

import java.util.List;
import java.util.Map;

import model.entities.LibraryDaoImpl.SCHEMA_TOKEN;

public interface IDBActions {
	Map<String,String> fetchUser(String username, char[] password);
	List<Map<String,String>> fetchAll(SCHEMA_TOKEN table);
	List<Map<String,String>> fetch(	SCHEMA_TOKEN content,
						SCHEMA_TOKEN table, SCHEMA_TOKEN orderBy);
	boolean delete(SCHEMA_TOKEN table, SCHEMA_TOKEN key, String value);
	boolean insert(SCHEMA_TOKEN table, SCHEMA_TOKEN column, String value);
	boolean insert(SCHEMA_TOKEN table, Map<String,String> map);
	boolean update(SCHEMA_TOKEN table, SCHEMA_TOKEN column, 
					String newValue, String title);
}
