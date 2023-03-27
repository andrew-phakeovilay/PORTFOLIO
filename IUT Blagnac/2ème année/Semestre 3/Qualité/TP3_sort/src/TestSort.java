import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestSort {
	public static void main(String[] args) {
		TestSort ts = new TestSort();
		ts.test();
	}

	private void test() {
		List<String> li = Arrays.asList("aaa", "b", "AAA", "eee", "ffff", "g", "h", "i", "FF", "j", "k", "K");

		// Faire ici li.sort(...) comme demandé

		// li.sort(null);

		Comparator<String> c1 = new ComparaisonStringSurLongueur();
		li.sort(c1);
		printList(li);

		System.out.println("\n");

		Comparator<String> c2 = new ComparaisonStringMajMin();
		li.sort(c2);
		printList(li);

		System.out.println("\n");
		
		Comparator<String> c3 = new Comparator<String> () {
			@Override
			public int compare(String s1, String s2) {
				int result = -1;
				if(s1.compareTo(s2) == 0) {
					result = 0;
				}
				else if(s1.compareTo(s2) > 0) {
					result = 1;
				}
				return result;
			}
		};
		
		li.sort(c3);
		printList(li);
	}
	private void printList (List<String> l) {
		for (String s : l) {
			System.out.println(s);
		}
	}

	private class ComparaisonStringMajMin implements Comparator<String>{

		@Override
		public int compare(String s1, String s2) {
			int result = -1;
			s1 = s1.toUpperCase();
			s2 = s2.toUpperCase();
			if(s1.compareTo(s2) == 0) {
				result = 0;
			}
			else if(s1.compareTo(s2) > 0) {
				result = 1;
			}
			return result;
		}
	}
/**
 * La classe Contexte est une classe qui importe la classe Stratégie pour ensuite permettre d'effectuer la méthode contexte().
 * La méthode contexte() fait appel à la méthode algorithme() de la classe Stratégie et ses classes enfants.
 * La classe Stratégie est le centre du patron, elle est importée dans la classe Contexte et est hérité par d'autres classes
 * La méthode algorithme() est une méthode de la classe Stratégie et elle est réécrite dans les classes enfants de Stratégie.
 */
}
