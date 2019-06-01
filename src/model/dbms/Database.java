package model.dbms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import utils.Log;
import utils.MyUtils;

abstract class Database implements IDatabase {

	protected abstract Connection getConn();
	Log logger = Log.getInstance();
	/// TODO logdaata
	@Override
	public Map<String, String> row(String query, String username, char[] password) throws Exception {
		Map<String,String> ris = new LinkedHashMap<String,String>();
		Connection conn = getConn();
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, String.valueOf(password));
		ResultSet rs = ps.executeQuery();
        if(rs.next())
	        for(int i=1; i<=rs.getMetaData().getColumnCount(); i++)
	        	ris.put (
	        		rs.getMetaData().getColumnLabel(i), 
	        		rs.getString(i) 
	        	);
		rs.close();
		conn.close();
		return ris;
	}
	
	@Override
	public List<Map<String, String>> rows(String sql) throws Exception {
		List<Map<String,String>> ris = new ArrayList<Map<String,String>>();
		Connection conn = getConn();
        ResultSet rs = conn.createStatement().executeQuery(sql);
		while(rs.next()) {
			Map<String,String> row = new LinkedHashMap<String,String>();
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i++)
		        	row.put (
		        		rs.getMetaData().getColumnLabel(i), 
		        		rs.getString(i) 
		        	);
			ris.add(row);
		}
		rs.close();
		conn.close();
		return ris;
	}
	
	@Override
	public List<Map<String, String>> rows(String sql, String[] parameters) throws Exception {
		List<Map<String,String>> ris = new ArrayList<Map<String,String>>();
        Connection conn = getConn();
        logger.debug(sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i=0; i<parameters.length; i++)
        	ps.setString(i+1,parameters[i]);
        logger.debug(ps.toString());
        ResultSet rs = ps.executeQuery();
        while(rs.next())
		{
			Map<String,String> row = new LinkedHashMap<String,String>();
			for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
		        	row.put (
		        		rs.getMetaData().getColumnLabel(i),
		        		rs.getString(i)
		        	);
			logger.data("Database.rows(): "+MyUtils.renderMap(row));
			ris.add(row);
		}
        rs.close();
        ps.close();
		conn.close();
		return ris;
	}
	
	@Override
	public boolean execute(String sql) throws Exception {
		Connection conn = getConn();
		conn.createStatement().execute(sql);
		conn.close();
		return true;
	}
	
	@Override
	public boolean execute(String sql, String[] parameters) throws Exception {
		Connection conn = getConn();
		PreparedStatement ps = conn.prepareStatement(sql);
        for(int i=0;i<parameters.length;i++)
        	ps.setString(i+1, parameters[i]);
        ps.execute();
        ps.close();
		conn.close();
		return true;
	}
}
