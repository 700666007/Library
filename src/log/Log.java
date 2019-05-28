package log;

import java.io.File;
import java.time.LocalDateTime;

import utils.InOut;
import utils.MyUtils;

public class Log 
{
	private static Log instance;
	private static File logger;
	private Log() { }
	
	public synchronized static Log getInstance() {
		if(instance == null) {
			instance = new Log();
			logger = MyUtils.createNewFile("logger.txt");
		}
		return instance;
	}
	
	public boolean info(String e) {
		String s = LocalDateTime.now()+" [INFO] "+e;
		InOut.print(s);
		MyUtils.write(logger, s);
		return true;
	}
	public boolean warn(String e) {
		String s = LocalDateTime.now()+" [WARN] "+e;
		InOut.print(s);
		MyUtils.write(logger, s);
		return true;
	}
	public boolean error(String e, Exception ex) {
		String s = LocalDateTime.now()+" [ERROR] "+e+" Reason: \n"+ex;
		InOut.printException(e,ex);
		MyUtils.write(logger, s);
		return true;
	}
}