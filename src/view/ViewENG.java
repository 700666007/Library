package view;

import java.util.HashMap;

public class ViewENG implements IView {

	@Override
	public String type() { return "ENG"; }
	
	HashMap<String,String> sentences = new HashMap<String,String>();

	public ViewENG() {
		
		/*  FRONT END CODES */
		/**/sentences.put("WELCOME", "Welcome ");
		/**/sentences.put("PUBLISH", "Publish!");
		/**/sentences.put("ADD_BUTT","Add");
		/**/sentences.put("PLCHLDR_NEW_GEN","new genre...");
		/**/sentences.put("DEL_BUTT","Delete");
		/**/sentences.put("EDIT_BUTT","Edit");
		/**/sentences.put("CHANGE_LANG", "Choose language");
		/**/sentences.put("KEY_TIT","Title");
		/**/sentences.put("KEY_AUT","Author");
		/**/sentences.put("KEY_GEN","Genre");
		/**/sentences.put("KEY_PATH", "Path");
		/**/sentences.put("OPEN","Open");
		/**/sentences.put("SEARCH", "Search...");
		/**/sentences.put("LOGINKO", "Invalid credentials.");
		/**/sentences.put("EMPTY_LIB","Your library is empty.");
		/**/sentences.put("ASK2LOG","Log in to add a new book.");
		
		/*  BACK END CODES */
		/*  Controller codes */
		/**/sentences.put("ACTION", 	"Action: ");
		/**/sentences.put("ERR_LOGIN", 	"Invalid credentials.");
		/**/sentences.put("SUX_LOGIN",	"Successfully logged in.");
		/**/sentences.put("USR_LVL", 	"User lvl: ");
		/**/sentences.put("LOGOUT", 	"Successfully logged out.");
		/**/sentences.put("USRNM", 		"Username: ");
		/**/sentences.put("PSWD", 		"Password: ");
		/**/sentences.put("PRINT_RESP", "Response printed.");
		/**/sentences.put("ERR_CMD", 	"Command not executed.");
		/**/
		/*  Database codes */
		/**/sentences.put("ROW", 			"Database.row(): ");
		/**/sentences.put("CONN", 			"Connecting to database...");
		/**/sentences.put("SUX_CONN", 		"Connection established!");
		/**/sentences.put("ERR_CONN", 		"Couldn't connect to database.");
		/**/sentences.put("ERR_FETCH", 		"Operation (FETCH) failed.");
		/**/sentences.put("SUX_DEL", 		"Deleted '");
		/**/sentences.put("ERR_DEL", 		"Operation (DELETE) failed.");
		/**/sentences.put("SUX_INS", 		"Inserted into '");
		/**/sentences.put("ERR_INS", 		"Operation (INSERT) failed.");
		/**/sentences.put("SUX_UPD", 		"Updated '");
		/**/sentences.put("ERR_UPD", 		"Operation (UPDATE) failed.");
		/**/sentences.put("TOK_VAL", 		"' value ");
		/**/sentences.put("TOK_FROM", 		" from '");
		/**/sentences.put("TOK_OF", 		"' of ");
		/**/sentences.put("TOK_SET", 		" set to '");
		/**/sentences.put("ERR_FETCH_USER", "Operation (FETCH_USER) failed.");
		/**/
		/*  Factory codes */
		/**/sentences.put("FACTORY_LIB",  "Factory: New ProxyLibrary created.");
		/**/sentences.put("FACTORY_DB",   "Factory: New MySQLDB created.");
		/**/sentences.put("FACTORY_SCAN", "Factory: New Scanner created.");
		/**/sentences.put("FACTORY_USER", "Factory: New User created.");
		/**/sentences.put("FACTORY_VIEW", "Factory: New View created.");
		/**/
		/*  File codes */
		/**/sentences.put("FILE_EXISTS", 	  " already exists.");
		/**/sentences.put("FILE_CREATED", 	  " successfully created.");
		/**/sentences.put("FILE_NOT_CREATED", " cannot be created.");
		/**/sentences.put("ERR_FILE_CREATION", 	"File creation failed.");
		/**/sentences.put("ERR_FILE_WRITING", 	"Couldn't write to file..");
		/**/
		/*  Input codes */
		/**/sentences.put("NOT_A_NUMBER", 	"Not a number!");
		/**/sentences.put("NOT_GT_THAN_0", 	"Not greater than 0!");
		/**/sentences.put("INSERT_TITLE", 	"Insert title:");
		/**/sentences.put("INSERT_AUTHOR", 	"Insert author:");
		/**/sentences.put("INSERT_GENRE", 	"Insert genre:");
		/**/sentences.put("INSERT_PATH", 	"Insert path:");
		/**/sentences.put("NEW_GENRE", 		"Creating new genre:");
		/**/sentences.put("NEW_BOOK", 		"Creating new book:");
		/**/
		/*  Library codes */
		/**/sentences.put("FETCH_B", "Fetching books...");
		/**/sentences.put("FETCH_U", "Fetching user...");
		/**/sentences.put("FETCH_G", "Fetching genres...");
		/**/sentences.put("FETCH_T", "Fetching titles...");
		/**/
		/*  Option codes */
		/**/sentences.put("OPT_NOT_VALID", 		"Invalid option!");
		/**/sentences.put("OPT_SELECT", 		"Select option:\n");
		/**/sentences.put("OPT_BOOKS_LIST", 	"Get books' list");
		/**/sentences.put("OPT_NEW_BOOK", 		"Add new book");
		/**/sentences.put("OPT_NEW_GENRE", 		"Add new genre");
		/**/sentences.put("OPT_DEL_GENRE", 		"Delete genre");
		/**/sentences.put("OPT_UPD_BOOK_GENRE", "Change book's genre");
		/**/
		/**/sentences.put("REASON", 			" Reason:");
		/**/sentences.put("", "");
	}

	@Override
	public String translate(String code) {
		return sentences.containsKey(code) ? sentences.get(code) : code;
	}
}
