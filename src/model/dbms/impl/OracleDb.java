package model.dbms.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import model.dbms.Database;
import model.dbms.IDatabase;
import model.entities.LibraryDaoImpl.SCHEMA_TOKEN;

public class OracleDb extends Database implements IDatabase, IDBActions  {

	@Override
	public Map<String, String> fetchUser(String username, char[] password) {
		return null;
	}

	@Override
	public List<Map<String, String>> fetchAll(SCHEMA_TOKEN table) {
		return null;
	}

	@Override
	public List<Map<String, String>> fetch(SCHEMA_TOKEN content, SCHEMA_TOKEN table, SCHEMA_TOKEN orderBy) {
		return null;
	}

	@Override
	public boolean delete(SCHEMA_TOKEN table, SCHEMA_TOKEN key, String value) {
		return false;
	}

	@Override
	public boolean insert(SCHEMA_TOKEN table, SCHEMA_TOKEN column, String value) {
		return false;
	}

	@Override
	public boolean insert(SCHEMA_TOKEN table, Map<String, String> map) {
		return false;
	}

	@Override
	public boolean update(SCHEMA_TOKEN table, SCHEMA_TOKEN column, String newValue, String title) {
		return false;
	}

	@Override
	protected Connection getConn() {
		return null;
	}

}
