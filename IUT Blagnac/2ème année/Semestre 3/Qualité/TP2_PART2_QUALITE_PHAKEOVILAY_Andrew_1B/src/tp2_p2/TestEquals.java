package tp2_p2;

import java.util.Arrays;
import java.util.List;

public class TestEquals {

	public static void main (String args[]) {
		
		List<String> li = Arrays.asList("a", "bb", "c", "dd", "e", "ff");
		
		System.out.println("Tests for List<String>:");
		System.out.println(li.contains("a"));
		System.out.println(li.contains("ff"));
		System.out.println(li.contains("c"));
		System.out.println(li.contains("ZZZ"));
		
		
		List<Person> lp = Arrays.asList(
				new Person ("a", "a"),
				new Person ("bb", "bb"),
				new Person ("c", "c"),
				new Person ("dd", ""),
				new Person ("e", "e"),
				new Person ("ff", "ff")
				);
		
		System.out.println("Tests for List<Person>:");
		System.out.println(lp.contains(new Person("a", "a")));
		System.out.println(lp.contains(new Person("ff", "ff")));
		System.out.println(lp.contains(new Person("c", "c")));
		System.out.println(lp.contains(new Person("ZZZ", "ZZZ")));
		
		
			
		List<String> li2 = Arrays.asList("a", "bb", "c", "dd", "e", "ff");
		List<Person> lp2 = Arrays.asList(
				new Person ("a", "a"),
				new Person ("bb", "bb"),
				new Person ("c", "c"),
				new Person ("dd", ""),
				new Person ("e", "e"),
				new Person ("ff", "ff")
				);
		System.out.println("Tests equals:");
		System.out.println(li.equals(li2));
		System.out.println(lp.equals(lp2));
		
	}
}
