package model.entities;

import java.util.List;

public interface LibraryDao {

	User getUser(String username, char[] password);
	List<Book> getBooksList();
	List<String> getGenresList();
	List<String> getTitlesList();
	boolean newBook(Book b);
	boolean newGenre(String g);
	boolean delGenre(String g);
	boolean changeGenre(String t, String g);
}
