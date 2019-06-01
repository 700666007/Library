package utils;

import java.time.LocalDateTime;

public class Log 
{
	private static Log instance;
	private static String filePath;
	private static boolean debug;
	private Log() { }
	
	public synchronized static Log getInstance() {
		return Log.getInstance(false,filePath);
	}
	public synchronized static Log getInstance(boolean shouldDebug, String filepaf) {
		if(instance == null) {
			instance = new Log();
			debug = shouldDebug;
			if(filepaf != null) {
				if(!MyUtils.createLogFile(filePath = filepaf))
					System.exit(-1);
			}
			else
				throw new NullPointerException();
		}
		return instance;
	}
	
	private boolean _logEvent(String type, String message, Exception e) {
		boolean isError = type.equals("ERROR");
		String str =  LocalDateTime.now()+
					  " ["+type+"] "+message+
					  (isError?(" Reason: \n"+e):"");
		if(!isError)
			InOut.print(str);
		else
			InOut.printException(str, e);
		
		MyUtils.write(filePath, str);
		return true;
	}
	
	public boolean info(String message) {
		return _logEvent("INFO",message,null);
	}
	public boolean warn(String message) {
		return _logEvent("WARN",message,null);
	}
	public boolean error(String message, Exception e) {
		return _logEvent("ERROR",message,e);
	}
	public boolean debug(String message) {
		if(debug)
			return _logEvent("DEBUG",message,null);
		return false;
	}
	public boolean data(String message) {
		if(debug)
			return _logEvent("DATA",message,null);
		return false;
	}
}