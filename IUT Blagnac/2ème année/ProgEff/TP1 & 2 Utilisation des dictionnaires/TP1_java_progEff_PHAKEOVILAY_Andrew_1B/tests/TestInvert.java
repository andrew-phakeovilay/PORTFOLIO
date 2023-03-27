package tp1.tests;

import org.junit.Test;
import tp1.MapUtils;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TestInvert {

	@Test
	public void testCore() {
		Map<String, Integer> dict = new HashMap<>();
		dict.put("A", 1);
		dict.put("B", 2);
		dict.put("C", 2);
		Map<Integer, List<String>> result = MapUtils.invert(dict);
		assertEquals("result keys", new HashSet<>(Arrays.asList(1, 2)), result.keySet());
		assertEquals("result.get(1)", List.of("A"), result.get(1));
		assertEquals("result.get(2)",
				new HashSet<>(Arrays.asList("B", "C")), new HashSet<>(result.get(2)));
	}

}
