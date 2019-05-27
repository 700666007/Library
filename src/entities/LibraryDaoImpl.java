package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dbms.IDatabase;
import dbms.MySqlDb;
import utils.Factory;

public class LibraryDaoImpl implements LibraryDao {
	
	MySqlDb db = null;
	public LibraryDaoImpl(String address, String schema, String username, String password) {
		db = (MySqlDb) IDatabase.make(address, schema, username, password);
	}
	
	@Override
	public List<Book> getBooksList() {
		List<Book> ris = new ArrayList<>();
		try {
			for(Map<String,String> bmap : db.fetch("books") ) {
				Book book = Factory.makeBook(bmap);
				if(book!=null)
					ris.add(book);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ris;
	}
	/*
	 * newbook
	 * newgenre
	 * delgenre
	 * setgenre
	 * getgenreslist
	 * gettitleslist
	 */
}
