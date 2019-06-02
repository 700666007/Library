package view;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import model.entities.Book;
import model.entities.LibraryDao;
import utils.Factory;

public class ViewHTML implements IView {

	@Override
	public String type() { return "HTML"; }
	
	private String path;
	private IView view;
	public void setView(String type) {
		this.view = Factory.makeView(type);
	}
	
	public ViewHTML(String path, IView view) {
		this.path = path;
		this.view = view;
	}
	public String loginKO(String header) {
		header = header.replace("<!-- LOGINKO -->", fileContent("loginKO"));
		return header.replace("<!-- LOGINKO -->", translate("ERR_LOGIN"));
	}
	public String renderHeader(int level, HttpServletRequest req) {
		String replacement = level==0
					? fileContent("forms\\login")
					: "<div class='centered'><div class='centered inline'>"+
					  translate("WELCOME")+
					  req.getSession().getAttribute("username")+
					  "!</div>"+fileContent("forms\\logout")+"</div>";
		return fileContent("header").replace("<!-- WELCOME_MESSAGE -->", replacement)
									.replace("<!-- LANG_BUTT -->", fileContent("forms\\changelang"))
									.replace("<!-- LANG_BUTT -->", translate("CHANGE_LANG"));
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
		
		body = _translateFront(body, new String[] {"PUBLISH","KEY_TIT","KEY_AUT","KEY_PATH",
							"ADD_BUTT", "PLCHLDR_NEW_GEN", "DEL_BUTT", "EDIT_BUTT"}, "TOKEN");
		
		if(booksList.isEmpty())
			return body.replace("<!-- TABLE -->", level==1
													? fileContent("emptyLib")
													: fileContent("emptyLib")
													+ fileContent("ask2log"));
		else {
			body = body.replace("<!-- SEARCH & ORDER -->", fileContent("forms\\searchAndOrder"));
			body = _translateFront(body, new String[] {"SEARCH","KEY_TIT","KEY_AUT","KEY_GEN"}, "SandO");
			
			return body.replace("<!-- TABLE -->", "<div><table id='bookTable'><tr class='heads'>"+
					  							  "<th scope='col'>"+translate("KEY_TIT").toUpperCase()+
					  							  "</th><th scope='col'>"+translate("KEY_AUT").toUpperCase()+
					  							  "</th><th scope='col'>"+translate("KEY_GEN").toUpperCase()+
					  							  "</th><th scope='col'>"+translate("OPEN").toUpperCase()+
					  							  "</th></tr><!-- TABLE_CONTENT --></table></div>"
					   							  .replace("<!-- TABLE_CONTENT -->", render(booksList)));
		}
	}
	private String _translateFront(String body, String[] tokens, String toReplace) {
		for(int i=0; i<tokens.length; i++)
			body = body.replace("<!-- "+toReplace+i+" -->",
								translate(tokens[i]));
		return body;
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
		Map<String,String> bmap = b.toMap();
		for(String key : new String[] {"title","author","genre"})
			html += "<td>"+bmap.get(key)+"</td>";
		html += "<td>"+fileContent("forms\\open")
						.replace("<!-- PATH -->", bmap.get("path"))+
		"</td>";
		return html+"</tr>";
	}
	public String render(List<Book> books) {
		String ris = "";
		for(Book b : books)
			ris += render(b);
		return ris;
	}
}
