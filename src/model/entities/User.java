package model.entities;

public class User {

	private String username, language;
	private int isAdmin;
	
	public User(String username, int isAdmin, String language) {
		this.username = username;
		this.isAdmin = isAdmin;
		this.language = language;
	}

	public String getUsername() { return username; }
	public int isAdmin() 	{ return isAdmin;  }
	public String getLanguage() { return language; }
	
	public void setUsername(String username) { this.username = username; }
	public void setLanguage(String language) { this.language = language; }
	public void setAdmin(int level) { isAdmin = level==1 ? 1 : 0; } 
}
