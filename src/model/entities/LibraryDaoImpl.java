package model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.dbms.IDatabase;
import model.dbms.MySqlDb;
import model.dbms.MySqlDb.Table;
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
		for(Map<String,String> bmap : db.fetch("*",Table.BOOKS,null) ) {
			Book book = Factory.makeBook(bmap);
			if(book!=null)
				ris.add(book);
		}
		return ris;
	}
	@Override
	public User getUser(String username, char[] password) {
		User ris = null;
		logger.info("Fetching user...");
		Map<String,String> row = db.fetchUser(username,password);
		if(row != null && !row.isEmpty())
			ris = Factory.makeUser(row);
		return ris;
	}
	@Override
	public List<String> getGenresList() {
		List<String> ris = new ArrayList<>();
		logger.info("Fetching genres...");
		for(Map<String,String> gmap : db.fetch("val",Table.GENRES,"val"))
			ris.add(gmap.get("val"));
		return ris;
	}
	@Override
	public List<String> getTitlesList() {
		List<String> ris = new ArrayList<>();
		logger.info("Fetching titles...");
		for(Map<String,String> gmap : db.fetch("title",Table.BOOKS,"title"))
			ris.add(gmap.get("title"));
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
