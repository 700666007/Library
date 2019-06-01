package model.entities;

import java.util.List;

public class ProxyLibrary implements LibraryDao {

	private LibraryDaoImpl real;
	public ProxyLibrary(LibraryDaoImpl real) { this.real = real; }
	
	@Override
	public List<Book> getBooksList() { return real.getBooksList(); }
	@Override
	public List<String> getGenresList() { return real.getGenresList(); }
	@Override
	public List<String> getTitlesList() { return real.getTitlesList(); }
	@Override
	public User getUser(String username, char[] password) { return real.getUser(username,password); }
	
	@Override
	public boolean newBook(Book b) { return real.newBook(b); }
	@Override
	public boolean newGenre(String g) { return real.newGenre(g); }
	@Override
	public boolean delGenre(String g) { return real.delGenre(g); }
	@Override
	public boolean changeGenre(String t, String g) { return real.changeGenre(t,g); }
}
