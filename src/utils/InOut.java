package utils;

import java.util.Scanner;

public abstract class InOut {
	
//	// PROPERTIES ==================================================================================================================================
	private static String marginPattern = ">>>";
	private static String _addMargin(String s) { return marginPattern+s.replace("\n", "\n"+marginPattern); }
	
	// PRINT METHODS =======================================================================================================================
	static void print(String text) {
		System.out.println(_addMargin(text));
	}
	static void printException(String message, Exception e) {
		print(message+" Reason:");
		e.printStackTrace();
	}
	public static String getStr(String message, Scanner kbd) { 
		System.out.print(_addMargin(message));
		return kbd.nextLine();
	}
	private static int _getInt(String message, Scanner kbd) {
		while(true)
			try { return Integer.parseInt(getStr(message, kbd)); }
			catch(NumberFormatException e) { print("Not a number!"); }
	}
	private static int _getInt1toInf(String message, Scanner kbd) {
		int input = 0;
		while((input = _getInt(message, kbd))<1)
			print("Not greater than 0!");
		return input;
	}
	public static int selectOption(String[] options, Scanner kbd) {
		String menu = "Select option:\n";
		for(Integer i=1; i<=options.length; i++)
			menu+=i+") "+options[i-1]+"\n";
		int input = -1;
		while((input = _getInt1toInf(menu, kbd)) > options.length)
			print("Invalid option!");
		return input;
	}
}
