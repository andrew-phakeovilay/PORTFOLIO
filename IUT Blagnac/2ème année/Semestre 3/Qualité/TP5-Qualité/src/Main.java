import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Main {
	public static void main(String args[]) throws ParseException {
		NumberFormat nff = NumberFormat.getInstance(Locale.FRANCE);
		NumberFormat nfe = NumberFormat.getInstance(Locale.ENGLISH);
		System.out.println(System.identityHashCode(nff));
		System.out.println(System.identityHashCode(nfe));
		
		System.out.println(nff.getClass());
		System.out.println(nfe.getClass());
		
		System.out.println("123456789.123456789");
		System.out.println(nff.format(123456789.123456789));
		System.out.println(nfe.format(123456789.123456789));
		
		System.out.println(nff.parse("10,23"));
		System.out.println(nfe.parse("10.23"));
		
		System.out.println(nff.parse("123 456 789,123"));
		System.out.println(nff.format(nff.parse("123 456 789,123")));
		
		System.out.println(nfe.parse("123,456,789.123"));
		System.out.println(nfe.format(nfe.parse("123,456,789.123")));

		System.out.println(nff.parse("123,456,789.123"));
		System.out.println(nfe.parse("123 456 789,123"));
		
		
		NumberFormat nfi = NumberFormat.getInstance(Locale.ITALIAN);
		NumberFormat nfa = NumberFormat.getInstance(Locale.GERMAN);
		System.out.println(nfi.format(123456789.123456789));
		System.out.println(nfa.format(123456789.123456789));
	}
}
