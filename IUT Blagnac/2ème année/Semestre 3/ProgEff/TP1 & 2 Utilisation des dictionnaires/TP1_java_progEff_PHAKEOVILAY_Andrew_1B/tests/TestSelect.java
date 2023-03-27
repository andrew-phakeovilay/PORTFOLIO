package tp1.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import tp1.MapUtils;

public class TestSelect {

	@Test
	public void testCore() {
		Map<String, String> dict = new HashMap<>();
		dict.put("UA", "not funny");
		dict.put("AA", "funny");
		dict.put("IUT", "funny");
		List<String> result = MapUtils.selectByValue(dict, "funny");
		List<String> expected = Arrays.asList("AA", "IUT");
		assertEquals(new HashSet<>(expected), new HashSet<>(result)); // Ignoring
																		// the
																		// order
	}

	@Test
	public void testSingle() {
		Map<String, String> dict = new HashMap<>();
		dict.put("UA", "not funny");
		dict.put("AA", "funny");
		dict.put("IUT", "funny");
		List<String> result = MapUtils.selectByValue(dict, "not funny");
		List<String> expected = Arrays.asList("UA");
		assertEquals(expected, result);
	}

	@Test
	public void testNone() {
		Map<String, String> dict = new HashMap<>();
		dict.put("UA", "not funny");
		dict.put("AA", "funny");
		dict.put("IUT", "funny");
		List<String> result = MapUtils.selectByValue(dict, "super funny");
		List<String> expected = Arrays.asList();
		assertEquals(expected, result);
	}

	@Test
	public void testComparison() {
		Map<String, String> dict = new HashMap<>();
		dict.put("UA", "not funny");
		dict.put("AA", "funny");
		dict.put("IUT", "funny");
		List<String> result = MapUtils.selectByValue(dict, new String(
				"not funny")); // Equal string, different reference
		List<String> expected = Arrays.asList("UA");
		assertEquals(expected, result);
	}

}
