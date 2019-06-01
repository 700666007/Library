package view;

import java.io.File;
import java.util.Scanner;

import model.entities.Book;

public class ViewHTML implements IView {
	
	private String path;
	private IView view;
	
	public ViewHTML(String path, IView view) {
		this.path = path;
		this.view = view;
	}
	
	@Override
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

	@Override
	public String render(Book b) {
		String html = "<tr style='text-align: center'>";
		for(String key : new String[] {"title","author","genre","path"})
			html += "<td>"+b.toMap().get(key)+"</td>";
		return html+"</tr>";
	}
}
