package utils;

import java.util.Map;
import java.util.Scanner;

import dbms.IDatabase;
import dbms.MySqlDb;
import entities.Book;
import entities.LibraryDao;
import entities.LibraryDaoImpl;
import entities.ProxyLibrary;

public abstract class Factory {
	
	static Log logger = Log.getInstance();
	
	public static IDatabase makeDb(String dbAddress, String schemaName, String username, String password) {
		MySqlDb mysqldb = new MySqlDb(dbAddress, schemaName, username, password);
		logger.info("New MySQLDB created.");
		return mysqldb;
	}
	public static Book makeBook(Map<String,String> bmap) {
		Book book = new Book(bmap.get("title"),bmap.get("author"),
							 bmap.get("genre"),bmap.get("path"));
		logger.info("New Book created.");
		return book;
	}
	public static Book makeBook(String title, String author, String genre, String path) {
		Book book = new Book(title,author,genre,path);
		logger.info("New Book created.");
		return book;
	}
	private static LibraryDao _makeLibrary() {
		LibraryDao library = new LibraryDaoImpl("localhost", "library", "root", "toor");
		return library;
	}
	public static ProxyLibrary makeLibrary() {
		ProxyLibrary library =
				new ProxyLibrary((LibraryDaoImpl)_makeLibrary());
		logger.info("New CachedLibrary created.");
		return library;
	}
	public static Scanner makeKbd() {
		Scanner kbd = new Scanner(System.in);
		logger.info("New Scanner created.");
		return kbd;
	}
}
