package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dbms.IDatabase;
import dbms.MySqlDb;
import dbms.MySqlDb.Table;
import utils.Factory;
import utils.Log;

public class LibraryDaoImpl implements LibraryDao {

	private MySqlDb db = null;
	public LibraryDaoImpl(String address, String schema, String username, String password) {
		db = (MySqlDb) IDatabase.make(address, schema, username, password);
	}

	Log logger = Log.getInstance();

	@Override
	public List<Book> getBooksList() {
		List<Book> ris = new ArrayList<>();
		logger.info("Fetching books...");
		for(Map<String,String> bmap : db.fetch("*",Table.BOOKS) ) {
			Book book = Factory.makeBook(bmap);
			if(book!=null)
				ris.add(book);
		}
		return ris;
	}

	@Override
	public List<String> getGenresList() {
		List<String> ris = new ArrayList<>();
		logger.info("Fetching genres...");
		for(Map<String,String> gmap : db.fetch("val",Table.GENRES))
			ris.add(gmap.get("val"));
		return ris;
	}

	@Override
	public List<String> getTitlesList() {
		List<String> ris = new ArrayList<>();
		logger.info("Fetching titles...");
		for(Map<String,String> gmap : db.fetch("val",Table.GENRES))
			ris.add(gmap.get("val"));
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
	public boolean changeGenre(String tit, String gen) {
		return db.update("books", "genre", gen, tit);
	}
}
