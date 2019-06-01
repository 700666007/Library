package model.dbms;

import java.util.List;
import java.util.Map;

import utils.Factory;

public interface IDatabase {

	static IDatabase make(String... params) {
		return Factory.makeDb(params[0], params[1], params[2], params[3]);
	}
	Map<String,String> row(String table, String username, char[] password) throws Exception;
	List<Map<String,String>> rows(String sql) throws Exception;
	List<Map<String,String>> rows(String sql, String[] parameters) throws Exception;
	boolean execute(String sql) throws Exception;
	boolean execute(String sql, String[] parameters) throws Exception;
}
