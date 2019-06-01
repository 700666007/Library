package utils;

import java.util.Scanner;

import view.IView;

public abstract class InOut {
	
	private static String marginPattern = ">>> ";
	private static String _addMargin(String s) { return marginPattern+s.replace("\n", "\n"+marginPattern); }
	
	static void print(String text) {
		System.out.println(_addMargin(text));
	}
	static void printException(String message, Exception e) {
		System.out.println(message + IView.translateLog("REASON"));
		e.printStackTrace();
	}
	public static String getStr(String message, Scanner kbd) { 
		System.out.print(_addMargin(message));
		return kbd.nextLine();
	}
	private static int _getInt(String message, Scanner kbd) {
		while(true)
			try { return Integer.parseInt(getStr(message, kbd)); }
			catch(NumberFormatException e) { IView.translateLog("NOT_A_NUMBER"); }
	}
	private static int _getInt1toInf(String message, Scanner kbd) {
		int input = 0;
		while((input = _getInt(message, kbd))<1)
			IView.translateLog("NOT_GT_THAN_0");
		return input;
	}
	public static int selectOption(String[] options, Scanner kbd) {
		String menu = IView.translateLog("OPT_SELECT");
		for(Integer i=1; i<=options.length; i++)
			menu+=i+") "+IView.translateLog(options[i-1])+"\n";
		int input = -1;
		while((input = _getInt1toInf(menu, kbd)) > options.length)
			IView.translateLog("OPT_NOT_VALID");
		return input;
	}
}
