package mars.apigen.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An array utilization class
 * @author Shaoheng.Weng
 *
 */
public abstract class ArrayUitls {
	
	/**
	 * splits a given string to array with the specified delimiter,
	 * if the given string is null then an empty array will be got.
	 * @param str
	 * @param delim
	 * @return <code>string</code> array
	 */
	public static String[] stringAsArray(String str, String delim) {

		if (str != null)
			return delim == null ? new String[] {str} : str.split(delim);
		
		return new String[0];
	}
	
	/**
	 * splits a given string to array with comma or semicolon as delimiter,
	 * if the given string is null then an empty array will be got.
	 * @param str
	 * @return <code>string</code> array
	 */
	public static String[] stringAsArray(String str) {
		return stringAsArray(str, "[,;]");
	}
	
	/**
	 * transform a given array to a list, if the array is null then an empty 
	 * list will be got
	 * @param array
	 * @return <code>List&lt;T&gt;</code>
	 */
	public static <T> List<T> asList(T[] array) {
		return array != null ? Arrays.asList(array) : new ArrayList<>();
	}
	
	/**
	 * splits a given string to list with the specified delimiter,
	 * if the given string is null then an empty list will be got.
	 * @param str
	 * @param delim
	 * @return <code>List&lt;String&gt;</code>
	 */
	public static List<String> asList(String str, String delim) {
		return asList(stringAsArray(str, delim));
	}
	
	/**
	 * splits a given string to list with comma or semicolon as delimiter,
	 * if the given string is null then an empty list will be got.
	 * @param str
	 * @return <code>List&lt;String&gt;</code>
	 */
	public static List<String> asList(String str) {
		return asList(stringAsArray(str));
	}
	
	/**
	 * transform a given list to array, if the list is null then an empty 
	 * array will be got
	 * @param list
	 * @param clz the component's class of the given list
	 * @return <code>T[]</code>
	 */
	public static <T> T[] asArray(List<T> list, Class<T> clz) {
		int len = list == null ? 0 : list.size();
		
		@SuppressWarnings("unchecked")
		T[] array = (T[])Array.newInstance(clz, len);
		return len != 0 ? list.toArray(array) : array;
	}
	
	/**
	 * checks the given array is not null and not empty.
	 * @param a
	 * @return if not null and not empty then <code>true</code> otherwise <code>false</code>
	 */
	public static <T> boolean isNonEmpty(T[] a) {
		return a != null && a.length != 0;
	}
	
	/**
	 * checks the given list is not null and not empty.
	 * @param list
	 * @return if not null and not empty then <code>true</code> otherwise <code>false</code>
	 */
	public static <T> boolean isNonEmpty(List<T> list) {
		return list != null && !list.isEmpty();
	}
	
	/**
	 * make sure the given list is not null, if is null then return an empty list
	 * @param list
	 * @return <code>List&lt;String&gt;</code>
	 */
	public static <T> List<T> nonNull(List<T> list) {
		return list != null ? list : new ArrayList<T>();
	}
	
	/**
	 * make sure the given array is not null, if is null then return an empty array
	 * @param a
	 * @return <code>T[]</code>
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] nonNull(T[] a) {
		return a != null ? a : (T[])new ArrayList<T>().toArray();
	}
}
