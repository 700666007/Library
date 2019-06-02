package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Log 
{
	private static Log instance;
	private static String filePath;
	private static boolean debug;
	private Log() { }
	
	public synchronized static Log getInstance() {
		return Log.getInstance(false,filePath);
	}
	public synchronized static Log getInstance(
			boolean shouldDebug, String filepaf)
	{
		if(instance == null) {
			instance = new Log();
			debug = shouldDebug;
			if(filepaf == null  ||
			   !MyUtils.createFile(
					filePath = filepaf)
			) 
				throw new NullPointerException();
		}
		return instance;
	}
	
	private String _now() {
		return LocalDateTime.now().format(
					DateTimeFormatter.ofPattern(
					  "yyyy-MM-dd HH:mm:ss")
		);
	}
	private boolean _logEvent(String type, String message,
			Exception e)
	{
		boolean isError = type.equals("ERROR");
		String str =  _now() + " ["+type+"] "+message;
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
	public boolean data(String key, String value) {
		if(debug)
			return _logEvent("DATA",key+value,null);
		return false;
	}
}