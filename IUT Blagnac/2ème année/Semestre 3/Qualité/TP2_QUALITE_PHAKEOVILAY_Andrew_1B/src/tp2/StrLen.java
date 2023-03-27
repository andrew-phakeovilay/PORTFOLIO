package tp2;

import java.util.ArrayList;
import java.util.List;

/**
 * A class offering static methods for processing list of strings according to
 * the length of the strings.
 */
public class StrLen {

	/**
	 * Returns the first longest String found in strList.
	 * 
	 * @param strList	the list of strings
	 * @return	longest String, "" if strList is empty
	 */
	public static String longestString (List<String> strList) {
		int longueur = 0;
		int index = 0;
		if(strList.isEmpty()) {
			return "";
		}
		for (int i = 0; i < strList.size(); i++) {
			if(strList.get(i).length() > longueur) {
				longueur = strList.get(i).length();
				index = i;
			}
		}
		return strList.get(index);
	}


	/**
	 * Checks if all elements of strList are in strListContaining. The reverse condition is nor checked.
	 * 
	 * @param strList	List of searched elements
	 * @param strListContaining	List in which check is done
	 * @return true if all elements of strList are in strListContaining
	 */
	public static boolean isIncluded (List<String> strList, List<String> strListContaining) {
		if(strList.isEmpty() || strListContaining.isEmpty()) {
			return true;
		}
		int cpt = 0;
		for(int i = 0; i < strList.size(); i++) {
			for(int j = 0; j < strListContaining.size(); j++) {
				if(strList.get(i).equals(strListContaining.get(j))) {
					cpt++;
					continue;
				}
			}
		}
		if(cpt == strList.size()) {
			return true;
		}
		return false;
	}


	/**
	 * Checks if each elements of strList is in strListContaining at the same index.
	 * It returns a List of "T"/"F" strings indicating for each index if it is equal or not.
	 * 
	 * @param strList	List of searched elements
	 * @param strListContaining	List in which check is done
	 * @return a List of String "T"/"F" indicating for each index i
	 * 		if string at index i in strList equals ("T") string at index i in strListContaining or not ("F")
	 */
	public static List<String> indexAreIdentical (List<String> strList, List<String> strListContaining) {
		List<String> result =  new ArrayList<String>();
		int sizeContaining = strListContaining.size();
		for(int i = 0; i < strList.size(); i++) {
			if(sizeContaining > i) {
				if(strList.get(i).equals(strListContaining.get(i))) {
					result.add("T");
				}
				else {
					result.add("F");
				}
			}
			else {
				result.add("F");
			}
		}
		return result;
	}


	/**
	 * Select in a list of the strings the ones having a length greater or equal
	 * to a minimal length.
	 * 
	 * The order of the elements in the selection respect the initial order.
	 * 
	 * @param strList
	 *            the list of strings
	 * @param minLen
	 *            the minimal length
	 * @return A new list of strings, containing the strings long enough.
	 */
	public static List<String> selectLong(List<String> strList, int minLen) {
		List<String> listSuperior = new ArrayList<>();
		for(int i = 0; i < strList.size(); i++) {
			if(strList.get(i).length() >= minLen) {
				listSuperior.add(strList.get(i));
			}
		}
		return listSuperior;
	}

	/**
	 * Remove from a list of strings the strings strictly longer than a maximal
	 * length.
	 * 
	 * @param strList
	 *            the list of strings
	 * @param maxLen
	 *            the maximal length
	 */
	public static void removeLong(List<String> strList, int maxLen) {
		int newLength = strList.size();
		for(int i = 0; i < newLength; i++) {
			if(strList.get(i).length() > maxLen) {
				strList.remove(i);
				newLength--;
				i--;
			}
		}
	}

	/**
	 * Truncate the strings in a list of strings to a maximal length. The string
	 * shorter than this length are not modified.
	 * 
	 * @param strList
	 *            the list of strings
	 * @param maxLen
	 *            the maximal length
	 */
	public static void truncate(List<String> strList, int maxLen) {
		for(int i = 0; i < strList.size(); i++) {
			if(strList.get(i).length() > maxLen) {
				int cpt = 0;
				for(int j = 0; j < strList.get(i).length(); j++) {
					if(strList.get(i).length() - j == maxLen) {
						cpt = j;
						break;
					}
				}
				strList.set(i, strList.get(i).substring(cpt));

			}
		}
	}

	/**
	 * Returns a copy of a list of strings, sorted by increasing length. The
	 * sort algorithm is stable.
	 * 
	 * @param strList
	 *            the list of strings
	 * @return A copy sorted by increasing length.
	 */
	public static List<String> increasingLength(List<String> strList) {
		List<String> triList = new ArrayList<>(strList);
		int taille = strList.size();  
		String permut = "";  
		for(int i=0; i < taille; i++){
			for(int j=1; j < (taille-i); j++){  
				if(triList.get(j-1).length() > triList.get(j).length()){
					permut = triList.get(j-1);
					triList.set(j-1, triList.get(j));
					triList.set(j, permut);
				}
			}
		}
		return triList;
	}


	/**
	 * Determines the most frequent string length in a list of strings. If
	 * several string length are tie, the highest one is returned.
	 * 
	 * @param strList
	 *            the list of strings
	 * @return The most frequent string length.
	 */
	public static int mostFrequent(List<String> strList) {
		int length = 0;
		for(int i = 0; i < strList.size(); i++) {
			int max = 0;
			for(int j = 0; j < strList.size(); j++) {
				if(strList.get(i).length() == strList.get(j).length()) {
					max++;
				}
			}
			if(length < max) {
				length = strList.get(i).length();
			}
		}
		return length;
	}

}
