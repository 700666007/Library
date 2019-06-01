package model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.dbms.IDatabase;
import model.dbms.MySqlDb;
import utils.Factory;
import utils.Log;
import view.IView;

public class LibraryDaoImpl implements LibraryDao {

	private MySqlDb db = null;
	public LibraryDaoImpl(String... params) {
		db = (MySqlDb) IDatabase.make(params[0], params[1], params[2], params[3]);
	}

	Log logger = Log.getInstance();

	public enum SCHEMA_TOKEN { BOOKS, GENRES, USERS, VAL, TITLE, GENRE }
	@Override
	public List<Book> getBooksList() {
		List<Book> ris = new ArrayList<>();
		logger.info(IView.translateLog("FETCH_B"));
		for(Map<String,String> bmap : db.fetchAll(SCHEMA_TOKEN.BOOKS) ) {
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
		for(Map<String,String> gmap : db.fetch(SCHEMA_TOKEN.VAL,SCHEMA_TOKEN.GENRES,SCHEMA_TOKEN.VAL))
			ris.add(gmap.get("val"));
		return ris;
	}
	@Override
	public List<String> getTitlesList() {
		List<String> ris = new ArrayList<>();
		logger.info(IView.translateLog("FETCH_T"));
		for(Map<String,String> gmap : db.fetch(SCHEMA_TOKEN.TITLE,SCHEMA_TOKEN.BOOKS,SCHEMA_TOKEN.TITLE))
			ris.add(gmap.get("title"));
		return ris;
	}

	@Override
	public boolean newBook(Book b) {
		return db.insert(SCHEMA_TOKEN.BOOKS, b.toMap());
	}
	@Override
	public boolean newGenre(String gen) {
		return db.insert(SCHEMA_TOKEN.GENRES,SCHEMA_TOKEN.VAL,gen);
	}
	@Override
	public boolean delGenre(String gen) {
		return db.delete(SCHEMA_TOKEN.GENRES,SCHEMA_TOKEN.VAL,gen);
	}
	@Override
	public boolean changeGenre(String tit, String gen) {
		return db.update(SCHEMA_TOKEN.BOOKS, SCHEMA_TOKEN.GENRE, gen, tit);
	}
}
