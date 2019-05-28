package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public abstract class MyUtils {

	// COMMA SEPARETED STRINGS =======================================================================================================================
	public static <T> String commaSepValues(List<T> list) {
		StringJoiner joiner = new StringJoiner(",");
		for (T t : list)
			joiner.add(t.toString());
		return joiner.toString();
	}

	// DATE MANIPULATION =======================================================================================================================
	public static LocalDate makeLocalDate(String date, Character separationChar) {
		String[] fields = date.split(separationChar.toString());
		return makeLocalDate(Integer.parseInt(fields[0]),
				Integer.parseInt(fields[1]),
				Integer.parseInt(fields[2]));
	}
	public static LocalDate makeLocalDate(int year, int month, int dayOfMonth) {
		return LocalDate.of(year, month, dayOfMonth);
	}

	// DATA STRUCTURE RELATED =======================================================================================================================
	public static boolean isStrInList(String x, String[] keys) {
		return Arrays.asList(keys).contains(x);
	}
	public static List<String> set2list(Collection<String> set) {
		List<String> list = new ArrayList<String>();
		list.addAll(set);
		return list;
	}
	public static List<Integer> populateList(int size, int maxNum) {
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<size; i++)
			list.add((int) (Math.random() * maxNum));
		return list;
	}
	public static String renderList(List<Integer> list) {
		if(list == null || list.size() == 0)
			return "[]";
		return "["+commaSepValues(list)+"]";
	}
	public static String renderMap(Map<String,String> map) {
		if(map == null || map.size() == 0)
			return "{}";
		StringJoiner joiner = new StringJoiner(",");
		for (String key : map.keySet())
			joiner.add("\n  "+key+":"+map.get(key));
		return "{"+joiner.toString()+"\n}";
	}

	// SORTING =======================================================================================================================
	public static List<Integer> quicksort(List<Integer> list) {
		if(list.size() <= 1)
			return list;
		else {
			List<Integer> lessThan = new ArrayList<>(),
					moreThan = new ArrayList<>(),
					equal = new ArrayList<>();
			equal.add(list.get(0));
			for(int i=1; i<list.size(); i++)
				if(list.get(i)>list.get(0))
					moreThan.add(list.get(i));
				else if(list.get(i)<list.get(0))
					lessThan.add(list.get(i));
				else
					equal.add(list.get(i));
			quicksort(lessThan).addAll(equal);
			lessThan.addAll(quicksort(moreThan));
			return lessThan;
		}
	}

	// FILE MANAGEMENT =======================================================================================================================
	public static File createNewFile(String path) {
		try {
			File file = new File(path);
			if(file.exists())
				InOut.print("File "+path+" already exists.");
			else if(file.createNewFile())
				InOut.print("File "+path+" successfully created.");
			else
				InOut.print("File "+path+" cannot be created.");
			return file;
		} catch(IOException e) {
			InOut.printException("File creation failed.",e);
			return null;
		}
	}
	public static boolean write(File file, String str) {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(str);
			bw.flush();
			bw.close();
			return true;
		} catch(IOException e) {
			InOut.printException("Couldn't write to file..", e);
			return false;
		}
	}


//	private static String _removeLastNChars(String s, int n) {
//		return s.substring(0, s.length()-(n+1));
//	}
//	public Integer[] getArray0toN(int n) {
//		Integer[] res = new Integer[n+1];
//		for(int i=0; i<res.length; i++)
//			res[i]=i;
//		return res;
//	}
}
