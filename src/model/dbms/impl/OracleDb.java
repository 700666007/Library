package model.dbms.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import model.dbms.Database;
import model.dbms.IDatabase;

@SuppressWarnings("unused")
public class OracleDb extends Database implements IDatabase, IDBActions  {

	@Override
	public Map<String, String> fetchUser(String username, char[] password) {
		return null;
	}

	@Override
	public List<Map<String, String>> fetchAll(String table) {
		return null;
	}

	@Override
	public List<Map<String, String>> fetch(String content, String table, String orderBy) {
		return null;
	}

	@Override
	public boolean delete(String table, String key, String value) {
		return false;
	}

	@Override
	public boolean insert(String table, String column, String value) {
		return false;
	}

	@Override
	public boolean insert(String table, Map<String, String> map) {
		return false;
	}

	@Override
	public boolean update(String table, String column, String newValue, String title) {
		return false;
	}

	@Override
	protected Connection getConn() {
		return null;
	}

}
