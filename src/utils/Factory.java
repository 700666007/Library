package utils;

import java.util.Map;

import dbms.MySqlDb;
import entities.Book;
import entities.LibraryDao;
import entities.LibraryDaoImpl;

public abstract class Factory {
	
	public static MySqlDb makeDb(String dbAddress, String schemaName, String username, String password) {
		MySqlDb mysqldb = new MySqlDb(dbAddress, schemaName, username, password);
		InOut.print("New MySQLDB created.");
		return mysqldb;
	}
	public static Book makeBook(Map<String,String> bmap) {
		Book book = new Book(bmap.get("title"), bmap.get("author"),
							 bmap.get("genre"), bmap.get("path"));
		InOut.print("New Book created.");
		return book;
	}
	public static LibraryDao makeLibrary() {
		LibraryDao library = new LibraryDaoImpl("localhost", "library", "root", "toor");
		InOut.print("New LibraryDaoImpl created.");
		return library;
	}
}
