package entities;

import java.util.List;

public interface LibraryDao {
	
	List<Book> getBooksList();
//	List<Book> getBooksList(String str);
	List<String> getGenresList();
	List<String> getTitlesList();
	boolean newBook(Book b);
	boolean newGenre(String g);
	boolean delGenre(String g);
	boolean setGenre(String t, String g);
}
