package dbms;

import java.util.List;
import java.util.Map;

import utils.Factory;

public interface IDatabase {
	
	static IDatabase make(String address, String schema, String username, String password) {
		return Factory.makeDb(address, schema, username, password);
	}
	Map<String,String> row(String table, int id) throws Exception;
	List<Map<String,String>> rows(String sql) throws Exception;
	List<Map<String,String>> rows(String sql, String[] parameters) throws Exception;
	boolean execute(String sql) throws Exception;
	boolean execute(String sql, String[] parameters) throws Exception;
}
