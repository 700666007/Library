package controller.web;

import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.entities.ProxyLibrary;
import model.entities.User;
import utils.Log;

//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.reflect.TypeToken;

//import model.entities.Book;
//import model.entities.ProxyLibrary;

public class Controller {
	
	static Log logger = Log.getInstance();
	
	static String validate(int level, String action) {
		
    	Map<String, Integer> rights 
    			= new HashMap<String,Integer>();
    	rights.put("newBook", 0/*TODO change to 1*/);
    	rights.put("newGenre", 1);
    	rights.put("delGenre", 1);
    	rights.put("setGenre", 1);
    	rights.put("changelang", 0);
    	rights.put("login", 0);
    	rights.put("logout", 0);
    	rights.put("",0);

    	if(action == null)
    		action = "";
    	else if(action!="" && !rights.containsKey(action)) 
    		action = "404";
    	else if(level < rights.get(action))
    		action = "403";
    	logger.data("Action: "+action);
    	return action;
	}
	static User login(String username, String password, ProxyLibrary library) {
		User u = null;
		char[] pwd = password.toCharArray();
		try {
			if((u = library.getUser(username,pwd)) == null)
				throw new NullPointerException();
		} catch(Exception e) {
			logger.info("Invalid credentials.");
			return u;
		}
		logger.info("Successfully logged in.");
		return u;
	}
	static int setUserLvl(HttpServletRequest req) {
		int level = 0;
		try {
			level = Integer.parseInt(req.getSession()
					.getAttribute("level").toString());
		} catch(Exception e) {
			level = 0;
		}
		logger.data("User lvl: "+level);
		return level;
	}
//	static JsonArray jsonResponse(ProxyLibrary library) {
//		
//		Gson gson = new Gson();
//		List<Book> booksList = library.getBooksList();
//		JsonElement jsonBooks = gson.toJsonTree(
//				booksList, new TypeToken<List<Book>>() {}.getType()
//				);
//		List<String> genresList = library.getGenresList();
//		JsonElement jsonGenresList = gson.toJsonTree(
//				genresList, new TypeToken<List<String>>() {}.getType()
//				);
//		List<String> titlesList = library.getTitlesList();
//		JsonElement jsonTitlesList = gson.toJsonTree(
//				titlesList, new TypeToken<List<String>>() {}.getType()
//				);
//		JsonArray jsonArr = jsonBooks.getAsJsonArray();
//		jsonArr.add(jsonTitlesList.getAsJsonArray());
//		jsonArr.add(jsonGenresList.getAsJsonArray());
//		return jsonArr;
//	}
}
