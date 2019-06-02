package controller.web;

import java.awt.Desktop;
import java.io.File;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.entities.ProxyLibrary;
import model.entities.User;
import utils.Log;
import view.IView;

//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.reflect.TypeToken;

//import model.entities.Book;
//import model.entities.ProxyLibrary;

class Controller {
	
	private static Log logger = Log.getInstance();
	
	static String validate(int level, String action) {
		
    	Map<String, Integer> rights 
    			= new HashMap<String,Integer>();
    	rights.put("newBook", 1);
    	rights.put("newGenre", 1);
    	rights.put("delGenre", 1);
    	rights.put("setGenre", 1);
    	rights.put("open", 0);
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
    	logger.data(IView.translateLog("ACTION"),action);
    	return action;
	}
	static User login(String username, String password, ProxyLibrary library) {
		User u = null;
		char[] pwd = password.toCharArray();
		try {
			if((u = library.getUser(username,pwd)) == null)
				throw new NullPointerException();
		} catch(Exception e) {
			logger.info(IView.translateLog("ERR_LOGIN"));
			return u;
		}
		logger.info(IView.translateLog("SUX_LOGIN"));
		return u;
	}
	static int setUserLvl(HttpServletRequest req) {
		Integer level = 0;
		try {
			level = Integer.parseInt(req.getSession()
					.getAttribute("level").toString());
		} catch(Exception e) {
			level = 0;
		}
		logger.data(IView.translateLog("USR_LVL"),level.toString());
		return level;
	}
	static boolean open(String path) {
		try {
			File f = new File(path);
			Desktop.getDesktop().open(f);
		} catch(Exception e) {
			logger.error("ERR_CMD", e);
			return false;
		}
		return true;
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
