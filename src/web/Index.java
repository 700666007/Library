package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import dbms.LibraryDao;
import entities.Book;
import utils.Factory;
import utils.Log;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Home")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Log logger;
	private LibraryDao library;
	
    public Index()  {
    	logger = Log.getInstance();
    	library = Factory.makeLibrary();
    	logger.info("APPLICATION STARTED");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		logger.info("Incoming GET request...");
		Gson gson = new Gson();
		
		List<Book> booksList = library.getBooksList();
		JsonElement jsonBooks = gson.toJsonTree(booksList/*, new TypeToken<List<Book>>() {}.getType()*/);
		
		List<String> genresList = library.getGenresList();
		JsonElement jsonGenresList = gson.toJsonTree(genresList/*, new TypeToken<List<String>>() {}.getType()*/);
		
		List<String> titlesList = library.getTitlesList();
		JsonElement jsonTitlesList = gson.toJsonTree(titlesList/*, new TypeToken<List<String>>() {}.getType()*/);
		
		JsonArray jsonArr = jsonBooks.getAsJsonArray();
		jsonArr.add(jsonTitlesList.getAsJsonArray());
		jsonArr.add(jsonGenresList.getAsJsonArray());

		logger.info("Printing response...");
		response.setContentType("application/json");
		response.getWriter().print(jsonArr);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		logger.info("Incoming POST request...");
		Map<String,String> pmap = new HashMap<>();
		for(String key : request.getParameterMap().keySet())
			pmap.put(key, request.getParameter(key));
		String action = pmap.remove("action");
		if(action != null)
			switch(action) {
				case "newBook" :
					library.newBook(Factory.makeBook(pmap));
					break;
				case "newGenre":
					library.newGenre(pmap.get("val"));
					break;
				case "delGenre":	
					library.delGenre(pmap.get("val"));
					break;
				case "setGenre":	
					library.changeGenre(pmap.get("tit"),pmap.get("gen"));
					break;
			}
	}
}
