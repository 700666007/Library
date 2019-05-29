package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachedLibrary implements LibraryDao {

	private LibraryDaoImpl real;
	private Map<String,List<Book>> cache = new HashMap<>();
	
	public CachedLibrary(LibraryDaoImpl real) {
		this.real = real;
	}
	
	@Override
	public List<Book> getBooksList() {
//		if(!cache.isEmpty())
//			return storedResult
		return real.getBooksList();
	}
	@Override
	public List<String> getGenresList() {
		return null;
	}
	@Override
	public List<String> getTitlesList() {
		return null;
	}
	@Override
	public boolean newBook(Book b) {
		return false;
	}
	@Override
	public boolean newGenre(String g) {
		return false;
	}
	@Override
	public boolean delGenre(String g) {
		return false;
	}
	@Override
	public boolean changeGenre(String t, String g) {
		return false;
	}
	
}
