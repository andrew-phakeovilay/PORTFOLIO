import java.util.Comparator;

public class ComparaisonStringSurLongueur implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		int result = -1;
		if(s1.length() == s2.length()) {
			result = 0;
		}
		else if(s1.length() > s2.length()) {
			result = 1;
		}
		return result;
	}
}

