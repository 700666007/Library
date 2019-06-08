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
import view.IView;

public class MainTester {

	public static void main(String[] args) {

		String type = "ITA";
		IView view = Factory.makeView(type);
		Scanner kbd = Factory.makeKbd();
		Log logger = Log.getInstance(
			true,"yourPath\\logger.txt"
		);
		ProxyLibrary library = Factory.makeLibrary("mysql","localhost","library","root","toor");
		int cmd = InOut.selectOption(new String[] {
				"OPT_BOOKS_LIST", "OPT_NEW_BOOK", "OPT_NEW_GENRE",
				"OPT_DEL_GENRE", "OPT_UPD_BOOK_GENRE" }, kbd);
		switch(cmd) {
			case 1:
				List<Book> list = library.getBooksList();
				for(Book book : list)
					logger.data("",book.map2Str());
				break;
			case 2:
				Map<String,String> bmap = new HashMap<>();
				logger.info(IView.translateLang("NEW_BOOK",view.type()));
				bmap.put("title" , InOut.getStr(IView.translateLang("INSERT_TITLE",view.type()), kbd));
				bmap.put("author", InOut.getStr(IView.translateLang("INSERT_AUTHOR",view.type()), kbd));
				bmap.put("genre" , InOut.getStr(IView.translateLang("INSERT_GENRE",view.type()), kbd));
				bmap.put("path"  , InOut.getStr(IView.translateLang("INSERT_PATH",view.type()), kbd));
				library.newBook(Factory.makeBook(bmap));
				break;
			case 3:
				library.newGenre(
					InOut.getStr(IView.translateLang("NEW_GENRE",view.type()), kbd)
				);
				break;
			case 4:
				library.delGenre(
					InOut.getStr(IView.translateLang("NEW_GENRE",view.type()), kbd)
				);
				break;
			case 5:
				library.changeGenre(
					InOut.getStr(IView.translateLang("INSERT_TITLE",view.type()), kbd),
					InOut.getStr(IView.translateLang("NEW_GENRE",view.type()), kbd)
				);
				break;
		}
	}

}
