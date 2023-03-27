package tp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Utility class for dictionaries. This class offers static methods for
 * manipulating objects of type Map.
 * 
 */
public class MapUtils {

	/**
	 * Exchange the values attached to two given keys.
	 * 
	 * @param dict
	 *            the dictionary where the exchange is performed
	 * @param k1
	 *            one key
	 * @param k2
	 *            another key
	 * @throws IllegalArgumentException
	 *             if any of the two keys is not in the dictionary.
	 */
	public static <K, V> void exchange(Map<K, V> dict, K k1, K k2) {
		// TODO: exercise 1
		if(dict.containsKey(k1) && dict.containsKey(k2)) {
			V valeurExchange = dict.get(k2);
			dict.put(k2, dict.get(k1));
			dict.put(k1, valeurExchange);
		}
		else {
			throw new IllegalArgumentException("La clé n'est pas dans le dictionnaire");
		}
	}

	/**
	 * Construct a dictionary from a list of strings representing the key-value
	 * pairs. Each string must be of the form "key:value" where key and value
	 * contains no ":".
	 * 
	 * @param pairs
	 *            a list of strings, each representing a pair
	 * @return the corresponding dictionary.
	 * @throws IllegalArgumentException
	 *             if any of the strings is not well-formed.
	 */
	public static Map<String, String> ofPairList(List<String> pairs) {
		// TODO: exercise 2
		Map<String, String> dict = new HashMap<String, String>();
		for(int i = 0; i < pairs.size(); i++) {
			if(pairs.get(i).contains(":") && (pairs.get(i).length() - pairs.get(i).replace(":", "").length() == 1)) {	

				String texte1 = pairs.get(i).substring(0, pairs.get(i).indexOf(":"));
				String texte2 = pairs.get(i).substring(pairs.get(i).indexOf(":")+1, pairs.get(i).length());
				dict.put(texte1, texte2);		
			}
			else{
				throw new IllegalArgumentException("Chaine erronée");
			}
		}
		return dict;
	}

	/**
	 * Return the list of keys that map to a given value.
	 * 
	 * @param dict
	 *            the dictionary where to search
	 * @param value
	 *            the value to search
	 * @return the list of keys mapping to that value.
	 */
	public static <K, V> List<K> selectByValue(Map<K, V> dict, V value) {
		// TODO: exercise 3
		List<K> keys = new ArrayList<K>();
		for (K key: dict.keySet())
		{
			if (dict.get(key).equals(value)) {
				keys.add(key);
			}
		}
		return keys;
	}

	/**
	 * Remove the pairs where the key is equal to the value.
	 * 
	 * @param dict
	 *            the dictionary where to remove the pairs
	 */
	public static <T> void removeEquals(Map<T, T> dict) {
		// TODO: exercise 4
		List<T> removeTList = new ArrayList<T>();
		for(T vKey: dict.keySet()) {
			if(vKey.equals(dict.get(vKey))) {
				removeTList.add(vKey);
			}
		}
		for(int i = 0; i < removeTList.size(); i++) {
			dict.remove(removeTList.get(i));
		}
	}

	/**
	 * Compute the number of time that each element appears in a list.
	 * The result is given as a dictionary binding elements to number of appearance.
	 *
	 * @param list
	 * 		      the list where to count
	 * @return a dictionary associating to each element of the list the number of times it appears in the list.
	 */
	public static <E> Map<E, Integer> frequency(List<E> list) {
		// TODO: exercise 5
		Map<E, Integer> dictCount = new HashMap<E, Integer>();
		for(int i = 0; i < list.size(); i++) {
			int count = 0;
			for(int j = 0; j < list.size(); j++) {
				if(list.get(i).equals(list.get(j))){
					count++;
				}
			}
			dictCount.put(list.get(i), count);
		}
		return dictCount;
	}

	/**
	 * Invert a dictionary by computing a new dictionary where the keys are the old values and the values are
	 * lists of old keys.
	 *
	 * @param dict
	 *            the dictionary to revert
	 * @return a new dictionary, inverse of the input one.
	 */
	public static <K, V> Map<V, List<K>> invert(Map<K, V> dict) {
		// TODO: exercise 6
		Map<V, List<K>> invertDict = new HashMap<V, List<K>>();
		
		
		for(Map.Entry<K, V> e : dict.entrySet()) {
			K key = e.getKey();
			V value = e.getValue();
			if(invertDict.containsKey(value)) {
				invertDict.get(value).add(key);
			} else {
				invertDict.put(value, new ArrayList<K>());
				invertDict.get(value).add(key);
			}
		}
		return invertDict;
	}

	/**
	 * Merge the input dictionaries together.
	 * If a key is bound in both dictionaries, the associated values are combined using the combination function.
	 * @param d1
	 *            the first dictionary
	 * @param d2
	 *            the second dictionary
	 * @param combine
	 *            the combination function (optional)
	 * @return a new dictionary merge the input dictionaries.
	 * @throws IllegalArgumentException
	 *             if a key is in both dictionaries and no combination function is provided.
	 */
	public static <K, V> Map<K, V> merge(Map<K, V> d1, Map<K, V> d2,
			BiFunction<V, V, V> combine) {
		// TODO: exercise 7
		Map<K,V> mergedDict = new HashMap<K,V>();
		
		for(Map.Entry<K,V> e : d1.entrySet()) {
			mergedDict.put(e.getKey(), e.getValue());
		}
		for(Map.Entry<K,V> e : d2.entrySet()) {
			if(mergedDict.containsKey(e.getKey())) {
				if(combine == null) {
					throw new IllegalArgumentException();
				}
					V value = combine.apply(e.getValue(), d1.get(e.getKey()));
					mergedDict.put(e.getKey(), value);
				} else {
					mergedDict.put(e.getKey(), e.getValue());
				}
			}		
		return mergedDict;
	}
}
