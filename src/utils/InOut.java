package utils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class InOut {
	
	// PROPERTIES ==================================================================================================================================
	private static String marginPattern = ">>>";
	public static void setMarginPattern (String pattern) { marginPattern = pattern+" "; }
	private static String _addMargin(String s) { return marginPattern+s.replace("\n", "\n"+marginPattern); }
	
	// PRINT METHODS =======================================================================================================================
	public static void print(String text) {
		System.out.println(_addMargin(text));
	}
	public static void printException(String message, Exception e) {
		print(message+" Reason:");
		e.printStackTrace();
	}
	public static void printList(List<Integer> list) { print(MyUtils.renderList(list)); }
	public static void printMap(Map<String,String> map) { print(MyUtils.renderMap(map)); }
	
	// GET INTEGER =======================================================================================================================
	public static int getInt(String message, Scanner kbd) {
		while(true)
			try { return Integer.parseInt(getStr(message, kbd)); }
			catch(NumberFormatException e) { print("Non è un numero!"); }
	}
	public static int getInt0toInf(String message, Scanner kbd) {
		int input = -1;
		while((input = getInt(message, kbd))<0)
			print("Non è positivo!");
		return input;
	}
	public static int getInt1toInf(String message, Scanner kbd) {
		int input = 0;
		while((input = getInt(message, kbd))<1)
			print("Non è maggiore di 0!");
		return input;
	}
	 
	// GET STRING =======================================================================================================================
	public static String getStr(String message, Scanner kbd) { 
		System.out.print(_addMargin(message));
		return kbd.nextLine();
	}
	private static int _getValidLength(Scanner kbd) {
		int length = -1;
		while(length<0) {
			print("Lunghezza non valida!");
			length = getInt0toInf("Nuova lunghezza: ", kbd);
		}
		return length;
	}
	public static String getStrLenN(String message, int length, Scanner kbd) {
		if(length<0)
			length = _getValidLength(kbd);
		if(length==0)
			return "";
		String str = "";
		while(str.length()!=length) {
			print("Dev'essere lunga "+length+" caratteri!");
			str = getStr(message,kbd);
		}
		return str;
	}
	public static String getStrLenMaxN(String message, int length, Scanner kbd) {
		if(length<0)
			length = _getValidLength(kbd);
		if(length==0)
			return "";
		String str = "";
		while(str.length()>length) {
			print("Dev'essere lunga massimo "+length+" caratteri!");
			str = getStr(message,kbd);
		}
		return str;
	}

	// SELECT OPTION =======================================================================================================================
	public static boolean getYN(String message, Scanner kbd) {
		String res = null;
		while(!MyUtils.isStrInList(res = getStr(message,kbd).toLowerCase(),"y/n".split("/")))
			print("Scelta non valida.");
		return res.equals("y")?true:false;
	}
	public static int selectOption(String message, String[] options, Scanner kbd) {
		String menu = message+"\n";
		for(Integer i=1; i<=options.length; i++)
			menu+=i+") "+options[i-1]+"\n";
		int input = -1;
		while((input = getInt1toInf(menu, kbd)) > options.length)
			print("Scelta non valida!");
		return input;
	}
}
