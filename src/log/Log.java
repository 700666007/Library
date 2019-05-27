package log;

import java.util.List;
import java.util.ArrayList;

public class Log 
{
	private static Log instance;
	private Log() { }
	
	public synchronized static Log getInstance() {
		if(instance == null)
			instance = new Log();
		return instance;
	}
	
	private List<String> events = new ArrayList<String>();
	public boolean addEvent(String e) {
		events.add(e);
		return true;
	}
}