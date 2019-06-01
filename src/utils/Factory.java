package utils;

import java.util.Map;
import java.util.Scanner;

import model.dbms.IDatabase;
import model.dbms.MySqlDb;
import model.entities.Book;
import model.entities.LibraryDao;
import model.entities.LibraryDaoImpl;
import model.entities.ProxyLibrary;
import model.entities.User;
import view.IView;
import view.ViewENG;
import view.ViewHTML;

public abstract class Factory {
	
	static Log logger = Log.getInstance();
	// TODO check access modifiers
	private static LibraryDao _makeLibrary(String... params) {
		LibraryDao library = new LibraryDaoImpl(params[0], params[1], params[2], params[3]);
		return library;
	}
	public static ProxyLibrary makeLibrary(String dbAddress, String schemaName, String username, String password) {
		ProxyLibrary library =
				new ProxyLibrary((LibraryDaoImpl)_makeLibrary(dbAddress, schemaName, username, password));
		logger.info("Factory: New ProxyLibrary created.");
		return library;
	}
	public static IDatabase makeDb(String dbAddress, String schemaName, String username, String password) {
		MySqlDb mysqldb = new MySqlDb(dbAddress, schemaName, username, password);
		logger.info("Factory: New MySQLDB created.");
		return mysqldb;
	}
	
	public static Scanner makeKbd() {
		Scanner kbd = new Scanner(System.in);
		logger.debug("Factory: New Scanner created.");
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
		logger.debug("Factory: New User created.");
		return user;
	}
	public static IView makeView(String path) {
		IView v = new ViewHTML(path,new ViewENG());
		logger.debug("Factory: New View created.");
		return v;
	}
	// TODO ma tutte ste view servono??
//	public static IView makeView() {
//		return makeView("HTML");
//	}
//	public static IView makeView(String type) {
//		IView ris = null;
//		
//		switch(type) {
//			case "HTML":
//				ris = new ViewHTML("");
//				break;
//			default:
//				ris = new ViewENG();
//		}
//		
//		return ris;
//	}
}
