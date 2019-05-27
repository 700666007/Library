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
	
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	public String getGenre() { return genre; }
	public String getPath() { return path; }
	
	public void setTitle(String title) { this.title = title; }
	public void setAuthor(String author) { this.author = author; }
	public void setGenre(String genre) { this.genre = genre; }
	public void setPath(String path) { this.path = path; }
}

