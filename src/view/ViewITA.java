package view;

import java.util.HashMap;

public class ViewITA implements IView {

	@Override
	public String type() { return "ITA"; }
	
	HashMap<String,String> sentences = new HashMap<String,String>();
	
	public ViewITA() {
		/*  FRONT END CODES */
		/**/sentences.put("WELCOME", "Benvenuto ");
		/**/sentences.put("PUBLISH", "Pubblica!");
		/**/sentences.put("ADD_BUTT","Aggiungi");
		/**/sentences.put("PLCHLDR_NEW_GEN","nuovo genere...");
		/**/sentences.put("DEL_BUTT","Elimina");
		/**/sentences.put("EDIT_BUTT","Modifica");
		/**/sentences.put("CHANGE_LANG", "Scegli lingua");
		/**/sentences.put("KEY_TIT","Titolo");
		/**/sentences.put("KEY_AUT","Autore");
		/**/sentences.put("KEY_GEN","Genere");
		/**/sentences.put("KEY_PATH", "Percorso");
		/**/sentences.put("OPEN","Apri");
		/**/sentences.put("SEARCH", "Cerca...");
		/**/sentences.put("ERR_LOGIN", "Credenziali errate.");
		/**/sentences.put("EMPTY_LIB","La tua libreria &egrave vuota.");
		/**/sentences.put("ASK2LOG","Effettua il login per aggiungere un nuovo libro.");
		
		/*  BACK END CODES */
		/*  Controller codes */
		/**/sentences.put("ACTION", 	"Azioni	: ");
		/**/sentences.put("SUX_LOGIN",	"Log in riuscito.");
		/**/sentences.put("USR_LVL", 	"User lvl: ");
		/**/sentences.put("LOGOUT", 	"Log out riuscito.");
		/**/sentences.put("USRNM", 		"Username: ");
		/**/sentences.put("PSWD", 		"Password: ");
		/**/sentences.put("PRINT_RESP", "Response inviata.");
		/**/sentences.put("ERR_CMD", 	"Comando non eseguito.");
		/**/
		/*  Database codes */
		/**/sentences.put("ROW", 			"Database.row(): ");
		/**/sentences.put("CONN", 			"Connessione al database...");
		/**/sentences.put("SUX_CONN", 		"Connessione stabilita!");
		/**/sentences.put("ERR_CONN", 		"Impossibile connettersi.");
		/**/sentences.put("ERR_FETCH", 		"Operazione (FETCH) fallita.");
		/**/sentences.put("SUX_DEL", 		"Eliminato '");
		/**/sentences.put("ERR_DEL", 		"Operazione (DELETE) fallita.");
		/**/sentences.put("SUX_INS", 		"Inserito in '");
		/**/sentences.put("ERR_INS", 		"Operazione (INSERT) fallita.");
		/**/sentences.put("SUX_UPD", 		"Aggiornato '");
		/**/sentences.put("ERR_UPD", 		"Operazione (UPDATE) fallita.");
		/**/sentences.put("TOK_VAL", 		"' valore ");
		/**/sentences.put("TOK_FROM", 		" da '");
		/**/sentences.put("TOK_OF", 		"' di ");
		/**/sentences.put("TOK_SET", 		" impostato a '");
		/**/sentences.put("ERR_FETCH_USER", "Operazione (FETCH_USER) fallita.");
		/**/
		/*  Factory codes */
		/**/sentences.put("FACTORY_LIB",  "Factory: Nuovo ProxyLibrary creato.");
		/**/sentences.put("FACTORY_DB",   "Factory: Nuovo MySQLDB creato.");
		/**/sentences.put("FACTORY_SCAN", "Factory: Nuovo Scanner creato.");
		/**/sentences.put("FACTORY_USER", "Factory: Nuovo User creato.");
		/**/sentences.put("FACTORY_VIEW", "Factory: Nuovo View creato.");
		/**/
		/*  File codes */
		/**/sentences.put("FILE_EXISTS", 	  " già esistente.");
		/**/sentences.put("FILE_CREATED", 	  " creato con successo.");
		/**/sentences.put("FILE_NOT_CREATED", " non può essere creato.");
		/**/sentences.put("ERR_FILE_CREATION", 	"Creazione file fallita.");
		/**/sentences.put("ERR_FILE_WRITING", 	"Impossibile scrivere sul file.");
		/**/
		/*  Input codes */
		/**/sentences.put("NOT_A_NUMBER", 	"Non è un numero!");
		/**/sentences.put("NOT_GT_THAN_0", 	"Non è maggiore di 0!");
		/**/sentences.put("INSERT_TITLE", 	"Inserire titolo:");
		/**/sentences.put("INSERT_AUTHOR", 	"Inserire autore:");
		/**/sentences.put("INSERT_GENRE", 	"Inserire genere:");
		/**/sentences.put("INSERT_PATH", 	"Inserire percorso:");
		/**/sentences.put("NEW_GENRE", 		"Creazione nuovo genere:");
		/**/sentences.put("NEW_BOOK", 		"Creazione nuovo libro:");
		/**/
		/*  Library codes */
		/**/sentences.put("FETCH_B", "Recupero libri...");
		/**/sentences.put("FETCH_U", "Recupero utente...");
		/**/sentences.put("FETCH_G", "Recupero generi...");
		/**/sentences.put("FETCH_T", "Recupero titoli...");
		/**/
		/*  Option codes */
		/**/sentences.put("OPT_NOT_VALID", 		"Scelta non valida!");
		/**/sentences.put("OPT_SELECT", 		"Seleziona:\n");
		/**/sentences.put("OPT_BOOKS_LIST", 	"Lista dei libri");
		/**/sentences.put("OPT_NEW_BOOK", 		"Nuovo libro");
		/**/sentences.put("OPT_NEW_GENRE", 		"Nuovo genere");
		/**/sentences.put("OPT_DEL_GENRE", 		"Elimina genere");
		/**/sentences.put("OPT_UPD_BOOK_GENRE", "Cambia genere di un libro");
		/**/
		/**/sentences.put("REASON", 			" Motivo:");
		/**/sentences.put("", "");
	}

	@Override
	public String translate(String code) {
		return sentences.containsKey(code) ? sentences.get(code) : code;
	}
}
