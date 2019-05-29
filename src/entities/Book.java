package entities;

import java.util.LinkedHashMap;
import java.util.Map;

public class Book {
	
	private String title, author, genre, path;
	public Book(String title, String author, String genre, String path) {
		setTitle(title);
		setAuthor(author);
		setGenre(genre);
		setPath(path);
	}
	public Map<String,String> toMap() {
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("title", title);
		map.put("author", author);
		map.put("genre", genre);
		map.put("path", path);
		return map;
	}
	public String toString() {
		String res = "";
		Map<String,String> map = toMap();
		for(String key : map.keySet())
			res += key+": "+map.get(key); 
		return res;
	}
	private void setTitle(String title) { this.title = title; }
	private void setAuthor(String author) { this.author = author; }
	private void setGenre(String genre) { this.genre = genre; }
	private void setPath(String path) { this.path = path; }
}

