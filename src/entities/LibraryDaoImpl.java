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
			for(Map<String,String> bmap : db.fetch("*","books") ) {
				Book book = Factory.makeBook(bmap);
				if(book!=null)
					ris.add(book);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ris;
	}

	@Override
	public List<String> getGenresList() {
		List<String> ris = new ArrayList<>();
		try {
			for(Map<String,String> gmap : db.fetch("val","genres"))
				ris.add(gmap.get("val"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ris;
	}

	@Override
	public List<String> getTitlesList() {
		List<String> ris = new ArrayList<>();
		try {
			for(Map<String,String> gmap : db.fetch("val","genres"))
				ris.add(gmap.get("val"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ris;
	}

	@Override
	public boolean newBook(Book b) {
		return db.insert("books", b.toMap());
	}

	@Override
	public boolean newGenre(String gen) {
		return db.insert("genres","val",gen);
	}

	@Override
	public boolean delGenre(String gen) {
		return db.delete("genres","val",gen);
	}

	@Override
	public boolean setGenre(String tit, String gen) {
		return db.update("books", "genre", gen, tit);
	}
}
