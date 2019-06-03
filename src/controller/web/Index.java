package controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.ProxyLibrary;
import model.entities.User;
import utils.Factory;
import utils.Log;
import utils.MyUtils;
import view.IView;
import view.ViewHTML;

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
	private ProxyLibrary library;
	private ViewHTML view;
	
    public Index()  {
    	logger = Log.getInstance(false,"yourPath\\log.txt");
    	logger.info("APPLICATION STARTED");    
    	view = Factory.makeView("yourPath\\Library\\WebContent\\templates","ENG");
    	library = Factory.makeLibrary("mysql","localhost","mylibrary","root","toor");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int level = Controller.setUserLvl(request);
		Map<String,String> pmap = MyUtils.request2map(request);
		String action = Controller.validate(level, pmap.remove("action"));
		
		String header = view.renderHeader(level,request);
		String body = view.fileContent("body");
		if(action!=null)
			switch(action) {
				// Status codes - HTTP client errors
				case"403":case"404":
									body = view.fileContent(action);		break;
				// admin's permissions
				case "newBook" :	library.newBook(Factory.makeBook(pmap));break;
				case "newGenre":	library.newGenre(pmap.get("val"));		break;
				case "delGenre":	library.delGenre(pmap.get("val"));		break;
				case "setGenre":	library.changeGenre(
										pmap.get("tit"),pmap.get("gen"));	break;
				// user's permissions
				case "changelang":
					header = view.setView(pmap.get("lang"),level,request);
					break;
				case "open":		Controller.open(pmap.get("path"));		break;
				case "logout":	
					request.getSession().invalidate();	
					level = 0;
					header = view.renderHeader(level,request);
					logger.info("LOGOUT");
					break;
				case "login"   :
					logger.data(IView.translateLog("USRNM"),pmap.get("username"));
					logger.data(IView.translateLog("PSWD"),pmap.get("password"));
					User user = Controller.login(
							pmap.get("username"),pmap.get("password"),library
					);
					if(user!=null) {
						request.getSession().setAttribute("username", user.name());
						request.getSession().setAttribute("level", user.isAdmin());
						request.getSession().setAttribute("lang", user.lang());
						level = 1;
						header = view.setView(user.lang(),level,request);
					} else
						header = view.loginKO(header);
					break;
				default:
					body = view.renderBody(level,body,library);
			}
		PrintWriter pw = response.getWriter();
		body = view.renderBody(level,body,library);
		pw.append(header+body+view.fileContent("footer"));
		logger.info("PRINT_RESP");
		
//		response.setContentType("application/json");
//		pw.print(Controller.jsonResponse(library));
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
