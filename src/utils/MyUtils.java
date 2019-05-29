package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public abstract class MyUtils {

	static Log logger = Log.getInstance();
	public static <T> String commaSepValues(List<T> list) {
		StringJoiner joiner = new StringJoiner(",");
		for (T t : list)
			joiner.add(t.toString());
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
		for (String key : map.keySet())
			joiner.add("\n  "+key+":"+map.get(key));
		return "{"+joiner.toString()+"\n}";
	}
	static File createNewFile(String path) {
		try {
			File file = new File(path);
			if(file.exists())
				logger.info("File "+path+" already exists.");
			else if(file.createNewFile())
				logger.info("File "+path+" successfully created.");
			else
				logger.info("File "+path+" cannot be created.");
			return file;
		} catch(IOException e) {
			logger.error("File creation failed.",e);
			return null;
		}
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
}
