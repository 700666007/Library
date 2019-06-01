package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;

public abstract class MyUtils {

	static Log logger = Log.getInstance();
	public static <T> String commaSepValues(List<T> list) {
		StringJoiner joiner = new StringJoiner(",");
		for (T t : list)
			joiner.add(t.toString());
		return joiner.toString();
	}
	public static <T> String commaSepQuestionMarks(int n) {
		StringJoiner joiner = new StringJoiner(",");
		for (int i=0; i<n ; i++)
			joiner.add("?");
		return joiner.toString();
	}
	public static List<String> set2list(Collection<String> set) {
		List<String> list = new ArrayList<String>();
		list.addAll(set);
		return list;
	}
	public static String renderMap(Map<String,String> map) {
		if(map == null || map.size() == 0)
			return "{}";
		StringJoiner joiner = new StringJoiner(",");
		for(Map.Entry<String,String> entry : map.entrySet())
			joiner.add("\n  "+entry.getKey()+":"+entry.getValue());
		return "{"+joiner.toString()+"\n}";
	}
	static boolean createLogFile(String path) {
		try {
			File file = new File(path);
			if(file.exists())
				logger.info("File "+path+" already exists.");
			else if(file.createNewFile())
				logger.info("File "+path+" successfully created.");
			else {
				logger.info("File "+path+" cannot be created.");
				return false;
			}
		} catch(IOException e) {
			InOut.printException("File creation failed.",e);
			return false;
		}
		return true;
	}
	static boolean write(String path, String str) {
		try {
			FileWriter fw = new FileWriter(new File(path));
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(str);
			bw.flush();
			bw.close();
			return true;
		} catch(IOException e) {
			logger.error("Couldn't write to file..", e);
			return false;
		}
	}
	public static Map<String,String> request2map(HttpServletRequest req) {
		Map<String,String> pmap = new HashMap<>();
		for(Map.Entry<String,String[]> entry : req.getParameterMap().entrySet())
			pmap.put(entry.getKey(), entry.getValue()[0]);
		return pmap;
	}
}
