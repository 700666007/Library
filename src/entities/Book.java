package entities;

import java.util.LinkedHashMap;
import java.util.Map;

import utils.MyUtils;

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
	/*
	public Map<String,String> toMap() {
		Map<String,String> map = new LinkedHashMap<String,String>();
		for(Field f : this.getClass().getDeclaredFields())
			try {
				String[] str = f.toString().split(".");
				if(str.length!=0)
					map.put(str[str.length-1].split(":")[0], (String) f.get(this));
			} catch (IllegalArgumentException |
					 IllegalAccessException e) {
				e.printStackTrace();
			}
		return map;
	}
    */
	public String toString() {
		return MyUtils.renderMap(toMap());
	}
	private void setTitle(String title) { this.title = title; }
	private void setAuthor(String author) { this.author = author; }
	private void setGenre(String genre) { this.genre = genre; }
	private void setPath(String path) { this.path = path; }
}

