package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.entities.Book;
import model.entities.ProxyLibrary;
import utils.Factory;
import utils.InOut;
import utils.Log;

public class MainTester {

	public static void main(String[] args) {
		
		Log logger = Log.getInstance(true,"C:\\Users\\LoneRaven\\Desktop\\logger.txt");
		ProxyLibrary library = Factory.makeLibrary("localhost","library","root","toor");
		Scanner kbd = Factory.makeKbd();
		
		int cmd = InOut.selectOption(new String[] {
			"Get books' list", "Add new book", "Add new genre",
			"Delete genre", "Change book's genre" }, kbd);
		
		switch(cmd) {
			case 1:
				List<Book> list = library.getBooksList();
				for(Book book : list)
					logger.data(book.toString());
				break;
			case 2:
				Map<String,String> bmap = new HashMap<>();
				bmap.put("title" , InOut.getStr("Creating new book:\nInsert title:", kbd));
				bmap.put("author", InOut.getStr("Insert author:", kbd));
				bmap.put("genre" , InOut.getStr("Insert genre:", kbd));
				bmap.put("path"  , InOut.getStr("Insert path:", kbd));
				library.newBook(Factory.makeBook(bmap));
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
	}

}
