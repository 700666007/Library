package utils;

import java.util.Map;
import java.util.Scanner;

import model.dbms.IDatabase;
import model.dbms.impl.MySqlDb;
import model.dbms.impl.OracleDb;
import model.entities.Book;
import model.entities.LibraryDao;
import model.entities.LibraryDaoImpl;
import model.entities.ProxyLibrary;
import model.entities.User;
import view.IView;
import view.ViewENG;
import view.ViewHTML;
import view.ViewITA;

public abstract class Factory {
	
	static Log logger = Log.getInstance();
	// TODO check access modifiers, useless methods, unnecessary parameters
	private static LibraryDao _libraryImpl(String... params) {
		LibraryDao library = new LibraryDaoImpl(params[0], params[1], params[2], params[3], params[4]);
		return library;
	}
	public static ProxyLibrary makeLibrary(String type, String dbAddress, String schemaName, String username, String password) {
		ProxyLibrary library =
				new ProxyLibrary((LibraryDaoImpl)_libraryImpl(type, dbAddress, schemaName, username, password));
		logger.info(IView.translateLog("FACTORY_LIB"));
		return library;
	}
	public static IDatabase makeDb(String... params) {
		IDatabase db = null;
		switch(params[0]) {
			case "oracle":
				db = new OracleDb();
				break;
			case "mysql":
			default:
				db = new MySqlDb(params[1], params[2], params[3], params[4]);
		}
		logger.info(IView.translateLog("FACTORY_DB"));
		return db;
	}
	public static Scanner makeKbd(IView view) {
		Scanner kbd = new Scanner(System.in);
		logger.debug(IView.translateLog("FACTORY_SCAN"));
		return kbd;
	}
	public static Book makeBook(Map<String,String> bmap) {
		Book book = new Book(bmap.get("title"),bmap.get("author"),
							 bmap.get("genre"),bmap.get("path"));
		return book;
	}
	public static User makeUser(Map<String,String> umap) {
		User user = new User(
				umap.get("username"),
				Integer.parseInt(umap.get("isAdmin")),
				umap.get("lang")
		);
		logger.debug(IView.translateLog("FACTORY_USER"));
		return user;
	}
	public static IView makeView(String path, String lang) {
		IView v = new ViewHTML(path,makeView(lang));
		logger.debug(IView.translateLog("FACTORY_VIEW"));
		return v;
	}
	public static IView makeView(String type) {
		IView ris = null;
		switch(type) {
			case "ENG": ris = new ViewENG(); break;
			case "ITA": ris = new ViewITA(); break;
			default:	ris = new ViewENG();
		}
		return ris;
	}
}
