package utils;

import java.time.LocalDateTime;

public class Log 
{
	private static Log instance;
	private static String filePath;
	private static boolean debug, data;
	private Log() { }
	
	// TODO better with getInstance(Main.class)
	public synchronized static Log getInstance() {
		return Log.getInstance(false,false,null);
	}
	public synchronized static Log getInstance(boolean shouldLogDebug, boolean shouldLogData, String filepaf) {
		if(instance == null) {
			instance = new Log();
			debug = shouldLogDebug;
			data = shouldLogData;
			if(filepaf != null)
				filePath = filepaf;
			else
				throw new NullPointerException();
		}
		return instance;
	}
	
	private boolean _logEvent(String s) {
		MyUtils.write(filePath, s);
		return true;
	}
	public boolean info(String e) {
		String s = LocalDateTime.now()+" [INFO] "+e;
		InOut.print(s);
		return _logEvent(s);
	}
	public boolean warn(String e) {
		String s = LocalDateTime.now()+" [WARN] "+e;
		InOut.print(s);
		return _logEvent(s);
	}
	public boolean error(String e, Exception ex) {
		String s = LocalDateTime.now()+" [ERROR] "+e+" Reason: \n"+ex;
		InOut.printException(e,ex);
		return _logEvent(s);
	}
	// TODO servono?
	public boolean debug(String e) {
		if(debug) {
			String s = LocalDateTime.now()+" [DEBUG] "+e;
			InOut.print(s);
			return _logEvent(s);
		}
		return false;
	}
	public boolean data(String e) {
		if(data) {
			String s = LocalDateTime.now()+" [DATA] "+e;
			InOut.print(s);
			return _logEvent(s);
		}
		return false;
	}
}