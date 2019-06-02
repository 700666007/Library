package model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dbms.IDatabase;
import model.dbms.impl.MySqlDb;
import utils.Factory;
import utils.Log;
import view.IView;

public class LibraryDaoImpl implements LibraryDao {

	private MySqlDb db = null;
	public LibraryDaoImpl(String... params) {
		db = (MySqlDb) IDatabase.make(params[0], params[1], params[2], params[3], params[4]);
	}

	Log logger = Log.getInstance();

	private enum SCHEMA_TOKEN { BOOKS, GENRES, USERS, VAL, TITLE, GENRE }
	public String tokens(SCHEMA_TOKEN token) {
		Map<SCHEMA_TOKEN,String> ris = new HashMap<>();
		ris.put(SCHEMA_TOKEN.BOOKS, "books");
		ris.put(SCHEMA_TOKEN.GENRES, "genres");
		ris.put(SCHEMA_TOKEN.USERS, "users");
		ris.put(SCHEMA_TOKEN.VAL, "val");
		ris.put(SCHEMA_TOKEN.TITLE, "title");
		ris.put(SCHEMA_TOKEN.GENRE, "genre");
		return ris.get(token);
	}
	
	@Override
	public List<Book> getBooksList() {
		List<Book> ris = new ArrayList<>();
		logger.info(IView.translateLog("FETCH_B"));
		for(Map<String,String> bmap : db.fetchAll(tokens(SCHEMA_TOKEN.BOOKS)) ) {
			Book book = Factory.makeBook(bmap);
			if(book!=null)
				ris.add(book);
		}
		return ris;
	}
	@Override
	public User getUser(String username, char[] password) {
		User ris = null;
		logger.info(IView.translateLog("FETCH_U"));
		Map<String,String> row = db.fetchUser(username,password);
		if(row != null && !row.isEmpty())
			ris = Factory.makeUser(row);
		return ris;
	}
	@Override
	public List<String> getGenresList() {
		List<String> ris = new ArrayList<>();
		logger.info(IView.translateLog("FETCH_G"));
		for(Map<String,String> gmap :
					db.fetch(tokens(SCHEMA_TOKEN.VAL),
							 tokens(SCHEMA_TOKEN.GENRES),
							 tokens(SCHEMA_TOKEN.VAL))
		)
			ris.add(gmap.get("val"));
		System.out.println(ris.get(0));
		return ris;
	}
	@Override
	public List<String> getTitlesList() {
		List<String> ris = new ArrayList<>();
		logger.info(IView.translateLog("FETCH_T"));
		for(Map<String,String> gmap :
					db.fetch(tokens(SCHEMA_TOKEN.TITLE),
							 tokens(SCHEMA_TOKEN.BOOKS),
							 tokens(SCHEMA_TOKEN.TITLE))
		)
			ris.add(gmap.get("title"));
		return ris;
	}

	@Override
	public boolean newBook(Book b) {
		return db.insert(tokens(SCHEMA_TOKEN.BOOKS), b.toMap());
	}
	@Override
	public boolean newGenre(String gen) {
		return db.insert(tokens(SCHEMA_TOKEN.GENRES),tokens(SCHEMA_TOKEN.VAL),gen);
	}
	@Override
	public boolean delGenre(String gen) {
		return db.delete(tokens(SCHEMA_TOKEN.GENRES),tokens(SCHEMA_TOKEN.VAL),gen);
	}
	@Override
	public boolean changeGenre(String tit, String gen) {
		return db.update(tokens(SCHEMA_TOKEN.BOOKS), tokens(SCHEMA_TOKEN.GENRE), gen, tit);
	}
}
