//package entities;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class CachedLibrary implements LibraryDao {
//
//	private LibraryDaoImpl real;
//	private Map<String,List<Book>> cache = new HashMap<>();
//	
//	public CachedLibrary(LibraryDaoImpl real) {
//		this.real = real;
//	}
//	
//	
//	
//	@Override
//	public List<Book> getBooksList() {
//		return real.getBooksList();
//	}
//	@Override
//	public List<Book> getBooksList(String str) {
//		if(cache.containsKey(str))
//			return cache.get(str);
//		else {
//			cache.put(str, real.getBooksList(str));
//			return cache.get(str);
//		}
//	}
//	
//}
