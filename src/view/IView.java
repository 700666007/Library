package view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.entities.Book;
import model.entities.LibraryDao;

public interface IView {
	
	String render(Book b);
	String translate(String code);
	String fileContent(String path);
	
	default String render(List<Book> books) {
		String ris = "";
		for(Book b : books)
			ris += render(b);
		return ris;
	}
	default String renderHeader(int level, HttpServletRequest req) {
		String replacement = level==0
					? fileContent("forms\\login")
					: "<div class='centered'><div class='centered inline'>"+
					  "Benvenuto "+req.getSession().getAttribute("username")+
					  "!</div>"+fileContent("forms\\logout")+
					  fileContent("forms\\changelangITA")+"</div>";
		return fileContent("header").replace("<!-- WELCOME_MESSAGE -->", replacement);
	}
	default String renderBody(int level, String body, LibraryDao library) {

		List<Book> booksList = library.getBooksList();
		
		if(level==1) {
			List<String> values = library.getGenresList();
			String html = "";
			for(String genre : values)
				html += "<option value='"+genre+"'>"+genre+"</option>";
			String form = fileContent("forms\\newBook").replace("<!-- GENRES -->", html);
			body = body.replace("<!-- ADD BOOK -->", form);
			
			if(!booksList.isEmpty()) {
				form = fileContent("forms\\formGenres").replace("<!-- GENRES -->", html);
				html = "";
				values = library.getTitlesList();
				for(String title : values)
					html += "<option value='"+title+"'>"+title+"</option>";
				body = body.replace("<!-- FORM GENRES -->",
									form.replace("<!-- TITLES -->", html));
			}
		} else
			body = body.replace("<!-- ADD BOOK -->", "")
					   .replace("<!-- FORM GENRES -->", "");
		
		if(booksList.isEmpty())
			return body.replace("<!-- TABLE -->", "");
		else {
			body = body.replace("<!-- SEARCH & ORDER -->", fileContent("forms\\searchAndOrder"));
			return body.replace("<!-- TABLE -->", "<div><table id='bookTable'><tr class='heads'>"+
					  							  "<th scope='col'>TITOLO</th><th scope='col'>AUTORE</th>"+
					  							  "<th scope='col'>GENERE</th><th scope='col'>PATH</th>"+
					   							  "</tr><tr><!-- TABLE_CONTENT --></tr></table></div>"
					   							  .replace("<!-- TABLE_CONTENT -->", render(booksList)));
		}
	}
}
