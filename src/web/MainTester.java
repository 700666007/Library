package web;

import java.util.List;
import java.util.Scanner;

import entities.Book;
import entities.LibraryDao;
import utils.Factory;
import utils.InOut;
import utils.Log;

public class MainTester {

	public static void main(String[] args) {
		
		Log logger = Log.getInstance(true,true,"");
		LibraryDao library = Factory.makeLibrary();
		Scanner kbd = Factory.makeKbd();
		String[] options = {
			"Get books' list", "Add new book", "Add new genre",
			"Delete genre", "Change book's genre"
		};
		
		int cmd = InOut.selectOption(options, kbd);
		switch(cmd) {
			case 1:
				List<Book> list = library.getBooksList();
				for(Book book : list)
					logger.data(book.toString());
				break;
			case 2:
				library.newBook(
					Factory.makeBook(
						InOut.getStr("Creating new book:\nInsert title:", kbd),
						InOut.getStr("Insert author:", kbd),
						InOut.getStr("Insert genre:", kbd),
						InOut.getStr("Insert path:", kbd)
					)
				);
				break;
			case 3:
				library.newGenre(
					InOut.getStr("Insert new genre:", kbd)
				);
				break;
			case 4:
				library.delGenre(
					InOut.getStr("Insert new genre:", kbd)
				);
				break;
			case 5:
				library.changeGenre(
					InOut.getStr("Insert book's title:", kbd),
					InOut.getStr("Insert new genre:", kbd)
				);
				break;
		}
		// TODO keySet to entrySet Set<Map.Entry<T>> to iterate
	}

}
