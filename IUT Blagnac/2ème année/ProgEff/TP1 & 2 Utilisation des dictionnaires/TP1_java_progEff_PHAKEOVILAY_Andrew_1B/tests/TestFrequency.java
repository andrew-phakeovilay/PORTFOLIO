package tp1.tests;

import org.junit.Test;
import tp1.MapUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestFrequency {

	@Test
	public void testCore() {
		List<String> list = Arrays.asList("A", "Z", "A", "B", "A", "Z");
		Map<String, Integer> result = MapUtils.frequency(list);
		Map<String, Integer> expected = new HashMap<>();
		expected.put("A", 3);
		expected.put("B", 1);
		expected.put("Z", 2);
		assertEquals(expected, result);
	}
}
