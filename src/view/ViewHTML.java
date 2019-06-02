package view;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import model.entities.Book;
import model.entities.LibraryDao;

public class ViewHTML implements IView {

	@Override
	public String type() { return "HTML"; }
	
	private String path;
	private IView view;
	
	public ViewHTML(String path, IView view) {
		this.path = path;
		this.view = view;
	}
	public String renderHeader(int level, HttpServletRequest req) {
		String replacement = level==0
					? fileContent("forms\\login")
					: "<div class='centered'><div class='centered inline'>"+
					  "Benvenuto "+req.getSession().getAttribute("username")+
					  "!</div>"+fileContent("forms\\logout")+
					  fileContent("forms\\changelangITA")+"</div>";
		return fileContent("header").replace("<!-- WELCOME_MESSAGE -->", replacement);
	}
	public String renderBody(int level, String body, LibraryDao library) {

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
	public String fileContent(String path) {
		String ris = "";
		Scanner rows = null;
		try {
			rows = new Scanner(new File(this.path+"//"+path+".html"));
			while(rows.hasNextLine())
				ris+=rows.nextLine()+"\n";
		} catch(Exception e) {
			ris = e.getMessage();
		} finally {
			try {
				rows.close();
			} catch(NullPointerException n) {
				n.printStackTrace();
			}
		}
		return ris;
	}
	@Override
	public String translate(String code) {
		return view.translate(code);
	}
	public String render(Book b) {
		String html = "<tr style='text-align: center'>";
		for(String key : new String[] {"title","author","genre","path"})
			html += "<td>"+b.toMap().get(key)+"</td>";
		return html+"</tr>";
	}
	public String render(List<Book> books) {
		String ris = "";
		for(Book b : books)
			ris += render(b);
		return ris;
	}
}
