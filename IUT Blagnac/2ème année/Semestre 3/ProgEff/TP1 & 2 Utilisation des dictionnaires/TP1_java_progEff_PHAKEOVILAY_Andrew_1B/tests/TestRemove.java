package tp1.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import tp1.MapUtils;

public class TestRemove {

	@Test
	public void testCore() {
		Map<String, String> dict = new HashMap<>();
		dict.put("un", "one");
		dict.put("deux", "deux");
		dict.put("trois", "three");
		MapUtils.removeEquals(dict);
		Map<String, String> expected = new HashMap<>();
		expected.put("un", "one");
		expected.put("trois", "three");
		assertEquals(expected, dict);
	}

	@Test
	public void testComparison() {
		Map<String, String> dict = new HashMap<>();
		dict.put("un", "one");
		dict.put("deux", new String("deux")); // Equal string, different
												// reference
		dict.put("trois", "three");
		MapUtils.removeEquals(dict);
		Map<String, String> expected = new HashMap<>();
		expected.put("un", "one");
		expected.put("trois", "three");
		assertEquals(expected, dict);
	}
	
	@Test
	public void testAll() {
		Map<String, String> dict = new HashMap<>();
		dict.put("un", "un");
		dict.put("deux", "deux");
		dict.put("trois", "trois");
		MapUtils.removeEquals(dict);
		Map<String, String> expected = new HashMap<>();
		assertEquals(expected, dict);
	}

}
